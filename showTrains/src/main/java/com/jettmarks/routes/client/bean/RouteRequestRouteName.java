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
 * Created on Apr 21, 2009
 */
package com.jettmarks.routes.client.bean;

import java.util.List;

import com.jettmarks.routes.client.bean.DisplayElementDTO;

/**
 * 
 * @author jett
 * 
 */
public class RouteRequestRouteName extends RouteRequestBase
{
  private String routeSourceName = null;
  String[] routeNames = null;
  private String[] tagList = null;
  private List<DisplayElementDTO> displayElements = null;
  /** Pre-1.5 style constructor accepting a list of names and a RouteSource. */
  public RouteRequestRouteName(String routeSourceName, String[] routeNames, 
                      String[] tagList)
  {
    this(routeSourceName, routeNames);
    this.setTagList(tagList);
  }
  
  /** Pre-1.5 style constructor accepting a list of names and a RouteSource. */
  public RouteRequestRouteName(String routeSourceName, String[] routeNames)
  {
    this.totalTasks = routeNames.length;
    this.routeNames = routeNames;
    this.routeSourceName = routeSourceName;
  }
  
  /** 
   * Post-1.5 style of requesting DisplayElements - by using the base which has
   * only the identifying fields.
   * 
   * This breaks much of the coupling between the view and the model.
   * 
   * @param elements
   */
  public RouteRequestRouteName(List<DisplayElementDTO> elements)
  {
    this.displayElements = elements;
  }

  /**
   * @return the routeSourceName
   */
  public String getRouteSourceName()
  {
    return routeSourceName;
  }

  /**
   * @param routeSourceName the routeSourceName to set
   */
  public void setRouteSourceName(String routeSourceName)
  {
    this.routeSourceName = routeSourceName;
  }

  public String[] getRouteNames()
  {
    return routeNames;
  }

  public void setRouteNames(String[] routeNames)
  {
    this.routeNames = routeNames;
  }

  /**
   * @param tagList the tagList to set
   */
  public void setTagList(String[] tagList)
  {
    this.tagList = tagList;
  }

  /**
   * @return the tagList
   */
  public String[] getTagList()
  {
    return tagList;
  }

  /**
   * @return the displayElements
   */
  public List<DisplayElementDTO> getDisplayElements()
  {
    return displayElements;
  }

  /**
   * @param displayElements the displayElements to set
   */
  public void setDisplayElements(List<DisplayElementDTO> displayElements)
  {
    this.displayElements = displayElements;
  }

  public String next()
  {
    return (routeNames[requestedTasks++]);
  }

}
