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
 * Created Jul 11, 2014
 */
package com.jettmarks.routes.client.rep;

import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.bean.Route;
import com.jettmarks.routes.client.bean.RouteRequest;
import com.jettmarks.routes.client.bean.RouteRequestRouteName;
import com.jettmarks.routes.client.ui.RouteContainer;

/**
 * Public place to hold the routes for the application.
 * 
 * @author jett
 */
public class RouteContainerImpl implements RouteContainer
{

  private Route selectedRoute = null;

  private DisplayGroupDTO displayGroup = null;

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.jettmarks.routes.client.ui.RouteContainer#addRoutes(com.jettmarks.routes
   * .client.bean.RouteRequestRouteName)
   */
  @Override
  public void addRoutes(RouteRequestRouteName routeRequestRouteName)
  {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.jettmarks.routes.client.ui.RouteContainer#addRoutes(com.jettmarks.routes
   * .client.bean.RouteRequest)
   */
  @Override
  public void addRoutes(RouteRequest routeRequest)
  {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.jettmarks.routes.client.ui.RouteContainer#put(java.lang.String,
   * com.jettmarks.routes.client.bean.Route)
   */
  @Override
  public void put(String routeName, Route route)
  {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.jettmarks.routes.client.ui.RouteContainer#updateProgress()
   */
  @Override
  public void updateProgress()
  {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.jettmarks.routes.client.ui.RouteContainer#getSelectedRoute()
   */
  @Override
  public Route getSelectedRoute()
  {
    return selectedRoute;
  }

  @Override
  public void setSelectedRoute(Route route)
  {
    selectedRoute = route;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.jettmarks.routes.client.ui.RouteContainer#getCurrentDisplayGroup()
   */
  @Override
  public DisplayGroupDTO getCurrentDisplayGroup()
  {
    return displayGroup;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.jettmarks.routes.client.ui.RouteContainer#setCurrentDisplayGroup(com
   * .jettmarks.routes.client.bean.DisplayGroupDTO)
   */
  @Override
  public void setCurrentDisplayGroup(DisplayGroupDTO displayGroup)
  {
    this.displayGroup = displayGroup;
  }

}
