package com.jettmarks.routes.client;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationMapper;
import com.jettmarks.routes.client.place.EventPlace;
import com.jettmarks.routes.client.place.EventSelectionPlace;
import com.jettmarks.routes.client.place.RouteDetailsPlace;

public class TabletMainAnimationMapper implements AnimationMapper {
	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {

		if (oldPlace instanceof RouteDetailsPlace
				|| (oldPlace instanceof EventPlace && newPlace instanceof EventSelectionPlace)) {
			return Animation.SLIDE_REVERSE;
		} else {
			return Animation.SLIDE;
		}
	}
}
