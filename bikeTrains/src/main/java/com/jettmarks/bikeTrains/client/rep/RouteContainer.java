/**
 * Created on Apr 29, 2009
 */
package com.jettmarks.bikeTrains.client.rep;

import com.jettmarks.bikeTrains.client.activities.EventActivity;
import com.jettmarks.bikeTrains.client.activities.RouteDetailsActivity;
import com.jettmarks.bikeTrains.client.bean.DisplayGroupDTO;
import com.jettmarks.bikeTrains.client.bean.DisplayOnlyRoute;
import com.jettmarks.bikeTrains.client.bean.Route;
import com.jettmarks.bikeTrains.client.bean.RouteRequest;
import com.jettmarks.bikeTrains.client.bean.RouteRequestRouteName;
import com.jettmarks.bikeTrains.client.ui.EventView;

/**
 * 
 * @author jett
 * 
 */
public interface RouteContainer {

	public void addView(EventView view);

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

	public abstract void setSelectedRoute(Integer i);

	public DisplayGroupDTO getCurrentDisplayGroup();

	public void setCurrentDisplayGroup(DisplayGroupDTO displayGroup);

	/**
	 * @return
	 */
	public boolean displayGroupHasChanged();

	/**
	 * @param displayGroupName
	 */
	public DisplayGroupDTO getDisplayGroup(String displayGroupName);

	/**
	 * @return
	 */
	public Integer getSelectedRouteIndex();

	/**
	 * @param displayOnlyRoute
	 */
	public void setSelectedRoute(DisplayOnlyRoute displayOnlyRoute);

	/**
	 * @param eventActivity
	 */
	public void addActivity(EventActivity eventActivity);

	/**
	 * @param routeDetailsActivity
	 */
	public void setDetailsActivity(RouteDetailsActivity routeDetailsActivity);
}
