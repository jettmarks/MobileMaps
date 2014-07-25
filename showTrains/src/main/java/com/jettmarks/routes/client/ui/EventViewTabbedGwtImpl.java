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
 * Created Jun 2, 2014
 */
package com.jettmarks.routes.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.maps.client.overlays.Marker;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;
import com.googlecode.mgwt.ui.client.widget.tabbar.Tab;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButtonBase;
import com.jettmarks.routes.client.MapDetailViewGwtImpl;
import com.jettmarks.routes.client.bean.BikeTrainRoute;
import com.jettmarks.routes.client.bean.Route;
import com.jettmarks.routes.client.rep.RouteContainer;
import com.jettmarks.routes.client.rep.RouteContainerFactory;
import com.jettmarks.routes.client.ui.MarkerFactory.MarkerType;
import com.jettmarks.routes.client.ui.tab.ListTabBarButton;
import com.jettmarks.routes.client.ui.tab.MapTabBarButton;
import com.jettmarks.routes.client.util.ScreenSize;

/**
 * Description.
 * 
 * @author jett
 */
public class EventViewTabbedGwtImpl extends MapDetailViewGwtImpl implements
		EventView {
	private MapWidget mapWidget;

	private static LatLngBounds mapBounds = null;

	private static int currentZoomLevel = 13;

	private List<Route> routes = new ArrayList<Route>();

	private String description;

	private String displayGroupName;

	private CellList<Route> listWidget;

	private ScrollPanel scrollPanel;

	public EventViewTabbedGwtImpl() {
		super();
		// Take care of the header for navigation
		setupHeader();
		mapWidget = prepareMap();
		listWidget = prepareList();
		tabPanel.addTab(prepareMapTab(mapWidget));
		tabPanel.addTab(prepareListTab(listWidget));
	}

	/**
   * 
   */
	private void setupHeader() {
		headerPanel.setRightWidget(headerForwardButton);
		headerPanel.setLeftWidget(headerBackButton);
		enableForwardButton(true);
	}

	/**
	 * @param listWidget
	 * @return
	 */
	private Tab prepareListTab(CellList<Route> listWidget) {
		Tab tab = new Tab();
		scrollPanel = new ScrollPanel();
		scrollPanel.add(listWidget);
		scrollPanel.setSize("100%", "100%");
		scrollPanel.setHeight(ScreenSize.getHeight() - 80 + "px");
		scrollPanel.setScrollingEnabledX(true);

		TabBarButtonBase button = new ListTabBarButton();
		tab.setButton(button);
		tab.setWidget(scrollPanel);
		return tab;
	}

	/**
	 * @param mapWidget2
	 * @return
	 */
	private Tab prepareMapTab(MapWidget mapWidget2) {
		Tab tab = new Tab();

		TabBarButtonBase button = new MapTabBarButton();
		tab.setButton(button);
		tab.setWidget(mapWidget2);
		return tab;
	}

	/**
	 * Setup the List of Bike Trains.
	 * 
	 * This is responsible for responding to the changes of selection performed
	 * on the list tab. This should probably be moved out to the activity.
	 * 
	 * @return
	 */
	private CellList<Route> prepareList() {
		CellList<Route> cellList = new CellList<Route>(new RouteCell());
		cellList.addCellSelectedHandler(new CellSelectedHandler() {

			// TODO: Move out to the activity
			@Override
			public void onCellSelected(CellSelectedEvent event) {
				RouteContainer rc = RouteContainerFactory.getRouteContainer();
				// Turn off any highlighted route
				BikeTrainRoute previouslySelectedRoute = (BikeTrainRoute) rc
						.getSelectedRoute();
				if (previouslySelectedRoute != null) {
					previouslySelectedRoute.toggleHighlight();
				}

				// Turn on the selected route
				Route route = routes.get(event.getIndex());
				BikeTrainRoute bikeTrainRoute = (BikeTrainRoute) route;

				bikeTrainRoute.toggleHighlight();

				// Make the announcement (which might be able to handle the
				// other tasks too)
				RouteContainerFactory.getRouteContainer().setSelectedRoute(
						route);
			}

		});
		return cellList;
	}

	/**
	 * Setup the Map along with resize registration.
	 * 
	 * The mapWidget comes out of this.
	 */
	private MapWidget prepareMap() {
		MapWidget mapWidget;

		LatLng atlanta = LatLng.newInstance(33.757787d, -84.359741d);
		MapOptions opts = MapOptions.newInstance();
		opts.setZoom(currentZoomLevel);
		opts.setCenter(atlanta);
		opts.setMapTypeId(MapTypeId.ROADMAP);
		opts.setScaleControl(true);

		mapWidget = new MapWidget(opts);
		// Only the height has to be spec'd
		mapWidget.setSize("100%", "100%");
		mapWidget.setHeight(ScreenSize.getHeight() - 80 + "px");
		ScreenSize.addRegistration(mapWidget);
		return mapWidget;
	}

	/**
	 * Adjusts bounds, adds markers and puts it on the mapWidget that is part of
	 * this view.
	 * 
	 * Passed as a generic Route, but understood to be a BikeTrainRoute.
	 * 
	 * @see com.jettmarks.routes.client.ui.EventView#add(com.jettmarks.routes.client
	 *      .bean.Route)
	 */
	@Override
	public void add(Route route) {
		BikeTrainRoute bikeRoute = (BikeTrainRoute) route;
		// Take care of the map
		LatLngBounds routeBounds = bikeRoute.getBounds();
		if (mapBounds == null) {
			mapBounds = routeBounds;
		} else {
			mapBounds.extend(routeBounds.getNorthEast());
			mapBounds.extend(routeBounds.getSouthWest());
		}
		routes.add(bikeRoute);
		addBeginEndMarkers(route, mapWidget);
		bikeRoute.setMap(mapWidget);
		bikeRoute.highlight(false);
	}

	/**
	 * Called after last route has been loaded.
	 */
	public void renderList() {
		listWidget.render(routes);
		scrollPanel.refresh();
	}

	/**
	 * Hides details of figuring out where to put the markers on the route.
	 * 
	 * @param route
	 * @param mapWidget2
	 */
	private void addBeginEndMarkers(Route route, MapWidget mapWidget2) {
		if (route.getPoints() == null)
			return;
		int pointCount = route.getPoints().length;
		if (pointCount == 0)
			return;

		LatLng beginLatLng = route.getPoints()[0];
		LatLng endLatLng = route.getPoints()[pointCount - 1];
		Marker beginMarker = MarkerFactory.getInstance(MarkerType.START_MARKER,
				beginLatLng);
		Marker endMarker = MarkerFactory.getInstance(MarkerType.END_MARKER,
				endLatLng);
		beginMarker.setMap(mapWidget2);
		endMarker.setMap(mapWidget2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jettmarks.routes.client.ui.EventView#resize()
	 */
	@Override
	public void resize() {
		// See if this works here
		renderList();
		if (mapBounds == null) {
			return;
		}
		mapWidget.fitBounds(mapBounds);
	}

	/**
	 * @return the dispGroupName
	 */
	public String getDisplayGroupName() {
		return displayGroupName;
	}

	/**
	 * @param dispGroupName
	 *            the dispGroupName to set
	 */
	public void setDisplayGroupName(String dispGroupName) {
		this.displayGroupName = dispGroupName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
		title.setText(description);
	}

	/**
	 * @see com.jettmarks.routes.client.ui.EventView#getForwardbutton()
	 */
	@Override
	public HasTapHandlers getForwardbutton() {
		return headerForwardButton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jettmarks.routes.client.ui.EventView#enableForwardButton()
	 */
	@Override
	public void enableForwardButton(boolean isEnabled) {
		headerForwardButton.setVisible(isEnabled);
	}

	/**
	 * Responds to external activity telling us a route has been selected.
	 * 
	 * 
	 * @see com.jettmarks.routes.client.ui.EventView#selectRoute(com.jettmarks.routes.client.bean.Route)
	 */
	@Override
	public void selectRoute(Route route) {
		// headerForwardButton.setVisible(route != null);
		int index = 0;
		for (Route r : routes) {
			String title = r.getDisplayName();
			boolean selected = (route != null)
					&& title.equals(route.getDisplayName());
			listWidget.setSelectedIndex(index++, selected);
		}
	}

	/**
	 * Removes all routes from the map.
	 * 
	 * @see com.jettmarks.routes.client.ui.EventView#clearMap()
	 */
	@Override
	public void clearMap() {
		for (Route r : routes) {
			r.setMap(null);
		}
		routes.clear();
		mapBounds = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jettmarks.routes.client.ui.EventView#showMapTab()
	 */
	@Override
	public void showMapTab() {
		tabPanel.setSelectedChild(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jettmarks.routes.client.ui.EventView#showListTab()
	 */
	@Override
	public void showListTab() {
		tabPanel.setSelectedChild(1);
	}
}
