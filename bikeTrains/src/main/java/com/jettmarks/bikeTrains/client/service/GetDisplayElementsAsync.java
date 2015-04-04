package com.jettmarks.bikeTrains.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.jettmarks.bikeTrains.client.bean.DisplayElementDTO;
import com.jettmarks.bikeTrains.client.bean.DisplayGroupDTO;
import com.jettmarks.bikeTrains.client.rep.BikeTrainDE;
import com.jettmarks.bikeTrains.client.rep.RouteOnlyDE;
import com.jettmarks.bikeTrains.client.rep.SuitabilitySegmentDE;

public interface GetDisplayElementsAsync {

	void getElementList(DisplayGroupDTO displayGroup,
			AsyncCallback<List<DisplayElementDTO>> callback);

	void getBikeTrain(DisplayElementDTO displayElement,
			AsyncCallback<BikeTrainDE> callback);

	void getSuitabilitySegment(DisplayElementDTO nextRoute,
			AsyncCallback<SuitabilitySegmentDE> callback);

	void getRouteOnlyList(AsyncCallback<List<RouteOnlyDE>> callback);

	/**
	 * Utility class to get the RPC Async interface from client-side code
	 */
	public static class Util {
		private static GetDisplayElementsAsync instance;

		public static GetDisplayElementsAsync getInstance() {
			if (instance == null) {
				instance = (GetDisplayElementsAsync) GWT
						.create(GetDisplayElements.class);
				ServiceDefTarget target = (ServiceDefTarget) instance;
				// target.setServiceEntryPoint(GWT.getModuleBaseURL() +
				target.setServiceEntryPoint("/getDisplayElements");
			}
			return instance;
		}
	}

}
