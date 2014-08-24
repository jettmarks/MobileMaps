package com.jettmarks.routes.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.jettmarks.routes.client.activities.AboutActivity;
import com.jettmarks.routes.client.activities.DisplayGroupListActivity;
import com.jettmarks.routes.client.activities.GetInvolvedActivity;
import com.jettmarks.routes.client.activities.HomeActivity;
import com.jettmarks.routes.client.activities.RouteDetailsActivity;
import com.jettmarks.routes.client.activities.showGroup.ShowGroupActivity;
import com.jettmarks.routes.client.activities.showGroup.ShowGroupPlace;
import com.jettmarks.routes.client.forms.FormsActivity;
import com.jettmarks.routes.client.forms.FormsPlace;
import com.jettmarks.routes.client.place.AboutPlace;
import com.jettmarks.routes.client.place.EventPlace;
import com.jettmarks.routes.client.place.EventSelectionPlace;
import com.jettmarks.routes.client.place.GetInvolvedPlace;
import com.jettmarks.routes.client.place.HomePlace;
import com.jettmarks.routes.client.place.RouteDetailsPlace;

/**
 * 
 */
public class PhoneActivityMapper implements ActivityMapper {

    private final ClientFactory clientFactory;

    public PhoneActivityMapper(ClientFactory clientFactory) {
	this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
	if (place instanceof HomePlace) {
	    return new HomeActivity(clientFactory);
	}

	if (place instanceof AboutPlace) {
	    return new AboutActivity(clientFactory);
	}

	if (place instanceof GetInvolvedPlace) {
	    return new GetInvolvedActivity(clientFactory);
	}

	if (place instanceof EventSelectionPlace) {
	    return new DisplayGroupListActivity(clientFactory);
	}

	if (place instanceof ShowGroupPlace) {
	    return new ShowGroupActivity(place, clientFactory);
	}

	if (place instanceof FormsPlace) {
	    return new FormsActivity(place, clientFactory);
	}

	if (place instanceof EventPlace) {
	    return new ShowGroupActivity(place, clientFactory);
	}

	if (place instanceof RouteDetailsPlace) {
	    return new RouteDetailsActivity(place, clientFactory);
	}

	return null;
    }
}
