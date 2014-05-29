package com.jettmarks.routes.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.jettmarks.routes.client.activities.DisplayGroupListActivity;
import com.jettmarks.routes.client.forms.FormsPlace;
import com.jettmarks.routes.client.place.HomePlace;

public class TabletNavActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;
  private DisplayGroupListActivity displayGroupListActivity;

	public TabletNavActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace || place instanceof FormsPlace) {
			return getDisplayGroupListActivity();
		}
		return null;
	}

  /**
   * @return
   */
  private Activity getDisplayGroupListActivity()
  {
    if (displayGroupListActivity == null) {
      displayGroupListActivity = new DisplayGroupListActivity(clientFactory);
    }
    return displayGroupListActivity;
  }
}
