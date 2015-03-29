/**
 *   Copyright 2015 Jett Marks
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
 * Created Mar 28, 2015
 */
package com.jettmarks.routes.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.place.RouteSelectedPlace;
import com.jettmarks.routes.client.ui.RouteSelectedView;

/**
 * Responds to a user having selected a particular route.
 *
 * @author jett
 *
 */
public class RouteSelectedActivity extends MGWTAbstractActivity {
	private ClientFactory clientFactory;
	private RouteSelectedPlace place;

	public RouteSelectedActivity(Place newPlace, ClientFactory cf) {
		place = (RouteSelectedPlace) newPlace;
		this.clientFactory = cf;
	}

	/**
	 * Popup a panel asking what the user wants to do with the selected route.
	 * 
	 * @see com.googlecode.mgwt.mvp.client.MGWTAbstractActivity#start(com.google.gwt.user.client.ui.AcceptsOneWidget,
	 *      com.google.gwt.event.shared.EventBus)
	 */
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		RouteSelectedView view = clientFactory.getRouteSelectedView();
		view.setRouteName(place.getSelectedRoute().getDisplayName());
		panel.setWidget(view);
	}
}
