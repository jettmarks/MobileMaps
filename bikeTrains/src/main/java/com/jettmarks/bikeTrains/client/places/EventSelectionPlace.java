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

/**
 * Allows selecting an event from the list of Display Groups so you can view the
 * map of routes for that event.
 * 
 * @author jett
 */
public class EventSelectionPlace extends Place {
    public static class Tokenizer implements
	    PlaceTokenizer<EventSelectionPlace> {

	@Override
	public EventSelectionPlace getPlace(String token) {
	    return new EventSelectionPlace();
	}

	@Override
	public String getToken(EventSelectionPlace place) {
	    return "eventSelection";
	}

    }
}
