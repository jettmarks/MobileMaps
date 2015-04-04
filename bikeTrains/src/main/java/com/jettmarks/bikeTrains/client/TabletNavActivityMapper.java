package com.jettmarks.bikeTrains.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.jettmarks.bikeTrains.client.activities.AboutPlace;
import com.jettmarks.bikeTrains.client.activities.ShowCaseListActivity;
import com.jettmarks.bikeTrains.client.activities.UIActivity;
import com.jettmarks.bikeTrains.client.activities.UIPlace;
import com.jettmarks.bikeTrains.client.activities.animation.AnimationActivity;
import com.jettmarks.bikeTrains.client.activities.animation.AnimationPlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationCubePlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationDissolvePlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationFadePlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationFlipPlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationPopPlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationSlidePlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationSlideUpPlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationSwapPlace;
import com.jettmarks.bikeTrains.client.activities.button.ButtonPlace;
import com.jettmarks.bikeTrains.client.activities.buttonbar.ButtonBarPlace;
import com.jettmarks.bikeTrains.client.activities.carousel.CarouselPlace;
import com.jettmarks.bikeTrains.client.activities.elements.ElementsPlace;
import com.jettmarks.bikeTrains.client.activities.forms.FormsPlace;
import com.jettmarks.bikeTrains.client.activities.gcell.GroupedCellListPlace;
import com.jettmarks.bikeTrains.client.activities.popup.PopupPlace;
import com.jettmarks.bikeTrains.client.activities.progressbar.ProgressBarPlace;
import com.jettmarks.bikeTrains.client.activities.progressindicator.ProgressIndicatorPlace;
import com.jettmarks.bikeTrains.client.activities.pulltorefresh.PullToRefreshPlace;
import com.jettmarks.bikeTrains.client.activities.scrollwidget.ScrollWidgetPlace;
import com.jettmarks.bikeTrains.client.activities.searchbox.SearchBoxPlace;
import com.jettmarks.bikeTrains.client.activities.slider.SliderPlace;
import com.jettmarks.bikeTrains.client.activities.tabbar.TabBarPlace;
import com.jettmarks.bikeTrains.client.places.HomePlace;

public class TabletNavActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	public TabletNavActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	private UIActivity uiActivity;
	private ShowCaseListActivity showCaseListActivity;
	private AnimationActivity animationActivity;

	private Activity getUIActivity() {
		if (uiActivity == null) {
			uiActivity = new UIActivity(clientFactory);
		}
		return uiActivity;
	}

	private Activity getShowCaseListActivity() {
		if (showCaseListActivity == null) {
			showCaseListActivity = new ShowCaseListActivity(clientFactory);
		}
		return showCaseListActivity;
	}

	private Activity getAnimationActicity() {
		if (animationActivity == null) {
			animationActivity = new AnimationActivity(clientFactory);
		}
		return animationActivity;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace || place instanceof AboutPlace) {
			return getShowCaseListActivity();
		}

		if (place instanceof PullToRefreshPlace
				|| place instanceof GroupedCellListPlace
				|| place instanceof CarouselPlace || place instanceof UIPlace
				|| place instanceof ScrollWidgetPlace
				|| place instanceof ElementsPlace
				|| place instanceof FormsPlace
				|| place instanceof ButtonBarPlace
				|| place instanceof SearchBoxPlace
				|| place instanceof TabBarPlace || place instanceof ButtonPlace
				|| place instanceof PopupPlace
				|| place instanceof ProgressBarPlace
				|| place instanceof SliderPlace
				|| place instanceof ProgressIndicatorPlace) {
			return getUIActivity();
		}

		if (place instanceof AnimationPlace) {
			return getAnimationActicity();
		}

		if (place instanceof AnimationSlidePlace
				|| place instanceof AnimationSlideUpPlace
				|| place instanceof AnimationDissolvePlace
				|| place instanceof AnimationFadePlace
				|| place instanceof AnimationFlipPlace
				|| place instanceof AnimationPopPlace
				|| place instanceof AnimationSwapPlace
				|| place instanceof AnimationCubePlace) {
			return getAnimationActicity();
		}
		return new ShowCaseListActivity(clientFactory);
	}
}
