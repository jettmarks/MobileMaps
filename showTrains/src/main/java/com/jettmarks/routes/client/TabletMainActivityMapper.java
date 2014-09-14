package com.jettmarks.routes.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.ui.client.MGWT;
import com.jettmarks.routes.client.activities.AboutActivity;
import com.jettmarks.routes.client.activities.ConductorActivity;
import com.jettmarks.routes.client.activities.EmptyMapActivity;
import com.jettmarks.routes.client.activities.EventMainActivity;
import com.jettmarks.routes.client.activities.EventSelectionActivity;
import com.jettmarks.routes.client.activities.FindRouteActivity;
import com.jettmarks.routes.client.activities.GetInvolvedActivity;
import com.jettmarks.routes.client.activities.HomeActivity;
import com.jettmarks.routes.client.activities.ResourcesActivity;
import com.jettmarks.routes.client.activities.RouteDetailsActivity;
import com.jettmarks.routes.client.forms.FormsActivity;
import com.jettmarks.routes.client.forms.FormsPlace;
import com.jettmarks.routes.client.place.AboutPlace;
import com.jettmarks.routes.client.place.ConductorPlace;
import com.jettmarks.routes.client.place.EventPlace;
import com.jettmarks.routes.client.place.EventSelectionPlace;
import com.jettmarks.routes.client.place.FindRoutePlace;
import com.jettmarks.routes.client.place.GetInvolvedPlace;
import com.jettmarks.routes.client.place.HomePlace;
import com.jettmarks.routes.client.place.ResourcesPlace;
import com.jettmarks.routes.client.place.RouteDetailsPlace;

public class TabletMainActivityMapper implements ActivityMapper {

    private Place lastPlace;

    private final ClientFactory clientFactory;

    private EventSelectionActivity eventSelectionActivity;

    public TabletMainActivityMapper(ClientFactory clientFactory) {
	this.clientFactory = clientFactory;

    }

    @Override
    public Activity getActivity(Place place) {
	Activity activity = getActivity(lastPlace, place);
	lastPlace = place;
	return activity;
    }

    private Activity getActivity(Place lastPlace, Place newPlace) {
	if (newPlace instanceof EventSelectionPlace) {
	    switch (MGWT.getOrientation()) {
	    case PORTRAIT:
		return getEventSelectionActivity();
	    case LANDSCAPE:
		return new EmptyMapActivity(clientFactory);
	    }
	}

	if (newPlace instanceof HomePlace) {
	    return new HomeActivity(clientFactory);
	}

	if (newPlace instanceof AboutPlace) {
	    return new AboutActivity(clientFactory);
	}

	if (newPlace instanceof EventPlace) {
	    return new EventMainActivity(newPlace, clientFactory);
	    // return new ShowGroupActivity(newPlace, clientFactory);
	}

	if (newPlace instanceof ConductorPlace) {
	    return new ConductorActivity(clientFactory);
	}

	if (newPlace instanceof GetInvolvedPlace) {
	    return new GetInvolvedActivity(clientFactory);
	}

	if (newPlace instanceof ResourcesPlace) {
	    return new ResourcesActivity(clientFactory);
	}

	if (newPlace instanceof FindRoutePlace) {
	    return new FindRouteActivity(clientFactory);
	}

	if (newPlace instanceof FormsPlace) {
	    return new FormsActivity(newPlace, clientFactory);
	}

	if (newPlace instanceof RouteDetailsPlace) {
	    RouteDetailsPlace place = (RouteDetailsPlace) newPlace;
	    return new RouteDetailsActivity(place, clientFactory);
	}

	return null;
    }

    private Activity getEventSelectionActivity() {
	if (eventSelectionActivity == null) {
	    eventSelectionActivity = new EventSelectionActivity(clientFactory);
	}
	return eventSelectionActivity;
    }

}