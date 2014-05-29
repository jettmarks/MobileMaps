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
package com.jettmarks.routes.client.activities.showGroup;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ShowGroupPlace extends Place {
	/**
   * @param displayGroupName
   */
  public ShowGroupPlace(String displayGroupName)
  {
    super();
    this.displayGroupName = displayGroupName;
  }

  /**
   * 
   */
  public ShowGroupPlace()
  {
  }

  /**
   * @param string
   * @param string2
   */
  public ShowGroupPlace(String displayGroupName, String description)
  {
    super();
    this.displayGroupName = displayGroupName;
    this.description = description;
  }

  public static class Tokenizer implements PlaceTokenizer<ShowGroupPlace> {

		@Override
		public ShowGroupPlace getPlace(String token) {
			return new ShowGroupPlace();
		}

		@Override
		public String getToken(ShowGroupPlace place) {
			return "";
		}

	}

  private String displayGroupName;
  private String description;

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
