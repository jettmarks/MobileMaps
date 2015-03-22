package com.jettmarks.routes.client;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.AnimationMapper;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.jettmarks.routes.client.place.ConductorPlace;
import com.jettmarks.routes.client.place.EventPlace;
import com.jettmarks.routes.client.place.EventSelectionPlace;
import com.jettmarks.routes.client.place.FindRoutePlace;
import com.jettmarks.routes.client.place.GetInvolvedPlace;
import com.jettmarks.routes.client.place.HomePlace;
import com.jettmarks.routes.client.place.ResourcesPlace;
import com.jettmarks.routes.client.place.RouteDetailsPlace;

public class TabletMainAnimationMapper implements AnimationMapper {
	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {
		if (oldPlace instanceof RouteDetailsPlace
				|| (oldPlace instanceof EventPlace && newPlace instanceof EventSelectionPlace)) {
			return Animations.SLIDE_REVERSE;
		}
		if (newPlace instanceof HomePlace) {
			return Animations.SLIDE_REVERSE;
		}

		// #1
		if (oldPlace instanceof EventSelectionPlace
				&& newPlace instanceof FindRoutePlace) {
			return Animations.SLIDE_REVERSE;
		}

		// #2
		if (oldPlace instanceof EventPlace
				&& newPlace instanceof FindRoutePlace) {
			return Animations.SLIDE_REVERSE;
		}

		// #3
		if (oldPlace instanceof ConductorPlace
				&& newPlace instanceof GetInvolvedPlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof EventPlace
				&& newPlace instanceof ConductorPlace) {
			return Animations.SLIDE_REVERSE;
		}

		// #4
		if (oldPlace instanceof ConductorPlace
				&& newPlace instanceof ResourcesPlace) {
			return Animations.SLIDE_REVERSE;
		}
		return Animations.SLIDE;
	}
}
