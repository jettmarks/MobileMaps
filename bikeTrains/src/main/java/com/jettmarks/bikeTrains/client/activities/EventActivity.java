/**
 *   Copyright 2014 Jett Marks
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created Apr 20, 2014
 */
package com.jettmarks.bikeTrains.client.activities;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.dialog.AlertDialog;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;
import com.jettmarks.bikeTrains.client.ClientFactory;
import com.jettmarks.bikeTrains.client.bean.BikeTrainRoute;
import com.jettmarks.bikeTrains.client.bean.DisplayGroupDTO;
import com.jettmarks.bikeTrains.client.bean.DisplayOnlyRoute;
import com.jettmarks.bikeTrains.client.bean.Route;
import com.jettmarks.bikeTrains.client.places.EventPlace;
import com.jettmarks.bikeTrains.client.places.EventSelectionPlace;
import com.jettmarks.bikeTrains.client.places.HomePlace;
import com.jettmarks.bikeTrains.client.places.RouteDetailsPlace;
import com.jettmarks.bikeTrains.client.rep.RouteContainer;
import com.jettmarks.bikeTrains.client.rep.RouteContainerFactory;
import com.jettmarks.bikeTrains.client.rep.RouteContainerImpl;
import com.jettmarks.bikeTrains.client.rep.ServiceWrapper;
import com.jettmarks.bikeTrains.client.ui.EventView;

public class EventActivity extends MGWTAbstractActivity {

	protected ClientFactory clientFactory;

	protected EventView view;
	protected RouteContainer routeContainer;

	protected EventActivity(EventView view) {
		this.view = view;
	}

	/**
	 * @param newPlace
	 * @param clientFactory
	 */
	protected void setupInstance(Place newPlace, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		EventPlace place = (EventPlace) newPlace;
		String description = place.getDescription();
		String displayGroupName = place.getDisplayGroupName();
		view.setDisplayGroupName(displayGroupName);
		view.setDescription(description);
		DisplayGroupDTO displayGroup = new DisplayGroupDTO();
		displayGroup.setDisplayName(displayGroupName);
		displayGroup.setDescription(description);
		displayGroup.setEventDate(place.getEventDate());
		routeContainer = RouteContainerFactory.getRouteContainer();
		routeContainer.addActivity(this);
		routeContainer.addView(view);
		routeContainer.setCurrentDisplayGroup(displayGroup);
		MGWT.addOrientationChangeHandler(new OrientationChangeHandler() {

			@Override
			public void onOrientationChanged(OrientationChangeEvent event) {
				adjustBackButtonVisibility(event.getOrientation());
			}
		});
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);

		// For testing, a desktop browser can be narrowed to look like Portrait
		ORIENTATION orientation = MGWT.getOrientation();
		adjustBackButtonVisibility(orientation);

		view.enableForwardButton(true);

