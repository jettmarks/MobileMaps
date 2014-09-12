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
 * Created Jul 11, 2014
 */
package com.jettmarks.routes.client.rep;

import java.util.ArrayList;
import java.util.List;

import com.google.code.p.gwtchismes.client.GWTCProgress;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.ui.client.dialog.AlertDialog;
import com.jettmarks.routes.client.bean.DisplayElementDTO;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.bean.Route;
import com.jettmarks.routes.client.bean.RouteRequest;
import com.jettmarks.routes.client.bean.RouteRequestRouteName;
import com.jettmarks.routes.client.ui.EventView;
import com.jettmarks.routes.client.util.RouteScheduledCommand;

/**
 * Public place to hold the routes for the application.
 * 
 * @author jett
 */
public class RouteContainerImpl implements RouteContainer {
    public enum RequestType {
	STRING, DISPLAY_ELEMENT, UNDETERMINED
    }

    private static RequestType requestType = RequestType.UNDETERMINED;

    private EventView mapView = null;

    private static RouteRequest currentRouteRequest = null;

    private static GWTCProgress gwtcProgress;

    private Route selectedRoute = null;

    private DisplayGroupDTO displayGroup = null;

    private boolean displayGroupHasChanged;

    private List<EventView> viewList = new ArrayList<EventView>();

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jettmarks.routes.client.ui.RouteContainer#addRoutes(com.jettmarks
     * .routes .client.bean.RouteRequestRouteName)
     */
    @Override
    public void addRoutes(RouteRequestRouteName routeRequestRouteName) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jettmarks.routes.client.ui.RouteContainer#addRoutes(com.jettmarks
     * .routes .client.bean.RouteRequest)
     */
    @Override
    public void addRoutes(RouteRequest routeRequest) {

	if (routeRequest == null || !routeRequest.hasNext()) {
	    gwtcProgress.hide();
	    AlertDialog noTrainsAlert = new AlertDialog(
		    "No Trains Found",
		    "No trains have been entered for this scheduled event. Try back later or submit your own Bike Train.");
	    noTrainsAlert.show();

	} else {

	    requestType = RequestType.DISPLAY_ELEMENT;
	    currentRouteRequest = routeRequest;
	    openProgressBar(routeRequest);
	    DisplayElementDTO firstRequest = (DisplayElementDTO) routeRequest
		    .next();
	    RouteContainer target = this;

	    Scheduler.get().scheduleDeferred(
		    new GetElementScheduledCommand(target, firstRequest));
	}

    }

    /**
     * This method allows drawing each of the routes as they come back from the
     * server; we add them to the view as each one comes in.
     * 
     * @see com.jettmarks.routes.client.ui.RouteContainer#put(java.lang.String,
     *      com.jettmarks.routes.client.bean.Route)
     */
    @Override
    public void put(String routeName, Route route) {
	for (EventView view : viewList) {
	    view.add(route);
	}
	// mapView.add(route);
    }

    /**
     * @param routeRequest
     */
    public void openProgressBar(RouteRequest routeRequest) {
	if (gwtcProgress == null) {
	    gwtcProgress = new GWTCProgress(16, GWTCProgress.SHOW_AS_DIALOG
		    | GWTCProgress.SHOW_TEXT | GWTCProgress.SHOW_NUMBERS);
	    gwtcProgress.setText("Routes loaded:");
	    RootPanel.get().add(gwtcProgress);
	}
	gwtcProgress.show();
	if (routeRequest != null) {
	    gwtcProgress.setProgress(routeRequest.getCompletedTasks(),
		    routeRequest.getTotalTasks());
	}
    }

    /**
     * As routes are brought back from the service, we build up the display on
     * the map, and once all requests have been handled, we can shut down the
     * progress bar and resize the map.
     * 
     * It would be good to farm out the resize stuff to a different
     * implementation in case this changes and the view doesn't have a resize.
     * 
     * @see com.jettmarks.routes.client.rep.RouteContainer#updateProgress()
     */
    @Override
    public void updateProgress() {
	// Not much to do if we load a single route at a time.
	if (currentRouteRequest == null) {
	    for (EventView view : viewList) {
		view.resize();
		// mapView.resize();
	    }
	    return;
	}

	if (!currentRouteRequest.hasNext()) {
	    for (EventView view : viewList) {
		view.resize();
		// mapView.resize();
	    }
	    gwtcProgress.hide();
	} else
	// ready to ask for next route
	{
	    // Display looks better when we make the asynch request after
	    // updating the progress bar
	    Object nextRoute = currentRouteRequest.next();
	    gwtcProgress.setProgress(currentRouteRequest.getCompletedTasks(),
		    currentRouteRequest.getTotalTasks());
	    switch (requestType) {
	    case DISPLAY_ELEMENT:
		Scheduler.get()
			.scheduleDeferred(
				new RouteScheduledCommand(
					(DisplayElementDTO) nextRoute));
		break;
	    case STRING:
		// routeReader.requestRoute((String)nextRoute,
		// ((RouteRequestRouteName)currentRouteRequest).getRouteSourceName(),
		// ((RouteRequestRouteName)currentRouteRequest).getTagList());
		// break;
	    default:
		Window.alert("Unknown Request Type");
		break;
	    }
	}

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jettmarks.routes.client.ui.RouteContainer#getSelectedRoute()
     */
    @Override
    public Route getSelectedRoute() {
	return selectedRoute;
    }

    @Override
    public void setSelectedRoute(Route route) {
	selectedRoute = route;
	// if (mapView != null)
	// {
	// mapView.enableForwardButton(selectedRoute != null);
	// }
	for (EventView view : viewList) {
	    view.selectRoute(selectedRoute);
	}
	// mapView.selectRoute(selectedRoute);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jettmarks.routes.client.ui.RouteContainer#getCurrentDisplayGroup()
     */
    @Override
    public DisplayGroupDTO getCurrentDisplayGroup() {
	return displayGroup;
    }

    /**
     * Tracks the current DisplayGroup in memory along with whether or not it
     * has been changed.
     * 
     * The change is used to manage the cache and to decide whether or not to
     * refresh the routes from the service.
     * 
     * @see com.jettmarks.routes.client.ui.RouteContainer#setCurrentDisplayGroup(com
     *      .jettmarks.routes.client.bean.DisplayGroupDTO)
     */
    @Override
    public void setCurrentDisplayGroup(DisplayGroupDTO displayGroup) {
	if (this.displayGroup == null) {
	    displayGroupHasChanged = true;
	} else {
	    displayGroupHasChanged = !(this.displayGroup.getDisplayName()
		    .equals(displayGroup.getDisplayName()));
	}
	if (this.displayGroup != null && displayGroupHasChanged) {
	    clearPreviousMap();
	}
	this.displayGroup = displayGroup;
    }

    /**
   * 
   */
    private void clearPreviousMap() {
	mapView.clearMap();
    }

    /**
     * Defers the loading of the first element until after the current queue
     * gets a chance to process.
     * 
     * @author jett
     */
    class GetElementScheduledCommand implements ScheduledCommand {
	RouteContainer target = null;

	DisplayElementDTO request = null;

	GetElementScheduledCommand(RouteContainer target2,
		DisplayElementDTO request) {
	    super();
	    this.target = target2;
	    this.request = request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.core.client.Scheduler.ScheduledCommand#execute()
	 */
	@Override
	public void execute() {
	    ServiceWrapper serviceWrapper = new ServiceWrapper(target);
	    serviceWrapper.requestElement((DisplayElementDTO) request);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jettmarks.routes.client.rep.RouteContainer#setView(com.jettmarks.
     * routes .client.ui.EventView)
     */
    @Override
    public void setView(EventView view) {
	mapView = view;
    }

    /**
     * @see com.jettmarks.routes.client.rep.RouteContainer#addView(com.jettmarks.routes.client.ui.EventView)
     */
    @Override
    public void addView(EventView view) {
	viewList.add(view);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jettmarks.routes.client.rep.RouteContainer#displayGroupHasChanged()
     */
    @Override
    public boolean displayGroupHasChanged() {
	return displayGroupHasChanged;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jettmarks.routes.client.rep.RouteContainer#getDisplayGroup(java.lang
     * .String)
     */
    @Override
    public DisplayGroupDTO getDisplayGroup(String displayGroupName) {
	return null;
    }

}
