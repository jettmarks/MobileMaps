/**
 * 
 */
package com.jettmarks.bikeTrains.server.bt;

import java.util.ArrayList;
import java.util.List;

import com.jettmarks.bikeTrains.client.bean.DisplayElementDTO;
import com.jettmarks.bikeTrains.client.rep.BikeTrainDE;
import com.jettmarks.bikeTrains.server.GetDisplayElementsImpl;

/**
 * Description.
 * 
 * @author jett
 */
public class RouteOnlyBikeTrain {
	private List<String> displayElementRouteNames = null;

	private List<String> routeNames = null;

	private List<String> routesWithoutBikeTrains = null;

	public RouteOnlyBikeTrain() {
		routeNames = new ArrayList<String>();
		displayElementRouteNames = new ArrayList<String>();
		routesWithoutBikeTrains = new ArrayList<String>();
	}

	public int addDisplayElements(List<DisplayElementDTO> displayElementList) {
		if (displayElementList == null || displayElementList.size() == 0) {
			return 0;
		}

		GetDisplayElementsImpl getDisplayElementsImpl = new GetDisplayElementsImpl();
		for (DisplayElementDTO de : displayElementList) {
			BikeTrainDE bikeTrainDE = getDisplayElementsImpl.getBikeTrain(de);
			displayElementRouteNames.add(bikeTrainDE.getBikeTrain()
					.getRouteName());
		}
		return displayElementRouteNames.size();
	}

	public int addRouteNames(List<String> routeList) {
		routeNames.addAll(routeList);
		return routeNames.size();
	}

	/**
	 * Pulls the routes that do not have a BikeTrain object setup in the DB.
	 * 
	 * Returns null if the list of names hasn't been set. Returns empty list if
	 * there are no names without a BikeTrain.
	 * 
	 * @return
	 */
	public List<String> getRoutesWithoutBikeTrains() {
		if (routeNames.isEmpty()) {
			return null;
		}

		if (displayElementRouteNames.isEmpty()) {
			routesWithoutBikeTrains.addAll(routeNames);
			return routesWithoutBikeTrains;
		}

		for (String routeName : routeNames) {
			if (!displayElementRouteNames.contains(routeName)) {
				routesWithoutBikeTrains.add(routeName);
			}
		}
		return routesWithoutBikeTrains;
	}

	/**
	 * @return the displayElementRouteNames
	 */
	public List<String> getDisplayElementRouteNames() {
		return displayElementRouteNames;
	}

	/**
	 * @return the routeNames
	 */
	public List<String> getRouteNames() {
		return routeNames;
	}
}