		if (routeContainer.displayGroupHasChanged()) {
			// We'll be loading new records, so start the progress bar
			RouteContainerImpl rcImpl = (RouteContainerImpl) RouteContainerFactory
					.getRouteContainer();
			// Will be re-opened later with the proper counts
			rcImpl.openProgressBar(null);

			beginReadingRoutesAsync(view.getDisplayGroupName());
		} else if (routeContainer.getSelectedRoute() != null) {
			view.selectRoute(routeContainer.getSelectedRouteIndex());
		}
		view.setRouteSelectedHandler(new RouteListCellSelectedHandler());
		this.addRegistration(view);
		panel.setWidget(view);
	}

	/**
	 * Uses async calls to retrieve the routes under control of the
	 * RouteContainer.
	 * 
	 * @param displayGroupName
	 */
	private void beginReadingRoutesAsync(String displayGroupName) {
		// Kicks off reading the routes in the DisplayGroup under control
		// of the RouteContainer
		RouteContainerImpl rcImpl = (RouteContainerImpl) RouteContainerFactory
				.getRouteContainer();
		DisplayGroupDTO dispGroup = new DisplayGroupDTO();
		dispGroup.setDisplayName(displayGroupName);
		ServiceWrapper serviceWrapper = new ServiceWrapper(rcImpl);
		serviceWrapper.showRoutes(dispGroup);
	}

	/**
	 * @param orientation
	 */
	private void adjustBackButtonVisibility(ORIENTATION orientation) {
		switch (orientation) {
		case PORTRAIT:
			view.enableBackButton(true);
			break;
		case LANDSCAPE:
			view.enableBackButton(false);
			break;
		}
	}

	/**
	 * Responsible for removing the Handler Registrations.
	 * 
	 * @see com.googlecode.mgwt.mvp.client.MGWTAbstractActivity#onStop()
	 */
	@Override
	public void onStop() {
		// TODO: I believe this is the place we can make sure the map elements
		// are cleared and returned to their previous empty state.
		super.onStop();
		cancelAllHandlerRegistrations();
	}

	public class RouteListCellSelectedHandler implements CellSelectedHandler {

		@Override
		public void onCellSelected(CellSelectedEvent event) {
			RouteContainer rc = RouteContainerFactory.getRouteContainer();
			// We tell the RouteContainer about the event, and it tells us
			// what we need to do
			rc.setSelectedRoute(event.getIndex());
		}

	}

	/**
	 * As routes come in, we pass to the view and also keep a list in the
	 * activity.
	 * 
	 * This provides a single list even though there may be multiple parts to
	 * the view or multiple views.
	 * 
	 * We're also adding the event handling for the route's polylines here as
	 * well.
	 * 
	 * @param route
	 */
	public void add(Route route) {
		view.add(route);
		DisplayOnlyRoute btRoute = (DisplayOnlyRoute) route;
		btRoute.addHandlerRegistration(new RouteClickMapHandler(btRoute));
	}

	/**
	 * @param newIndex
	 * @param selectedRoute
	 */
	public void setSelectedRoute(Integer newIndex, Route selectedRoute) {
		view.selectRoute(newIndex);
	}

	/**
     * 
     */
	public void clearMap() {
		view.clearMap();
	}

	public void resize(List<BikeTrainRoute> routes) {
		view.resize(routes);
	}

	/**
	 * @see com.googlecode.mgwt.mvp.client.MGWTAbstractActivity#start(com.google.gwt
	 *      .user.client.ui.AcceptsOneWidget,
	 *      com.google.gwt.event.shared.EventBus)
	 */
	public void addRegistration(EventView eventView) {
		addHandlerRegistration(eventView.getHomeButton().addTapHandler(
				new TapHandler() {

					@Override
					public void onTap(TapEvent event) {
						RouteContainerFactory.getRouteContainer()
								.setSelectedRoute((Integer) null);
						view.clearMap();
						clientFactory.getPlaceController()
								.goTo(new HomePlace());
					}

				}));
		addHandlerRegistration(eventView.getHeaderTapHandlers()
				.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						BikeTrainRoute selectedRoute = (BikeTrainRoute) RouteContainerFactory
								.getRouteContainer().getSelectedRoute();
						if (selectedRoute != null) {
							clientFactory.getPlaceController().goTo(
									new RouteDetailsPlace(selectedRoute));
						} else {
							com.googlecode.mgwt.ui.client.widget.dialog.AlertDialog noRouteSelectedAlert = new AlertDialog(
									"No Train Selected",
									"Choose a Bike Train to view");
							noRouteSelectedAlert.show();
						}
					}
				}));

		addHandlerRegistration(eventView.getBackbutton().addTapHandler(
				new TapHandler() {

					@Override
					public void onTap(TapEvent event) {
						view.clearMap();
						RouteContainerFactory.getRouteContainer()
								.setSelectedRoute((Integer) null);
						clientFactory.getPlaceController().goTo(
								new EventSelectionPlace());
					}

				}));

		addHandlerRegistration(eventView.getForwardbutton().addTapHandler(
				new TapHandler() {
					@Override
					public void onTap(TapEvent event) {
						BikeTrainRoute selectedRoute = (BikeTrainRoute) RouteContainerFactory
								.getRouteContainer().getSelectedRoute();
						if (selectedRoute != null) {
							clientFactory.getPlaceController().goTo(
									new RouteDetailsPlace(selectedRoute));
						} else {
							AlertDialog noRouteSelectedAlert = new AlertDialog(
									"No Train Selected",
									"Choose a Bike Train to view");
							noRouteSelectedAlert.show();
						}
					}
				}));
	}

	/**
	 * Description.
	 * 
	 * @author jett
	 */
	public class RouteClickMapHandler implements ClickMapHandler {

		DisplayOnlyRoute displayOnlyRoute = null;

		public RouteClickMapHandler(DisplayOnlyRoute route) {
			displayOnlyRoute = route;
		}

		@Override
		public void onEvent(ClickMapEvent event) {
			// Invoked when the polyline is clicked
			RouteContainerFactory.getRouteContainer().setSelectedRoute(
					displayOnlyRoute);
		}

	}

}
