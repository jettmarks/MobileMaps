/**
 * Created on Apr 29, 2009
 */
package com.jettmarks.routes.client.ui;

import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.bean.Route;
import com.jettmarks.routes.client.bean.RouteRequest;
import com.jettmarks.routes.client.bean.RouteRequestRouteName;

/**
 * 
 * @author jett
 * 
 */
public interface RouteContainer
{
  public void addRoutes(RouteRequestRouteName routeRequestRouteName);

  /**
   * @param routeRequest
   */
  public void addRoutes(RouteRequest routeRequest);

  /**
   * @param routeName
   * @param route
   */
  public void put(String routeName, Route route);

  /**
   * 
   */
  public void updateProgress();

  public Route getSelectedRoute();

  public abstract void setSelectedRoute(Route route);

  public DisplayGroupDTO getCurrentDisplayGroup();

  public void setCurrentDisplayGroup(DisplayGroupDTO displayGroup);

}
