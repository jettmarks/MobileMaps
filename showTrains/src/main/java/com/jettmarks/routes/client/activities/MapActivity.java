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
package com.jettmarks.routes.client.activities;

import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.DetailActivity;
import com.jettmarks.routes.client.DetailView;
import com.jettmarks.routes.client.bean.BikeTrainRoute;
import com.jettmarks.routes.client.ui.EventView;

/**
 * Handles Activities of the Map including progress and turning each of the
 * routes into something we can view.
 * 
 * @author jett
 * @deprecated - functionality pulled up into EventActivity and subclasses.
 */
public class MapActivity extends DetailActivity {

	private ClientFactory clientFactory = null;

	private BikeTrainRoute selectedRoute = null;

	/**
	 * @param view
	 * @param clientFactory
	 */
	EventView mapView = null;

	public MapActivity(DetailView view, ClientFactory cf) {
		super(view, "");
		clientFactory = cf;

		if (view instanceof EventView) {
			final EventView eventView = (EventView) view;
			mapView = eventView;
		}
	}

	/**
	 * Responsible for shutting down the button handlers registered here.
	 * 
	 * @see com.googlecode.mgwt.mvp.client.MGWTAbstractActivity#onStop()
	 */
	@Override
	public void onStop() {
		super.onStop();
		cancelAllHandlerRegistrations();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.mgwt.mvp.client.MGWTAbstractActivity#start(com.google.gwt
	 * .user.client.ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
	 */
	public void addRegistration(EventView eventView) {
		// Home Button
		// addHandlerRegistration(eventView.getHomeButton().addTapHandler(
		// new TapHandler() {
		//
		// @Override
		// public void onTap(TapEvent event) {
		// mapView.clearMap();
		// RouteContainerFactory.getRouteContainer()
		// .setSelectedRoute((Integer) null);
		// clientFactory.getPlaceController()
		// .goTo(new HomePlace());
		// }
		//
		// }));

		// addHandlerRegistration(eventView.getHeaderTapHandlers()
		// .addClickHandler(new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// selectedRoute = (BikeTrainRoute) RouteContainerFactory
		// .getRouteContainer().getSelectedRoute();
		// if (selectedRoute != null) {
		// clientFactory.getPlaceController().goTo(
		// new RouteDetailsPlace(selectedRoute));
		// } else {
		// AlertDialog noRouteSelectedAlert = new AlertDialog(
		// "No Train Selected",
		// "Choose a Bike Train to view");
		// noRouteSelectedAlert.show();
		// }
		// }
		// }));

		// Back Button
		// addHandlerRegistration(eventView.getBackbutton().addTapHandler(
		// new TapHandler() {
		//
		// @Override
		// public void onTap(TapEvent event) {
		// mapView.clearMap();
		// RouteContainerFactory.getRouteContainer()
		// .setSelectedRoute((Integer) null);
		// clientFactory.getPlaceController().goTo(
		// new EventSelectionPlace());
		// }
		//
		// }));

		// Forward Button
		// addHandlerRegistration(eventView.getForwardbutton().addTapHandler(
		// new TapHandler() {
		// @Override
		// public void onTap(TapEvent event) {
		// selectedRoute = (BikeTrainRoute) RouteContainerFactory
		// .getRouteContainer().getSelectedRoute();
		// if (selectedRoute != null) {
		// clientFactory.getPlaceController().goTo(
		// new RouteDetailsPlace(selectedRoute));
		// } else {
		// AlertDialog noRouteSelectedAlert = new AlertDialog(
		// "No Train Selected",
		// "Choose a Bike Train to view");
		// noRouteSelectedAlert.show();
		// }
		// }
		// }));
	}
}
