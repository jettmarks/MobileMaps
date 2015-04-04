/**
 *   Copyright 2009 Jett Marks
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
 * Created Oct 19, 2009
 */
package com.jettmarks.bikeTrains.server.common;

import java.util.HashMap;

import com.jettmarks.bikeTrains.client.bean.FacilityType;

/**
 * Supply the name of the route and this returns the FacilityType.
 * 
 * @author jett
 */
public class Facility {
	private static HashMap<String, Integer> facilityTypes = new HashMap<String, Integer>();

	/** Hard coded for now; DB later. */
	static {
		facilityTypes.put("HardSurface_MultiusePath", FacilityType.SEGREGATED);
		facilityTypes.put("HardSurface_MultiUsePath", FacilityType.SEGREGATED);
		facilityTypes
				.put("Bike_Lane_And_Signed_Route", FacilityType.BIKE_ROUTE);
		facilityTypes.put("Bike_Lane", FacilityType.BIKE_LANE);
		facilityTypes.put("Bike_Shoulder", FacilityType.BIKE_FRIENDLY);
	}

	/**
	 * Give this a route name and it figures out the FacilityType.
	 * 
	 * At this time, the characters prior to the '-' are used for a lookup in a
	 * HashMap. Later, we expect this to be recorded in a database.
	 * 
	 * @param name
	 *            a String representation of the route's name.
	 * @return the FacilityType or UNKNOWN if not found.
	 */
	public static Integer getFacilityType(String name) {
		String[] values = name.split("-");
		String key = values[0];
		if (facilityTypes.containsKey(key)) {
			return (facilityTypes.get(key));
		} else {
			return FacilityType.UNKNOWN;
		}
	}

}
