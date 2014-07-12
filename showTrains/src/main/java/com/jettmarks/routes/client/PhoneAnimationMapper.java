package com.jettmarks.routes.client;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationMapper;
import com.jettmarks.routes.client.place.RouteDetailsPlace;

/**
 * 
 */
public class PhoneAnimationMapper implements AnimationMapper
{

  @Override
  public Animation getAnimation(Place oldPlace, Place newPlace)
  {
    if (oldPlace instanceof RouteDetailsPlace)
    {
      return Animation.SLIDE_REVERSE;
    }
    else
    {
      return Animation.SLIDE;
    }
  }

}
