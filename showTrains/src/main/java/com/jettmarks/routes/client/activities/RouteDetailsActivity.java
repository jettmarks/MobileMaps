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
package com.jettmarks.routes.client.activities;

import java.util.Date;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.DetailActivity;
import com.jettmarks.routes.client.bean.BikeTrainDTO;
import com.jettmarks.routes.client.bean.BikeTrainRoute;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.place.EventPlace;
import com.jettmarks.routes.client.place.HomePlace;
import com.jettmarks.routes.client.place.RouteDetailsPlace;
import com.jettmarks.routes.client.rep.RouteContainer;
import com.jettmarks.routes.client.rep.RouteContainerFactory;
import com.jettmarks.routes.client.ui.RouteDetailsView;

/**
 * Description.
 * 
 * @author jett
 */
public class RouteDetailsActivity extends DetailActivity implements Activity {
    private ClientFactory clientFactory = null;

    private BikeTrainRoute bikeTrainRoute = null;

    private String headerText = null;

    private RouteDetailsView view;

    /**
     * @param detailView
     * @param eventId
     */
    public RouteDetailsActivity(Place newPlace, ClientFactory cf) {
	super(cf.getRouteDetailsView(), "");
	clientFactory = cf;

	if (newPlace instanceof RouteDetailsPlace) {
	    RouteDetailsPlace place = (RouteDetailsPlace) newPlace;
	    view = clientFactory.getRouteDetailsView();
	    bikeTrainRoute = place.getRoute();

	    RouteContainerFactory.getRouteContainer().setDetailsActivity(this);

	    addHandlerRegistration(view.getBackbutton().addTapHandler(
		    new ReturnToEventTapHandler()));
	    addHandlerRegistration(view.getHeaderTapHandlers().addClickHandler(
		    new ReturnToEventClickHandler()));
	}
    }

    /**
     * @see com.google.gwt.activity.shared.Activity#mayStop()
     */
    @Override
    public String mayStop() {
	// TODO Auto-generated method stub
	return null;
    }

    /**
     * @see com.google.gwt.activity.shared.Activity#onCancel()
     */
    @Override
    public void onCancel() {
	// TODO Auto-generated method stub

    }

    /**
     * @see com.google.gwt.activity.shared.Activity#onStop()
     */
    @Override
    public void onStop() {
	cancelAllHandlerRegistrations();
    }

    /**
     * @see com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client
     *      .ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
     */
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	setViewDetails(bikeTrainRoute);
	setNavHandlers(view);
	panel.setWidget(view);
    }

    /**
     * Fills the form with details from the bikeTrainRoute.
     * 
     * @param bikeTrainRoute
     */
    public void setViewDetails(BikeTrainRoute bikeTrainRoute) {
	this.bikeTrainRoute = bikeTrainRoute;
	if (bikeTrainRoute == null) {
	    headerText = "Select a Route";
	} else {
	    headerText = bikeTrainRoute.getDisplayName();
	}
	view.getHeader().setText(headerText);

	// Populate View with the BikeTrain
	BikeTrainDTO bikeTrain = bikeTrainRoute.getBikeTrain();
	view.setArrival(bikeTrain.getArrivalTime());
	view.setDeparture(bikeTrain.getDepartureTime());
	view.setLeaderName(bikeTrain.getLeaderName());
	RouteContainer rc = RouteContainerFactory.getRouteContainer();
	Date currentTime = new Date();
	Date yesterday = new Date(currentTime.getTime() - 86400000);
	DisplayGroupDTO currentDisplayGroup = rc.getCurrentDisplayGroup();

	// Based on date, decide whether to display contact info
	if (currentDisplayGroup == null
		|| currentDisplayGroup.getEventDate() == null
		|| currentDisplayGroup.getEventDate().after(yesterday)) {
	    view.setLeaderEmail(bikeTrain.getLeaderEmail());
	    view.setLeaderPhone(bikeTrain.getLeaderPhone());
	} else {
	    view.setLeaderEmail("private");
	    view.setLeaderPhone("private");
	}
	view.setNotes(bikeTrain.getNotes());
	view.setRouteName(bikeTrain.getRouteName());

	// Populate View with the BikeTrainRoute
	view.setDisplayName(bikeTrainRoute.getDisplayName());
    }

    /**
     * @param view
     */
    protected void setNavHandlers(RouteDetailsView view) {
	addHandlerRegistration(view.getHomeButton().addTapHandler(
		new TapHandler() {

		    @Override
		    public void onTap(TapEvent event) {
			clientFactory.getPlaceController()
				.goTo(new HomePlace());
		    }

		}));

    }

    /**
     * Implementation of TapHandler that returns user back to the enclosing
     * Event.
     * 
     * @author jett
     */
    public class ReturnToEventTapHandler implements TapHandler {

	/**
	 * @see com.googlecode.mgwt.dom.client.event.tap.TapHandler#onTap(com.googlecode
	 *      .mgwt.dom.client.event.tap.TapEvent)
	 */
	@Override
	public void onTap(TapEvent event) {
	    DisplayGroupDTO displayGroup = RouteContainerFactory
		    .getRouteContainer().getCurrentDisplayGroup();
	    clientFactory.getPlaceController().goTo(
		    new EventPlace(displayGroup));
	}
    }

    /**
     * Implementation of ClickHandler that returns user back to the enclosing
     * Event.
     * 
     * @author jett
     */
    public class ReturnToEventClickHandler implements ClickHandler {

	/**
	 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt
	 *      .event.dom.client.ClickEvent)
	 */
	@Override
	public void onClick(ClickEvent event) {
	    DisplayGroupDTO displayGroup = RouteContainerFactory
		    .getRouteContainer().getCurrentDisplayGroup();
	    clientFactory.getPlaceController().goTo(
		    new EventPlace(displayGroup));
	}

    }

}
