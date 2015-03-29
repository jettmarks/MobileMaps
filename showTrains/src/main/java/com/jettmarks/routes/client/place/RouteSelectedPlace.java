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
package com.jettmarks.routes.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.jettmarks.routes.client.bean.Route;

/**
 * When user has chosen a route -- either on the map or from a list -- we go to
 * this place to potentially:
 * <UL>
 * <LI>Show the name of the route selected.
 * <LI>Ask if they want to see details.
 * <LI>Provide other options and details so they know what they've selected and
 * how to navigate to details page.
 * </UL>
 * 
 * @author jett
 *
 */
public class RouteSelectedPlace extends Place {

	private Route selectedRoute;

	public RouteSelectedPlace(Route selectedRoute) {
		super();
		this.selectedRoute = selectedRoute;
	}

	/**
	 * @param token
	 */
	public RouteSelectedPlace(String token) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the selectedRoute
	 */
	public Route getSelectedRoute() {
		return selectedRoute;
	}

	/**
	 * @param selectedRoute
	 *            the selectedRoute to set
	 */
	public void setSelectedRoute(Route selectedRoute) {
		this.selectedRoute = selectedRoute;
	}

	public static class Tokenizer implements PlaceTokenizer<RouteSelectedPlace> {

		@Override
		public RouteSelectedPlace getPlace(String token) {
			return new RouteSelectedPlace(token);
		}

		@Override
		public String getToken(RouteSelectedPlace place) {
			return place.getSelectedRoute().getDisplayName();
		}

	}
}
