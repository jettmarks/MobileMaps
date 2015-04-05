package com.jettmarks.bikeTrains.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.jettmarks.bikeTrains.client.activities.AboutActivity;
import com.jettmarks.bikeTrains.client.activities.AboutPlace;
import com.jettmarks.bikeTrains.client.activities.HomeActivity;
import com.jettmarks.bikeTrains.client.activities.UIPlace;
import com.jettmarks.bikeTrains.client.activities.animation.AnimationPlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationCubePlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationDissolvePlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationDoneActivity;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationFadePlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationFlipPlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationPopPlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationSlidePlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationSlideUpPlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationSwapPlace;
import com.jettmarks.bikeTrains.client.activities.button.ButtonActivity;
import com.jettmarks.bikeTrains.client.activities.button.ButtonPlace;
import com.jettmarks.bikeTrains.client.activities.buttonbar.ButtonBarActivity;
import com.jettmarks.bikeTrains.client.activities.buttonbar.ButtonBarPlace;
import com.jettmarks.bikeTrains.client.activities.carousel.CarouselActivity;
import com.jettmarks.bikeTrains.client.activities.carousel.CarouselPlace;
import com.jettmarks.bikeTrains.client.activities.elements.ElementsActivity;
import com.jettmarks.bikeTrains.client.activities.elements.ElementsPlace;
import com.jettmarks.bikeTrains.client.activities.forms.FormsActivity;
import com.jettmarks.bikeTrains.client.activities.forms.FormsPlace;
import com.jettmarks.bikeTrains.client.activities.gcell.GroupedCellListActivity;
import com.jettmarks.bikeTrains.client.activities.gcell.GroupedCellListPlace;
import com.jettmarks.bikeTrains.client.activities.popup.PopupActivity;
import com.jettmarks.bikeTrains.client.activities.popup.PopupPlace;
import com.jettmarks.bikeTrains.client.activities.progressbar.ProgressBarActivity;
import com.jettmarks.bikeTrains.client.activities.progressbar.ProgressBarPlace;
import com.jettmarks.bikeTrains.client.activities.progressindicator.ProgressIndicatorActivity;
import com.jettmarks.bikeTrains.client.activities.progressindicator.ProgressIndicatorPlace;
import com.jettmarks.bikeTrains.client.activities.pulltorefresh.PullToRefreshActivity;
import com.jettmarks.bikeTrains.client.activities.pulltorefresh.PullToRefreshPlace;
import com.jettmarks.bikeTrains.client.activities.scrollwidget.ScrollWidgetActivity;
import com.jettmarks.bikeTrains.client.activities.scrollwidget.ScrollWidgetPlace;
import com.jettmarks.bikeTrains.client.activities.searchbox.SearchBoxActivity;
import com.jettmarks.bikeTrains.client.activities.searchbox.SearchBoxPlace;
import com.jettmarks.bikeTrains.client.activities.slider.SliderActivity;
import com.jettmarks.bikeTrains.client.activities.slider.SliderPlace;
import com.jettmarks.bikeTrains.client.activities.tabbar.TabBarActivity;
import com.jettmarks.bikeTrains.client.activities.tabbar.TabBarPlace;
import com.jettmarks.bikeTrains.client.places.HomePlace;

public class TabletMainActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	private Place lastPlace;

	public TabletMainActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public Activity getActivity(Place place) {
		Activity activity = getActivity(lastPlace, place);
		lastPlace = place;
		return activity;

	}

	private AboutActivity aboutActivity;

	private AboutActivity getAboutActivity() {
		if (aboutActivity == null) {
			aboutActivity = new AboutActivity(clientFactory);
		}

		return aboutActivity;
	}

	private HomeActivity homeActivity;

	private HomeActivity getHomeActivity() {
		if (homeActivity == null) {
			homeActivity = new HomeActivity(clientFactory);
		}
		return homeActivity;
	}

	private Activity getActivity(Place lastPlace, Place newPlace) {
		if (newPlace instanceof HomePlace) {
			return getHomeActivity();
		}

		if (newPlace instanceof AboutPlace) {
			return getAboutActivity();
		}

		if (newPlace instanceof UIPlace) {
			return getAboutActivity();
		}

		if (newPlace instanceof ScrollWidgetPlace) {
			return new ScrollWidgetActivity(clientFactory);
		}

		if (newPlace instanceof ElementsPlace) {
			return new ElementsActivity(clientFactory);
		}

		if (newPlace instanceof FormsPlace) {
			return new FormsActivity(clientFactory);
		}

		if (newPlace instanceof ButtonBarPlace) {
			return new ButtonBarActivity(clientFactory);
		}

		if (newPlace instanceof SearchBoxPlace) {
			return new SearchBoxActivity(clientFactory);
		}

		if (newPlace instanceof TabBarPlace) {
			return new TabBarActivity(clientFactory);
		}

		if (newPlace instanceof ButtonPlace) {
			return new ButtonActivity(clientFactory);
		}

		if (newPlace instanceof PopupPlace) {
			return new PopupActivity(clientFactory);
		}

		if (newPlace instanceof ProgressBarPlace) {
			return new ProgressBarActivity(clientFactory);
		}

		if (newPlace instanceof ProgressIndicatorPlace) {
			return new ProgressIndicatorActivity(clientFactory);
		}

		if (newPlace instanceof SliderPlace) {
			return new SliderActivity(clientFactory);
		}

		if (newPlace instanceof AnimationPlace) {
			return new AboutActivity(clientFactory);
		}

		if (newPlace instanceof PullToRefreshPlace) {
			return new PullToRefreshActivity(clientFactory);
		}

		if (newPlace instanceof CarouselPlace) {
			return new CarouselActivity(clientFactory);
		}

		if (newPlace instanceof GroupedCellListPlace) {
			return new GroupedCellListActivity(clientFactory);
		}

		if (newPlace instanceof AnimationSlidePlace
				|| newPlace instanceof AnimationSlideUpPlace
				|| newPlace instanceof AnimationDissolvePlace
				|| newPlace instanceof AnimationFadePlace
				|| newPlace instanceof AnimationFlipPlace
				|| newPlace instanceof AnimationPopPlace
				|| newPlace instanceof AnimationSwapPlace
				|| newPlace instanceof AnimationCubePlace) {
			return new AnimationDoneActivity(clientFactory);
		}

		return null;
	}

}
