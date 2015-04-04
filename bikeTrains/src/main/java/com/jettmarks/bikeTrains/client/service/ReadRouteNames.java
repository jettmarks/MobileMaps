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
 * Created on Apr 17, 2009
 */
package com.jettmarks.bikeTrains.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * This service "lists the directory" of routes for specific route sources.
 * 
 * @author jett
 */
@RemoteServiceRelativePath("/readRouteNames")
public interface ReadRouteNames extends RemoteService
{
  /** Provide a list of route names given the name of a RouteSource. */
  public String[] getRouteNames(String routeSourceName);
  /** Provide a list of route names from the Local Drive RouteSource. */
  public String[] getLocalRouteNames();
  /** Provide a list of route names from the Local Drive RouteSource matching the tag. */
  public String[] getLocalRouteNames(String tag);
  /** Provide a list of route names from Bikely matching the user. */
  public String[] getUsersRouteNames(String user);
  /** Provide a list of overlay names from the Local Drive RouteSource. */
  public String[] getLocalOverlayNames();

  public static class Util
  {

    public static ReadRouteNamesAsync getInstance()
    {

      return GWT.create(ReadRouteNames.class);
    }
  }

}
