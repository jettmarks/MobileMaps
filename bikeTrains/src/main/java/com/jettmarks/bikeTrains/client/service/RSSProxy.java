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
 * Created April 2009
 */
package com.jettmarks.bikeTrains.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("/proxy")
public interface RSSProxy extends RemoteService
{
  public String getFeed(String url);
  public String getGPXTemplate();
  public String getRoute(String routeSourcePath);
  public String getRoute(String routeName, String routeSourceName);
  public String getRoute(String routeName, String routeSourceName, String[] tagList);
  public String getOverlay(String overlayName, String routeSourceName);
  
  public static class Util
  {
    public static RSSProxyAsync getInstance()
    {
      return GWT.create(RSSProxy.class);
    }
  }
}
