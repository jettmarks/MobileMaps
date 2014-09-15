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
 * Created Sep 6, 2014
 */
package com.jettmarks.routes.client.place;

import com.google.gwt.place.shared.Place;

/**
 * Maps between a Place and the type of place that might need special handling
 * upon orientation change.
 * 
 * The ones that map to MAP are the ones that should have Event List as the main
 * when the nav is retracted.
 * 
 * @author jett
 */
public class PlaceClassifier {
    public enum PlaceType {
	NAV, EMPTY_MAP, EVENT, OTHER
    };

    public static PlaceType getPlaceType(Place place) {
	if (place instanceof EventSelectionPlace)
	    return PlaceType.EMPTY_MAP;
	// No longer so sure
	if (place instanceof EventPlace)
	    return PlaceType.EVENT;
	if (place instanceof RouteListPlace)
	    return PlaceType.EVENT;
	if (place instanceof RouteDetailsPlace)
	    return PlaceType.EVENT;

	// Recognized as non-Map
	if (place instanceof HomePlace)
	    return PlaceType.NAV;
	if (place instanceof AboutPlace)
	    return PlaceType.NAV;
	if (place instanceof ConductorPlace)
	    return PlaceType.NAV;
	if (place instanceof ResourcesPlace)
	    return PlaceType.NAV;
	if (place instanceof GetInvolvedPlace)
	    return PlaceType.NAV;
	if (place instanceof FindRoutePlace)
	    return PlaceType.NAV;

	// We don't recognize the place
	return PlaceType.OTHER;
    }
}
