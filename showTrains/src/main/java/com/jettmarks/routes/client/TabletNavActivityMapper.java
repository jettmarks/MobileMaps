package com.jettmarks.routes.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.jettmarks.routes.client.activities.EventActivity;
import com.jettmarks.routes.client.activities.EventSelectionActivity;
import com.jettmarks.routes.client.place.EventPlace;

public class TabletNavActivityMapper implements ActivityMapper {

    private final ClientFactory clientFactory;

    private Activity eventSelectionActivity;

    private EventActivity eventActivity;

    public TabletNavActivityMapper(ClientFactory clientFactory) {
	this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
	if (place instanceof EventPlace) {
	    return getEventActivity(place);
	} else
	    return getEventSelectionActivity();
    }

    /**
     * @return
     */
    private Activity getEventActivity(Place place) {
	if (eventActivity == null) {
	    eventActivity = new EventActivity(place, clientFactory);
	}
	return eventActivity;
    }

    private Activity getEventSelectionActivity() {
	if (eventSelectionActivity == null) {
	    eventSelectionActivity = new EventSelectionActivity(clientFactory);
	}
	return eventSelectionActivity;
    }
}
