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
 * Created Jun 02, 2014
 */
package com.jettmarks.routes.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EventPlace extends Place {
	/**
	 * An EventPlace specifies the displayGroupName which matches one-to-one with
	 * a date and a description of the event, as well as bundling the bike trains
	 * that are being conducted as part of that event.
	 * 
   * @param displayGroupName
   */
  public EventPlace(String displayGroupName)
  {
    super();
    this.displayGroupName = displayGroupName;
  }

  /**
   * 
   */
  public EventPlace()
  {
  }

  /**
   * @param dispGroup
   * @param description
   */
  public EventPlace(String dispGroup, String description)
  {
    super();
    this.displayGroupName = dispGroup;
    this.description = description;
  }

  public static class Tokenizer implements PlaceTokenizer<EventPlace> {

		@Override
		public EventPlace getPlace(String token) {
			return new EventPlace();
		}

		@Override
		public String getToken(EventPlace place) {
			return "";
		}

	}

  /** Human name for the Event; shows as the title. */
  private String description;
  /** Forms the URL for the Event. */
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

  /**
   * @return the description
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description)
  {
    this.description = description;
  }
}
