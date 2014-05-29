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
package com.jettmarks.routes.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class RouteListPlace extends Place {
	/**
   * @param displayGroupName
   */
  public RouteListPlace(String displayGroupName)
  {
    super();
    this.displayGroupName = displayGroupName;
  }

  /**
   * 
   */
  public RouteListPlace()
  {
  }

  public static class Tokenizer implements PlaceTokenizer<RouteListPlace> {

		@Override
		public RouteListPlace getPlace(String token) {
			return new RouteListPlace();
		}

		@Override
		public String getToken(RouteListPlace place) {
			return "";
		}

	}

  private String displayGroupName;

  /**
   * @param name
   */
  public void setDisplayGroupName(String name)
  {
    this.displayGroupName = name;
  }

  /**
   * @return
   */
  public String getDisplayGroupName()
  {
    return displayGroupName;
  }
}
