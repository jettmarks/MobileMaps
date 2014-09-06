package com.jettmarks.routes.client;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationMapper;

public class TabletNavAnimationMapper implements AnimationMapper {

    @Override
    public Animation getAnimation(Place oldPlace, Place newPlace) {
	return Animation.POP;
    }

}
