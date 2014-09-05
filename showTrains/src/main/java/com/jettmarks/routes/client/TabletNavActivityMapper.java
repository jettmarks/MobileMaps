package com.jettmarks.routes.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.jettmarks.routes.client.activities.EventSelectionActivity;

public class TabletNavActivityMapper implements ActivityMapper
{

  private final ClientFactory clientFactory;

  private Activity eventSelectionActivity;

  public TabletNavActivityMapper(ClientFactory clientFactory)
  {
    this.clientFactory = clientFactory;
  }

  @Override
  public Activity getActivity(Place place)
  {
    return getEventSelectionActivity();
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
