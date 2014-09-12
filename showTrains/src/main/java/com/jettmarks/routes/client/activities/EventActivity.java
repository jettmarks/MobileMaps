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
package com.jettmarks.routes.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.DetailActivity;
import com.jettmarks.routes.client.bean.BikeTrainRoute;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.bean.Route;
import com.jettmarks.routes.client.place.EventPlace;
import com.jettmarks.routes.client.rep.RouteContainer;
import com.jettmarks.routes.client.rep.RouteContainerFactory;
import com.jettmarks.routes.client.rep.RouteContainerImpl;
import com.jettmarks.routes.client.rep.ServiceWrapper;
import com.jettmarks.routes.client.ui.EventView;

public class EventActivity extends DetailActivity {

    private final ClientFactory clientFactory;

    private EventView view;

    private RouteContainer routeContainer;

    // Yes, unconventional that I'm using a second Activity
    private static MapActivity mapActivity = null;

    public EventActivity(Place newPlace, ClientFactory clientFactory) {
	super(clientFactory.getEventView(), "nav");
	EventPlace place = (EventPlace) newPlace;
	String description = place.getDescription();
	String displayGroupName = place.getDisplayGroupName();
	view = clientFactory.getEventListView();
	view.setDisplayGroupName(displayGroupName);
	view.setDescription(description);
	DisplayGroupDTO displayGroup = new DisplayGroupDTO();
	displayGroup.setDisplayName(displayGroupName);
	displayGroup.setDescription(description);
	displayGroup.setEventDate(place.getEventDate());
	routeContainer = RouteContainerFactory.getRouteContainer();
	routeContainer.addView(view);
	routeContainer.setCurrentDisplayGroup(displayGroup);
	this.clientFactory = clientFactory;
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

	    // Not applicable to non-tabbed view
	    // view.showMapTab();
	    mapActivity = new MapActivity(view, clientFactory);

	    beginReadingRoutesAsync(view.getDisplayGroupName());
	} else if (routeContainer.getSelectedRoute() != null) {
	    view.selectRoute(routeContainer.getSelectedRoute());
	}
	view.setRouteSelectedHandler(new RouteListCellSelectedHandler());
	mapActivity.addRegistration(view);
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
        mapActivity.onStop();
    }

    public class RouteListCellSelectedHandler implements CellSelectedHandler {
    
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
//		Route route = routes.get(event.getIndex());
		Route route = view.getRoute(event.getIndex());
		BikeTrainRoute bikeTrainRoute = (BikeTrainRoute) route;

		bikeTrainRoute.toggleHighlight();

		// Make the announcement (which might be able to handle the
		// other tasks too)
		RouteContainerFactory.getRouteContainer().setSelectedRoute(
			route);
        }
    
    }

}
