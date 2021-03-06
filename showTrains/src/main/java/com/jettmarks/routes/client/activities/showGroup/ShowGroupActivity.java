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
package com.jettmarks.routes.client.activities.showGroup;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.DetailActivity;
import com.jettmarks.routes.client.activities.MapActivity;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.place.EventPlace;
import com.jettmarks.routes.client.place.HomePlace;
import com.jettmarks.routes.client.rep.RouteContainer;
import com.jettmarks.routes.client.rep.RouteContainerFactory;
import com.jettmarks.routes.client.rep.RouteContainerImpl;
import com.jettmarks.routes.client.rep.ServiceWrapper;
import com.jettmarks.routes.client.ui.EventView;

/**
 * Description.
 * 
 * @author jett
 * @deprecated = Subclasses of EventActivity are where it's at.
 */
public class ShowGroupActivity extends DetailActivity {

    private final ClientFactory clientFactory;

    private EventView view;

    private RouteContainer routeContainer;

    // Yes, unconventional that I'm using a second Activity
    private static MapActivity mapActivity = null;

    public ShowGroupActivity(Place newPlace, ClientFactory clientFactory) {
	super(clientFactory.getEventView(), "nav");
	EventPlace place = (EventPlace) newPlace;
	String description = place.getDescription();
	String displayGroupName = place.getDisplayGroupName();
	view = clientFactory.getEventMapView();
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

    /**
     * Responsible for removing the Handler Registrations.
     * 
     * @see com.googlecode.mgwt.mvp.client.MGWTAbstractActivity#onStop()
     */
    @Override
    public void onStop() {
	super.onStop();
	cancelAllHandlerRegistrations();
	mapActivity.onStop();
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

	    view.showMapTab();
	    mapActivity = new MapActivity(view, clientFactory);

	    // Kicks off reading the routes in the DisplayGroup under control
	    // of the RouteContainer
	    DisplayGroupDTO dispGroup = new DisplayGroupDTO();
	    dispGroup.setDisplayName(view.getDisplayGroupName());
	    ServiceWrapper serviceWrapper = new ServiceWrapper(rcImpl);
	    serviceWrapper.showRoutes(dispGroup);
	} else if (routeContainer.getSelectedRoute() != null) {
	    view.selectRoute(routeContainer.getSelectedRoute());
	}
	if (mapActivity != null) {
	    mapActivity.addRegistration(view);
	}
	panel.setWidget(view);
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
     * Callback response for saving a Display Group.
     * 
     * After saving, we go to the Home Place.
     * 
     * @author jett
     */
    public class SaveDisplayGroupCallback<T> implements AsyncCallback<Integer> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.
	 * Throwable )
	 */
	@Override
	public void onFailure(Throwable caught) {
	    // Not sure what to do here
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.
	 * Object)
	 */
	@Override
	public void onSuccess(Integer result) {
	    Window.alert("Saved Display Group as ID: " + result);
	    clientFactory.getPlaceController().goTo(new HomePlace());
	}

    }

}
