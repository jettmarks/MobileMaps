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
 * Created Aug 19, 2014
 */
package com.jettmarks.routes.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Description.
 * 
 * @author jett
 */
public class FindRoutePlace extends Place {
    public static class Tokenizer implements PlaceTokenizer<FindRoutePlace> {

	@Override
	public FindRoutePlace getPlace(String token) {
	    return new FindRoutePlace();
	}

	@Override
	public String getToken(FindRoutePlace place) {
	    return "findRoute";
	}

    }
}
