/**
 *   Copyright 2010 Jett Marks
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
 * Created Aug 10, 2010
 */
package com.jettmarks.routes.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jettmarks.routes.client.bean.DisplayElementDTO;
import com.jettmarks.routes.client.rep.BikeTrainDE;
import com.jettmarks.routes.client.rep.RouteOnlyDE;
import com.jettmarks.routes.client.rep.SuitabilitySegmentDE;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;

/**
 * How to get a list of DisplayElements for a given DisplayGroup and then instances
 * matching the particular subclass (Bike Train or SuitabilitySegment).
 * 
 * This handles
 * <UL>
 * <LI>Obtaining a list of Bike Trains for the 'btFall2010' tag for example.
 * <LI>Obtaining the BikeTrainDE that matches a particular DisplayElement.
 * <LI>Obtaining the SuitabilitySegmentDE that matches a particular DisplayElement.
 * </UL>
 * 
 * At this time, the caller must know which displayGroups will return one type of 
 * DisplayElement so it can be used to retrieve the proper subclassed object.
 *
 * @author jett
 */
@RemoteServiceRelativePath("getDisplayElements")
public interface GetDisplayElements extends RemoteService
{
  /** Procure a list of the elements for a given DisplayGroup. */
  public List<DisplayElementDTO> getElementList(DisplayGroupDTO displayGroup);
  
  /** Procure the BikeTrain corresponding to the given DisplayElement. */
  public BikeTrainDE getBikeTrain(DisplayElementDTO displayElement);
  
  SuitabilitySegmentDE getSuitabilitySegment(DisplayElementDTO nextRoute);
  
  /** Procure the RouteOnlyDE list for GPX files not yet turned into DEs. */
  public List<RouteOnlyDE> getRouteOnlyList();
  
}
