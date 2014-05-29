/**
 * Copyright 2009 Jett Marks
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Created on Apr 5, 2009
 */
package com.jettmarks.routes.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jettmarks.routes.client.bean.BikeTrainDTO;
import com.jettmarks.routes.client.bean.SuitabilityRating;

/**
 * All methods provided by this service will put a route on the disk in a 
 * particularly local drive directory(tag), but some will also persist the 
 * route as a BikeTrain or a rated segment.
 * 
 * @author jett
 * 
 */
@RemoteServiceRelativePath("../saveRoute")
public interface RouteSave extends RemoteService
{
  /** Save route contents as particular file specified as a resource string. */
  public int saveAs(String fileResource, String fileContents);

  /** Save route contents as named file under specified tagList. */
  public int saveAs(String fileResource, String[] tagList, String fileContents);

  int saveAs(String fileResource, String[] tagList, String fileContents,
		SuitabilityRating rating);

  /** Save route as a Bike Train. */
  public int saveBikeTrain(String fileResource,
                           String fileContents,
                           BikeTrainDTO bikeTrain,
                           Integer displayGroupId);
  
  /** Update existing Bike Train with changes in the passed BikeTrain. */
  public int updateBikeTrain(BikeTrainDTO bikeTrain);
  
  /** Copy an existing Bike Train into new instance and map to named group. */
  public int copyBikeTrain(BikeTrainDTO bikeTrain, Integer displayGroupId);

  public static class Util
  {
    public static RouteSaveAsync getInstance()
    {
      return GWT.create(RouteSave.class);
    }
  }
}
