/**
 * Created on Apr 29, 2009
 */
package com.jettmarks.routes.client.rep;

import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.activities.showGroup.ShowGroupActivity;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.bean.Route;
import com.jettmarks.routes.client.bean.RouteRequest;
import com.jettmarks.routes.client.bean.RouteRequestRouteName;
import com.jettmarks.routes.client.ui.EventView;

/**
 * 
 * @author jett
 * 
 */
public interface RouteContainer
{
  // TODO: Review this; don't want something so specific in here
  public void setView(EventView view);

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

  /**
   * @return
   */
  public boolean displayGroupHasChanged();

public abstract ShowGroupActivity getActivity();

public abstract void setActivity(ShowGroupActivity activity);

}
