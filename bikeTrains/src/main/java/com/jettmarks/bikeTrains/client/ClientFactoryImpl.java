/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.jettmarks.bikeTrains.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.jettmarks.bikeTrains.client.activities.AboutView;
import com.jettmarks.bikeTrains.client.activities.AboutViewGwtImpl;
import com.jettmarks.bikeTrains.client.activities.UIView;
import com.jettmarks.bikeTrains.client.activities.UIViewImpl;
import com.jettmarks.bikeTrains.client.activities.animation.AnimationView;
import com.jettmarks.bikeTrains.client.activities.animation.AnimationViewGwtImpl;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationDoneView;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationDoneViewGwtImpl;
import com.jettmarks.bikeTrains.client.activities.button.ButtonView;
import com.jettmarks.bikeTrains.client.activities.button.ButtonViewGwtImpl;
import com.jettmarks.bikeTrains.client.activities.buttonbar.ButtonBarView;
import com.jettmarks.bikeTrains.client.activities.buttonbar.ButtonBarViewGwtImpl;
import com.jettmarks.bikeTrains.client.activities.carousel.CarouselView;
import com.jettmarks.bikeTrains.client.activities.carousel.CarouselViewGwtImpl;
import com.jettmarks.bikeTrains.client.activities.elements.ElementsView;
import com.jettmarks.bikeTrains.client.activities.elements.ElementsViewImpl;
import com.jettmarks.bikeTrains.client.activities.forms.FormsView;
import com.jettmarks.bikeTrains.client.activities.forms.FormsViewGwtImpl;
import com.jettmarks.bikeTrains.client.activities.gcell.GroupedCellListGwtImpl;
import com.jettmarks.bikeTrains.client.activities.gcell.GroupedCellListView;
import com.jettmarks.bikeTrains.client.activities.popup.PopupView;
import com.jettmarks.bikeTrains.client.activities.popup.PopupViewGwtImpl;
import com.jettmarks.bikeTrains.client.activities.progressbar.ProgressBarView;
import com.jettmarks.bikeTrains.client.activities.progressbar.ProgressBarViewImpl;
import com.jettmarks.bikeTrains.client.activities.progressindicator.ProgressIndicatorView;
import com.jettmarks.bikeTrains.client.activities.progressindicator.ProgressIndicatorViewImpl;
import com.jettmarks.bikeTrains.client.activities.pulltorefresh.PullToRefreshDisplay;
import com.jettmarks.bikeTrains.client.activities.pulltorefresh.PullToRefreshDisplayGwtImpl;
import com.jettmarks.bikeTrains.client.activities.scrollwidget.ScrollWidgetView;
import com.jettmarks.bikeTrains.client.activities.scrollwidget.ScrollWidgetViewImpl;
import com.jettmarks.bikeTrains.client.activities.searchbox.SearchBoxView;
import com.jettmarks.bikeTrains.client.activities.searchbox.SearchBoxViewGwtImpl;
import com.jettmarks.bikeTrains.client.activities.slider.SliderView;
import com.jettmarks.bikeTrains.client.activities.slider.SliderViewGwtImpl;
import com.jettmarks.bikeTrains.client.activities.tabbar.TabBarView;
import com.jettmarks.bikeTrains.client.activities.tabbar.TabBarViewGwtImpl;
import com.jettmarks.bikeTrains.client.ui.HomeView;
import com.jettmarks.bikeTrains.client.ui.HomeViewGwtImpl;
import com.jettmarks.bikeTrains.client.ui.RouteDetailsView;
import com.jettmarks.bikeTrains.client.ui.RouteDetailsViewGwtImpl;

/**
 * @author Daniel Kurka
 * 
 */
public class ClientFactoryImpl implements ClientFactory {

	private EventBus eventBus;
	private PlaceController placeController;
	private HomeView homeViewImpl;
	private UIView uiView;
	private AboutView aboutView;
	private AnimationView animationView;
	private AnimationDoneView animationDoneView;
	private ScrollWidgetView scrollWidgetView;
	private ElementsView elementsView;
	private ButtonBarViewGwtImpl footerPanelView;
	private SearchBoxViewGwtImpl searchBoxViewGwtImpl;
	private TabBarView tabBarView;
	private ButtonView buttonView;
	private PopupView popupView;
	private ProgressBarView progressBarView;

