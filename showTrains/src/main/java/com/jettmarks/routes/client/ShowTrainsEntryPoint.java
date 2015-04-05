/**
 *   Copyright 2014 Jett Marks
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created Apr 20, 2014
 */
package com.jettmarks.routes.client;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.mvp.client.AnimationMapper;
import com.googlecode.mgwt.mvp.client.history.MGWTPlaceHistoryHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.util.SuperDevModeUtil;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;
import com.googlecode.mgwt.ui.client.widget.menu.overlay.OverlayMenu;
import com.jettmarks.routes.client.place.AboutPlace;
import com.jettmarks.routes.client.place.EventSelectionPlace;
import com.jettmarks.routes.client.place.HomePlace;
import com.jettmarks.routes.client.place.PlaceClassifier;

/**
 * Upon detection of tablet/desktop or phone, present either a PhoneDisplay or a
 * TabletDisplay.
 * 
 * @author Jett Marks
 */
public class ShowTrainsEntryPoint implements EntryPoint {

	private void start() {
		SuperDevModeUtil.showDevMode();

		// set viewport and other settings for mobile
		MGWT.applySettings(MGWTSettings.getAppSetting());

		final ClientFactory clientFactory = new ClientFactoryImpl();

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper = GWT
				.create(AppPlaceHistoryMapper.class);

		MGWTStyle.injectStyleSheet("bikeTrains/css/adjustment.css");
		MGWTStyle.injectStyleSheet("bikeTrains/css/workaround.css");

		if ((MGWT.getOsDetection().isTablet())
				|| MGWT.getOsDetection().isDesktop()) {
			createTabletDisplay(clientFactory);
		} else {
			createPhoneDisplay(clientFactory);
		}

		// PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
		// historyMapper);

		// From the ShowCase; not clear if I can take advantage of this yet
		AppHistoryObserver historyObserver = new AppHistoryObserver();
		MGWTPlaceHistoryHandler historyHandler = new MGWTPlaceHistoryHandler(
				historyMapper, historyObserver);

		historyHandler.register(clientFactory.getPlaceController(),
				clientFactory.getEventBus(), new HomePlace());

		historyHandler.handleCurrentHistory();
	}

	private void createPhoneDisplay(ClientFactory clientFactory) {
		AnimationWidget display = new AnimationWidget();

		PhoneActivityMapper appActivityMapper = new PhoneActivityMapper(
				clientFactory);

		PhoneAnimationMapper appAnimationMapper = new PhoneAnimationMapper();

		AnimatingActivityManager activityManager = new AnimatingActivityManager(
				appActivityMapper, appAnimationMapper,
				clientFactory.getEventBus());

		activityManager.setDisplay(display);

		RootPanel.get().add(display);

	}

	private void createTabletDisplay(ClientFactory clientFactory) {
		OverlayMenu overlayMenu = new OverlayMenu();
		// Nav Container
		AnimationWidget navDisplay = new AnimationWidget();
		ActivityMapper navActivityMapper = new TabletNavActivityMapper(
				clientFactory);
		AnimationMapper navAnimationMapper = new TabletNavAnimationMapper();

		AnimatingActivityManager navActivityManager = new AnimatingActivityManager(
				navActivityMapper, navAnimationMapper,
				clientFactory.getEventBus());
		navActivityManager.setDisplay(navDisplay);

		overlayMenu.setMaster(navDisplay);

		// SimplePanel navContainer = new SimplePanel();
		// navContainer.getElement().setId("nav");
		// navContainer.getElement().addClassName("landscapeonly");

		// final TabletPortraitOverlay tabletPortraitOverlay = new
		// TabletPortraitOverlay();
		//
		// new OrientationRegionHandler(navContainer, tabletPortraitOverlay,
		// navDisplay);
		// new MasterRegionHandler(clientFactory.getEventBus(), "nav",
		// tabletPortraitOverlay);

		// RootPanel.get().add(navContainer);

		// Main Container
		// SimplePanel mainContainer = new SimplePanel();
		// mainContainer.getElement().setId("main");
		AnimationWidget mainDisplay = new AnimationWidget();

		TabletMainActivityMapper tabletMainActivityMapper = new TabletMainActivityMapper(
				clientFactory);

		AnimationMapper tabletMainAnimationMapper = new TabletMainAnimationMapper();

		AnimatingActivityManager mainActivityManager = new AnimatingActivityManager(
				tabletMainActivityMapper, tabletMainAnimationMapper,
				clientFactory.getEventBus());

		mainActivityManager.setDisplay(mainDisplay);
		// mainContainer.setWidget(mainDisplay);

		overlayMenu.setDetail(mainDisplay);
		// RootPanel.get().add(mainContainer);
		RootPanel.get().add(overlayMenu);

		MGWT.addOrientationChangeHandler(new MyOrientationChangeHandler(
				clientFactory));
	}

	@Override
	public void onModuleLoad() {

		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void onUncaughtException(Throwable e) {
				// TODO put in your own meaningful handler
				Window.alert("uncaught: " + e.getMessage());
				e.printStackTrace();

			}
		});

		new Timer() {
			@Override
			public void run() {
				start();

			}
		}.schedule(1);

	}

	/**
	 * Description.
	 * 
	 * @author jett
	 */
	public class EventSelectionPlaceOrientationChange extends
			EventSelectionPlace {

	}

	public class MyOrientationChangeHandler implements OrientationChangeHandler {

		private ClientFactory clientFactory;

		public MyOrientationChangeHandler(ClientFactory cf) {
			this.clientFactory = cf;
		}

		@Override
		public void onOrientationChanged(OrientationChangeEvent event) {
			PlaceController pc = clientFactory.getPlaceController();
			Place currentPlace = pc.getWhere();
			// Window.alert("We are hitting this");
			switch (PlaceClassifier.getPlaceType(currentPlace)) {
			case EMPTY_MAP:
				Place place = new EventSelectionPlaceOrientationChange();
				clientFactory.getPlaceController().goTo(place);
				break;
			case EVENT:
			case NAV:
				break;
			default:
			}
			if (currentPlace instanceof AboutPlace)
				return;
		}

	}
}
