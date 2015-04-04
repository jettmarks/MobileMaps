/**
 * 
 */
package com.jettmarks.bikeTrains.client.util;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.jettmarks.bikeTrains.client.bean.DisplayElementDTO;
import com.jettmarks.bikeTrains.client.rep.ServiceWrapper;

/**
 * Description.
 * 
 * @author jett
 */
public class RouteScheduledCommand implements ScheduledCommand {
	/**
   * 
   */
	public DisplayElementDTO nextRoute;

	/**
   * 
   */
	public RouteScheduledCommand(DisplayElementDTO nextRoute2) {
		this.nextRoute = nextRoute2;
	}

	/**
	 * @see com.google.gwt.core.client.Scheduler.ScheduledCommand#execute()
	 */
	public void execute() {
		ServiceWrapper.getInstance().requestElement(nextRoute);
	}

}