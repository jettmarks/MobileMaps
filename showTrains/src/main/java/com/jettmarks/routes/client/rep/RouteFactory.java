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
 * Created Oct 10, 2009
 */
package com.jettmarks.routes.client.rep;

import com.jettmarks.routes.client.bean.BikeTrainRoute;
import com.jettmarks.routes.client.bean.DisplayOnlyRoute;
import com.jettmarks.routes.client.bean.EncodedTrack;
import com.jettmarks.routes.client.bean.Route;

/**
 * Returns an instance of Route appropriate for the EncodedTrack.
 * 
 * This supports several Route Types:<UL>
 * <LI>DisplayOnlyRoute by default
 * <LI>When the hillCategory is non-zero, it will return a HillRoute
 * <LI>FacilityRoute when 'KML' is the source name (not robust)
 * <LI>BikeTrainRoute when there is a value for the BikeTrain.
 * <LI>SgementedRoute when there is a value for the Rating.
 * </UL>
 * 
 * See the Wiki page http://bikehighways.wikidot.com/addingroutetype for 
 * more details on all the changes required to add a new Route Type.
 * 
 * @author jett
 */
public class RouteFactory
{
  public static Route getInstance(EncodedTrack encodedTrack)
  {
    Route route = null;
    String name = encodedTrack.getRouteName();
    if (encodedTrack.getHillCategory() > 0)
    {
//      route = new HillRoute(name);
    }
    else if ("KML".equals(encodedTrack.getRouteSourceName()))
    {
//      route = new FacilityRoute(name);
    }
    else if (encodedTrack.getBikeTrain() != null)
    {
      route = new BikeTrainRoute(name, encodedTrack.getBikeTrain());
    }
    else if (encodedTrack.getRating() != null)
    {
//      route = new SegmentedRoute(name);
    }
    else
    {
      route = new DisplayOnlyRoute(name);
    }
    route.setEncodedTrack(encodedTrack);
    return route;
  }
}
