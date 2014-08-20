package com.jettmarks.routes.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.jettmarks.routes.client.activities.DisplayGroupListActivity;
import com.jettmarks.routes.client.activities.EventSelectionActivity;
import com.jettmarks.routes.client.activities.showGroup.HomeActivity;
import com.jettmarks.routes.client.place.EventSelectionPlace;
import com.jettmarks.routes.client.place.HomePlace;

public class TabletNavActivityMapper implements ActivityMapper {

    private final ClientFactory clientFactory;

    private DisplayGroupListActivity displayGroupListActivity;

    private Activity eventSelectionActivity;

    private HomeActivity homeActivity;

    public TabletNavActivityMapper(ClientFactory clientFactory) {
	this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
	if (place instanceof HomePlace) {
	    return getHomeActivity();
	}

	if (place instanceof EventSelectionPlace) {
	    return getEventSelectionActivity();
	}
	return null;
    }

    /**
     * @return
     */
    private Activity getHomeActivity() {
	if (homeActivity == null) {
	    homeActivity = new HomeActivity(clientFactory);
	}
	return homeActivity;
    }

    private Activity getEventSelectionActivity() {
	if (eventSelectionActivity == null) {
	    eventSelectionActivity = new EventSelectionActivity(clientFactory);
	}
	return eventSelectionActivity;
    }
}
