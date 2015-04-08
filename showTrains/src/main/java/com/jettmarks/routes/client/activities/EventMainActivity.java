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
 * Created Sep 13, 2014
 */
package com.jettmarks.routes.client.activities;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.bean.Route;

/**
 * Description.
 * 
 * @author jett
 */
public class EventMainActivity extends EventActivity implements Activity {

	/**
	 * @param newPlace
	 * @param clientFactory
	 */
	public EventMainActivity(Place newPlace, ClientFactory clientFactory) {
		super(clientFactory.getEventMapView());
		super.setupInstance(newPlace, clientFactory);
	}

	/**
	 * @see com.jettmarks.routes.client.activities.EventActivity#setSelectedRoute(java.lang.Integer,
	 *      com.jettmarks.routes.client.bean.Route)
	 */
	@Override
	public void setSelectedRoute(Integer newIndex, Route selectedRoute) {
		super.setSelectedRoute(newIndex, selectedRoute);
		if (selectedRoute != null) {
			// view.getHeader().setText("View " +
			// selectedRoute.getDisplayName());
			view.getHeader().setText(selectedRoute.getDisplayName());
		}
	}

}
