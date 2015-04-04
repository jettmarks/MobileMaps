/**
 * Copyright 2010 Jett Marks
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
 * Created Aug 13, 2010
 */
package com.jettmarks.bikeTrains.client.bean;

import java.util.List;

/*
 * Description.
 * 
 * @author jett
 */
public abstract class RouteRequestBase implements RouteRequest
{

  private int completedTasks = 0;
  protected int requestedTasks = 0;
  protected int totalTasks;

  /**
   * 
   */
  public RouteRequestBase()
  {
    super();
  }

  /**
   * Version that create an instance based on List of DisplayElement.
   * 
   * @param result List of DisplayElement.
   * @return RouteRequestDE
   */
  public static RouteRequest getInstance(List<DisplayElementDTO> result)
  {
    return new RouteRequestDE(result);
  }
  
  /** Pre-1.5 style constructor accepting a list of names, RouteSource
   *  and a tag list. */
  public static RouteRequest getInstance(String routeSourceName, String[] routeNames, 
                                 String[] tagList)
  {
    return new RouteRequestRouteName(routeSourceName, routeNames, tagList);
  }
  
  /** Pre-1.5 style constructor accepting a list of names and a RouteSource. */
  public static RouteRequest getInstance(String routeSourceName, String[] routeNames) 
  {
    return new RouteRequestRouteName(routeSourceName, routeNames);
  }
  
  /* (non-Javadoc)
   * @see com.jettmarks.routes.client.bean.RouteRequest#getCompletedTasks()
   */
  public int getCompletedTasks()
  {
    completedTasks = requestedTasks - 1;
    return completedTasks;
  }

  /* (non-Javadoc)
   * @see com.jettmarks.routes.client.bean.RouteRequest#setCompletedTasks(int)
   */
  public void setCompletedTasks(int completedTasks)
  {
    this.completedTasks = completedTasks;
  }

  /* (non-Javadoc)
   * @see com.jettmarks.routes.client.bean.RouteRequest#getTotalTasks()
   */
  public int getTotalTasks()
  {
    return totalTasks;
  }

  /* (non-Javadoc)
   * @see com.jettmarks.routes.client.bean.RouteRequest#setTotalTasks(int)
   */
  public void setTotalTasks(int totalTasks)
  {
    this.totalTasks = totalTasks;
  }

  /* (non-Javadoc)
   * @see com.jettmarks.routes.client.bean.RouteRequest#hasNext()
   */
  public boolean hasNext()
  {
    return (requestedTasks < totalTasks);
  }

}