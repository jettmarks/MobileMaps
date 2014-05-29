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
package com.jettmarks.routes.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jettmarks.routes.client.bean.EncodedTrack;

/**
 * Whereas this is the "GetRoute" service, for this service, a Route means an 
 * EncodedTrack so it can be displayed in GoogleMaps efficiently.
 * 
 * If you supply a tag, this will search a particular "name space" for an 
 * EncodedTrack, otherwise the default name space is used.  The default 
 * name space can be overridden by using the ManageProperties interface.
 * 
 * The RouteSourceName isn't fully implemented, but should normally be given
 * as "Local Drive" for implementations where the resource is on the local hard
 * drive (as opposed to a network/URL-based resource such as "Bikely".
 * 
 * This uses the RSSProxy interface underneath to actually retrieve the
 * appropriate resource as a "raw Track" to be turned into an EncodedTrack by
 * the Encoder project. 
 * 
 * @author jett
 */
@RemoteServiceRelativePath("getRoute")
public interface GetRoute extends RemoteService
{
  public EncodedTrack getEncodedTrack(String routeName, String routeSourceName);
  public EncodedTrack getEncodedTrack(String routeName, String routeSourceName, 
                               String[] tagList);
  
  public static class Util
  {
    public static RSSProxyAsync getInstance()
    {
      return GWT.create(GetRoute.class);
    }
  }
}
