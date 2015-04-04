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
 * Created May 26, 2014
 */
package com.jettmarks.bikeTrains.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.jettmarks.bikeTrains.client.bean.BikeTrainRoute;

/**
 * Allows user to see the details of a selected Route.
 * 
 * Accepts the Display Element ID for the particular Bike Train details.
 * 
 * @author jett
 */
public class RouteDetailsPlace extends Place {
	private Integer id = null;
	private String displayName = null;

	private BikeTrainRoute route = null;

	/**
	 * @param routeName
	 */
	public RouteDetailsPlace(Integer id) {
		this.id = id;
	}

	/**
	 * @param routeName
	 */
	public RouteDetailsPlace(String routeName) {
		displayName = routeName;
	}

	public RouteDetailsPlace(BikeTrainRoute route) {
		this.id = route.getBikeTrain().getId();
		this.route = route;
	}

	/**
	 * @return the displayName
	 */
	String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return
	 */
	public BikeTrainRoute getRoute() {
		return route;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id + "";
	}

	public static class Tokenizer implements PlaceTokenizer<RouteDetailsPlace> {

		@Override
		public RouteDetailsPlace getPlace(String token) {
			String displayGroupName = token;
			return new RouteDetailsPlace(displayGroupName);
		}

		@Override
		public String getToken(RouteDetailsPlace place) {
			return place.getId();
		}

	}

}
