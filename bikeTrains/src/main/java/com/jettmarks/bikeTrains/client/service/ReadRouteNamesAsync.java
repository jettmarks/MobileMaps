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

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * 
 * @author jett
 * 
 */
public interface ReadRouteNamesAsync
{
  /** Provide a list of route names given the name of a RouteSource. */
  public void getRouteNames(String routeSourceName, AsyncCallback<String[]> callback);
  /** Provide a list of route names from the Local Drive RouteSource. */
  public void getLocalRouteNames(AsyncCallback<String[]> callback);
  /** Provide a list of route names from the Local Drive RouteSource matching the tag. */
  public void getLocalRouteNames(String tag, AsyncCallback<String[]> callback);
  /** Provide a list of route names from Bikely matching the user. */
  public void getUsersRouteNames(String user, AsyncCallback<String[]> callback);
  /** Provide a list of overlay names from the Local Drive RouteSource. */
  public void getLocalOverlayNames(AsyncCallback<String[]> callback);

}
