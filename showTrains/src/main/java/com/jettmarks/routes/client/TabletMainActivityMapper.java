package com.jettmarks.routes.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.jettmarks.routes.client.activities.EmptyMapActivity;
import com.jettmarks.routes.client.activities.RouteDetailsActivity;
import com.jettmarks.routes.client.activities.RouteSelectedActivity;
import com.jettmarks.routes.client.activities.showGroup.ShowGroupActivity;
import com.jettmarks.routes.client.activities.showGroup.ShowGroupPlace;
import com.jettmarks.routes.client.forms.FormsActivity;
import com.jettmarks.routes.client.forms.FormsPlace;
import com.jettmarks.routes.client.place.EventPlace;
import com.jettmarks.routes.client.place.EventSelectionPlace;
import com.jettmarks.routes.client.place.HomePlace;
import com.jettmarks.routes.client.place.RouteDetailsPlace;
import com.jettmarks.routes.client.place.RouteSelectedPlace;

public class TabletMainActivityMapper implements ActivityMapper {

	private Place lastPlace;

	private final ClientFactory clientFactory;

	public TabletMainActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public Activity getActivity(Place place) {
		Activity activity = getActivity(lastPlace, place);
		lastPlace = place;
		return activity;

	}

	private Activity getActivity(Place lastPlace, Place newPlace) {
		if (newPlace instanceof EventSelectionPlace) {
			return new EmptyMapActivity(clientFactory);
			// return new DisplayGroupListActivity(clientFactory);
		}

		if (newPlace instanceof HomePlace) {
			return new FormsActivity(newPlace, clientFactory);
			// return getAboutActivity();
		}

		if (newPlace instanceof EventPlace) {
			return new ShowGroupActivity(newPlace, clientFactory);
		}

		if (newPlace instanceof ShowGroupPlace) {
			return new ShowGroupActivity(newPlace, clientFactory);
		}

		if (newPlace instanceof FormsPlace) {
			return new FormsActivity(newPlace, clientFactory);
		}

		if (newPlace instanceof RouteDetailsPlace) {
			RouteDetailsPlace place = (RouteDetailsPlace) newPlace;
			return new RouteDetailsActivity(place, clientFactory);
		}

		if (newPlace instanceof RouteSelectedPlace) {
			RouteSelectedPlace place = (RouteSelectedPlace) newPlace;
			return new RouteSelectedActivity(place, clientFactory);
		}

		//
		// if (newPlace instanceof AnimationSlidePlace || newPlace instanceof
		// AnimationSlideUpPlace || newPlace instanceof AnimationDissolvePlace
		// ||
		// newPlace instanceof AnimationFadePlace
		// || newPlace instanceof AnimationFlipPlace || newPlace instanceof
		// AnimationPopPlace || newPlace instanceof AnimationSwapPlace ||
		// newPlace
		// instanceof AnimationCubePlace) {
		// return new AnimationDoneActivity(clientFactory);
		// }

		return null;
	}

}