	private SliderView sliderView;
	private PullToRefreshDisplayGwtImpl pullToRefreshView;
	private ProgressIndicatorViewImpl progressIndicatorView;
	private FormsViewGwtImpl formsView;
	private CarouselView carouselView;
	private GroupedCellListGwtImpl groupedCellListView;
	private RouteDetailsView routeDetailsView;

	public ClientFactoryImpl() {
		eventBus = new SimpleEventBus();

		placeController = new PlaceController(eventBus);

		homeViewImpl = new HomeViewGwtImpl();
	}

	@Override
	public HomeView getHomeView() {
		if (homeViewImpl == null) {
			homeViewImpl = new HomeViewGwtImpl();
		}
		return homeViewImpl;
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public UIView getUIView() {
		if (uiView == null) {
			uiView = new UIViewImpl();
		}
		return uiView;
	}

	@Override
	public AboutView getAboutView() {
		if (aboutView == null) {
			aboutView = new AboutViewGwtImpl();
		}

		return aboutView;
	}

	@Override
	public AnimationView getAnimationView() {
		if (animationView == null) {
			animationView = new AnimationViewGwtImpl();
		}
		return animationView;
	}

	@Override
	public AnimationDoneView getAnimationDoneView() {
		if (animationDoneView == null) {
			animationDoneView = new AnimationDoneViewGwtImpl();
		}
		return animationDoneView;
	}

	@Override
	public ScrollWidgetView getScrollWidgetView() {
		if (scrollWidgetView == null) {
			scrollWidgetView = new ScrollWidgetViewImpl();
		}
		return scrollWidgetView;
	}

	@Override
	public ElementsView getElementsView() {
		if (elementsView == null) {
			elementsView = new ElementsViewImpl();
		}
		return elementsView;
	}

	@Override
	public ButtonBarView getButtonBarView() {
		if (footerPanelView == null) {
			footerPanelView = new ButtonBarViewGwtImpl();
		}
		return footerPanelView;
	}

	@Override
	public SearchBoxView getSearchBoxView() {
		if (searchBoxViewGwtImpl == null) {
			searchBoxViewGwtImpl = new SearchBoxViewGwtImpl();
		}
		return searchBoxViewGwtImpl;
	}

	@Override
	public TabBarView getTabBarView() {
		if (tabBarView == null) {
			tabBarView = new TabBarViewGwtImpl();
		}
		return tabBarView;
	}

	@Override
	public ButtonView getButtonView() {
		if (buttonView == null) {
			buttonView = new ButtonViewGwtImpl();
		}
		return buttonView;
	}

	@Override
	public PopupView getPopupView() {
		if (popupView == null) {
			popupView = new PopupViewGwtImpl();
		}
		return popupView;
	}

	@Override
	public ProgressBarView getProgressBarView() {
		if (progressBarView == null) {
			progressBarView = new ProgressBarViewImpl();
		}
		return progressBarView;
	}

	@Override
	public SliderView getSliderView() {
		if (sliderView == null) {
			sliderView = new SliderViewGwtImpl();
		}
		return sliderView;
	}

	@Override
	public PullToRefreshDisplay getPullToRefreshDisplay() {
		if (pullToRefreshView == null) {
			pullToRefreshView = new PullToRefreshDisplayGwtImpl();
		}
		return pullToRefreshView;
	}

	@Override
	public ProgressIndicatorView getProgressIndicatorView() {
		if (progressIndicatorView == null) {
			progressIndicatorView = new ProgressIndicatorViewImpl();
		}
		return progressIndicatorView;
	}

	@Override
	public FormsView getFormsView() {
		if (formsView == null) {
			formsView = new FormsViewGwtImpl();
		}
		return formsView;
	}

	@Override
	public CarouselView getCarouselHorizontalView() {
		if (carouselView == null) {
			carouselView = new CarouselViewGwtImpl();
		}
		return carouselView;
	}

	@Override
	public GroupedCellListView getGroupedCellListView() {
		if (groupedCellListView == null) {
			groupedCellListView = new GroupedCellListGwtImpl();
		}
		return groupedCellListView;
	}

	/**
	 * @see com.jettmarks.routes.client.ClientFactory#getRouteDetailsView()
	 */
	@Override
	public RouteDetailsView getRouteDetailsView() {
		if (routeDetailsView == null) {
			routeDetailsView = new RouteDetailsViewGwtImpl();
		}
		return routeDetailsView;
	}

}
