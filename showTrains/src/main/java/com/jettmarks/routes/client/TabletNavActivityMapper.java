package com.jettmarks.routes.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.jettmarks.routes.client.activities.DisplayGroupListActivity;
import com.jettmarks.routes.client.activities.EventSelectionActivity;

public class TabletNavActivityMapper implements ActivityMapper
{

  private final ClientFactory clientFactory;

  private DisplayGroupListActivity displayGroupListActivity;

  private Activity eventSelectionActivity;

  public TabletNavActivityMapper(ClientFactory clientFactory)
  {
    this.clientFactory = clientFactory;
  }

  @Override
  public Activity getActivity(Place place)
  {
    // if (place instanceof HomePlace || place instanceof EventSelectionPlace)
    // {
    return getEventSelectionActivity();
    // }
    // return null;
  }

  private Activity getEventSelectionActivity()
  {
    if (eventSelectionActivity == null)
    {
      eventSelectionActivity = new EventSelectionActivity(clientFactory);
    }
    return eventSelectionActivity;
  }
}
