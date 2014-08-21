package com.jettmarks.routes.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.jettmarks.routes.client.activities.AboutActivity;
import com.jettmarks.routes.client.activities.EmptyMapActivity;
import com.jettmarks.routes.client.activities.GetInvolvedActivity;
import com.jettmarks.routes.client.activities.HomeActivity;
import com.jettmarks.routes.client.activities.RouteDetailsActivity;
import com.jettmarks.routes.client.activities.showGroup.ShowGroupActivity;
import com.jettmarks.routes.client.activities.showGroup.ShowGroupPlace;
import com.jettmarks.routes.client.forms.FormsActivity;
import com.jettmarks.routes.client.forms.FormsPlace;
import com.jettmarks.routes.client.place.AboutPlace;
import com.jettmarks.routes.client.place.EventPlace;
import com.jettmarks.routes.client.place.EventSelectionPlace;
import com.jettmarks.routes.client.place.GetInvolvedPlace;
import com.jettmarks.routes.client.place.HomePlace;
import com.jettmarks.routes.client.place.RouteDetailsPlace;

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

	if (newPlace instanceof AboutPlace) {
	    return new AboutActivity(clientFactory);
	}

	if (newPlace instanceof GetInvolvedPlace) {
	    return new GetInvolvedActivity(clientFactory);
	}

	if (newPlace instanceof HomePlace) {
	    // return new FormsActivity(newPlace, clientFactory);
	    // return getAboutActivity();
	    return new HomeActivity(clientFactory);
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

	// if (newPlace instanceof AboutPlace) {
	// return getAboutActivity();
	// }
	//
	// if (newPlace instanceof UIPlace) {
	// return getAboutActivity();
	// }
	//
	// if (newPlace instanceof ScrollWidgetPlace) {
	// return new ScrollWidgetActivity(clientFactory);
	// }
	//
	// if (newPlace instanceof ElementsPlace) {
	// return new ElementsActivity(clientFactory);
	// }

	// if (newPlace instanceof ButtonBarPlace) {
	// return new ButtonBarActivity(clientFactory);
	// }
	//
	// if (newPlace instanceof SearchBoxPlace) {
	// return new SearchBoxActivity(clientFactory);
	// }
	//
	// if (newPlace instanceof TabBarPlace) {
	// return new TabBarActivity(clientFactory);
	// }
	//
	// if (newPlace instanceof ButtonPlace) {
	// return new ButtonActivity(clientFactory);
	// }
	//
	// if (newPlace instanceof PopupPlace) {
	// return new PopupActivity(clientFactory);
	// }
	//
	// if (newPlace instanceof ProgressBarPlace) {
	// return new ProgressBarActivity(clientFactory);
	// }
	//
	// if (newPlace instanceof ProgressIndicatorPlace) {
	// return new ProgressIndicatorActivity(clientFactory);
	// }
	//
	// if (newPlace instanceof SliderPlace) {
	// return new SliderActivity(clientFactory);
	// }
	//
	// if (newPlace instanceof AnimationPlace) {
	// return new AboutActivity(clientFactory);
	// }
	//
	// if (newPlace instanceof PullToRefreshPlace) {
	// return new PullToRefreshActivity(clientFactory);
	// }
	//
	// if (newPlace instanceof CarouselPlace) {
	// return new CarouselActivity(clientFactory);
	// }
	//
	// if (newPlace instanceof GroupedCellListPlace) {
	// return new GroupedCellListActivity(clientFactory);
	// }
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
