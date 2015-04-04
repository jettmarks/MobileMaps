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
package com.jettmarks.bikeTrains.client.bean;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author jett
 *
 */
public interface FacilityType extends IsSerializable
{
  public static final int UNKNOWN = 0;
  public static final int SEGREGATED = 1;
  public static final int BIKE_LANE = 2;
  public static final int BIKE_ROUTE = 3;
  public static final int BIKE_FRIENDLY = 4;
}
