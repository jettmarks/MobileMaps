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
 * Created April 2009
 */
package com.jettmarks.bikeTrains.client.rep;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TreeItem;
import com.jettmarks.bikeTrains.client.bean.BikeTrainDTO;
import com.jettmarks.bikeTrains.client.bean.DisplayElementDTO;
import com.jettmarks.bikeTrains.client.bean.DisplayGroupDTO;
import com.jettmarks.bikeTrains.client.bean.EncodedTrack;
import com.jettmarks.bikeTrains.client.bean.Route;
import com.jettmarks.bikeTrains.client.bean.RouteRequest;
import com.jettmarks.bikeTrains.client.bean.RouteRequestBase;
import com.jettmarks.bikeTrains.client.bean.RouteRequestRouteName;
import com.jettmarks.bikeTrains.client.service.GetDisplayElementsAsync;
import com.jettmarks.bikeTrains.client.service.ReadRouteNames;
import com.jettmarks.bikeTrains.client.service.ReadRouteNamesAsync;
import com.jettmarks.bikeTrains.client.service.RouteSave;
import com.jettmarks.bikeTrains.client.service.RouteSaveAsync;
import com.jettmarks.routes.client.bean.SuitabilityRating;

/**
 * GWT Browser side component that helps turn a request for a particular set of
 * records into a bunch of EncodedTracks on a Map.
 * 
 * There are a few layers to this. It started with requesting routes based on
 * GPX files. Since this could take a while to load and present, the browser
 * code was given the ability to break the request into individual AJAX requests
 * so it could have the opportunity to present a progress bar.
 * <P>
 * The next layer of indirection was added to isolate the EncodedTracks away
 * from other information about the track. This supported BikeTrains and
 * SuitabilitySegments. The main difference was the introduction of a
 * DisplayElement class that could be subclassed according to the type of data
 * being persisted. This implied another important layer: the Data Persistence
 * for the additional data (since the GPX tracks were kept in the original
 * files.
 * <P>
 * The next layer of indirection is presenting DisplayElements that have a track
 * but do not yet have the data we want to eventually persist. The example is
 * presenting a list of routes (from GPX tracks or other source) and then asking
 * the user to enter the DisplayElement information that gets persisted to the
 * Database.
 * 
 * 
 * @author jett
 */
public class ServiceWrapper {
	protected ListBox listBox = null;
	TreeItem tree = null;

	/**
	 * It looks weird to have a bunch of static fields here, but remember that
	 * this code gets turned into JavaScript and is executed inside the user's
	 * browser and runs as a single thread. This code would get confused if I
	 * tried to compile it without making it static.
	 */
	private static String routeSourceName = null;
	private static String[] tagList = new String[1];
	private static ServiceWrapper singleton;
	/** Generic container that will hold the routes we find. */
	private static RouteContainer routeContainer = null;

	public ServiceWrapper() {

	}

	public ServiceWrapper(RouteContainer rc) {
		routeContainer = rc;
	}

	/**
	 * 
	 public static void addOverlays() { GeoXmlLoadCallback callback = new
	 * GeoXmlLoadCallback() {
	 * 
	 * @Override public void onFailure(String url, Throwable caught) { }
	 * @Override public void onSuccess(String url, GeoXmlOverlay overlay) {
	 *           MapPanel.addOverlay(overlay); } }; GeoXmlOverlay.load(
	 *           "http://gpx.cycling.jettmarks.com/master/CyclingRides.kmz",
	 *           callback); }
	 */

	public void populateTree(RouteContainer passedTree, String routeSourceName) {
		Window.setStatus("Loading list of routes from: " + routeSourceName);
		routeContainer = passedTree;

		// Create Async callback
		ReadRouteNamesAsync service = ReadRouteNames.Util.getInstance();
		AsyncCallback<String[]> callback = new RouteNamesAsyncCallback();

		// Make call
		service.getRouteNames(routeSourceName, callback);
	}

	// protected void parseFeedXML(String xml)
	// {
	// try
	// {
	// // parse the XML document into a DOM
	// Document messageDom = XMLParser.parse(xml);
	//
	// // Pull out the list of recent routes
	// NodeList rdfLines = messageDom.getElementsByTagName("li");
	// for (int i = 0; i < rdfLines.getLength(); i++)
	// {
	// String fullURL = ((Element)
	// rdfLines.item(i)).getAttribute("rdf:resource");
	// String name = fullURL.substring(fullURL.lastIndexOf('/') + 1);
	// listBox.addItem(name, fullURL);
	// }
	// }
	// catch (DOMException e)
	// {
	// Window.alert("Could not parse XML document: " + e);
	// }
	// Window.setStatus("Done");
	// }

	public void showRoutesByUser(String user) {
		routeSourceName = "Bikely (my user)";
		ReadRouteNamesAsync service = ReadRouteNames.Util.getInstance();

		AsyncCallback<String[]> callback = new RouteNamesAsyncCallback();

		service.getUsersRouteNames(user, callback);
	}

	/**
	 * Accepts the tag from a URL and presents that tag's list of routes.
	 * 
	 * Makes a call to ReadRouteNames which then calls us back with a list of
	 * the names.
	 * 
	 * @see RouteNamesAsyncCallback.onSuccess(String[]) for details of what
	 *      happens with the returned list.
	 * 
	 * @param tag
	 */
	public void showLocalRoutes(String tag) {
		// Record our Route Source
		routeSourceName = "Local Drive";
		tagList[0] = tag;

		ReadRouteNamesAsync service = ReadRouteNames.Util.getInstance();

		AsyncCallback<String[]> callback = new RouteNamesAsyncCallback();

		if (tag == null) {
			service.getLocalRouteNames(callback);
		} else {
			service.getLocalRouteNames(tag, callback);
		}

	}

	/**
	 * Makes a call to ReadRouteNames with the name of a DisplayGroup which then
	 * calls us back with a list of IDs.
	 */
	public void showRoutes(DisplayGroupDTO displayGroup) {
		GetDisplayElementsAsync service = GetDisplayElementsAsync.Util
				.getInstance();
		AsyncCallback<List<DisplayElementDTO>> callback = new DisplayElementsAsyncCallback();

		service.getElementList(displayGroup, callback);
	}

	/**
	 * Version of method that accepts a Route.
	 * 
	 * @param joinedRoute
	 */
	public static void saveRoute(Route route, String tags[]) {
		// saveRoute(route.getName(), route.getGpxContent(), tags);
	}

	/**
	 * Save this route as a SegmentedRoute.
	 * 
	 * Calls the service that also updates the database as well as generating
	 * the GPX on the file system.
	 * 
	 * @param route
	 *            - Route that supplies the GPX data and the name of the route.
	 * @param tags
	 *            - How to classify the route.
	 * @param rating
	 *            - SuitabilityRating that indicates this is a SegmentedRoute
	 *            that should be saved as a SegmentedRoute.
	 */
	public static void saveRoute(Route route, String tags[],
			SuitabilityRating rating) {
		String newRouteName = route.getName();
		// String gpxContent = route.getGpxContent();
		String gpxContent = null;

		RouteSaveAsync routeSaveService = RouteSave.Util.getInstance();
		AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {
			public void onFailure(Throwable caught) {
				Window.alert("Route Save Failed: " + caught.toString());
			}

			public void onSuccess(Integer result) {
				// No action required at this time
			}
		};
		routeSaveService.saveAs(newRouteName + ".gpx", tags, gpxContent,
				rating, callback);
	}

	/**
	 * 
	 * @param newRouteName
	 * @param tag
	 * @param newPoints
	 */
	public static void saveRoute(String newRouteName, String gpxContent,
			String[] tags) {
		RouteSaveAsync routeSaveService = RouteSave.Util.getInstance();
		AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {
			public void onFailure(Throwable caught) {
				Window.alert(ServiceWrapper.class.getName()
						+ ": Route Save Failed: " + caught.toString());
			}

			public void onSuccess(Integer result) {
				// No action required at this time
			}
		};
		routeSaveService.saveAs(newRouteName + ".gpx", tags, gpxContent,
				callback);
	}

	/**
	 * 
	 * @param route
	 */
	public void saveRouteThenShow(Route route) {
		// saveRouteThenShow(route.getName(), route.getGpxContent());
	}

	/**
	 * 
	 * @param name
	 * @param gpxContent
	 */
	public void saveRouteThenShow(String name, String gpxContent) {
		RouteSaveAsync routeSaveService = RouteSave.Util.getInstance();
		AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {
			public void onFailure(Throwable caught) {
				Window.alert("Route Save Failed: " + caught.toString());
			}

			public void onSuccess(Integer result) {
				showLocalRoutes(null);
			}
		};
		routeSaveService.saveAs(name + ".gpx", gpxContent, callback);
	}

	/**
	 * 
	 * @return
	 */
	public static ServiceWrapper getInstance() {
		if (singleton == null) {
			singleton = new ServiceWrapper();
		}
		return singleton;
	}

	/**
	 * Handles the call to request a DisplayElement for viewing.
	 * 
	 * Uses the appropriate inner class for constructing an appropriate client
	 * bean from the given DisplayElement.
	 * 
	 * @param nextRoute
	 */
	public void requestElement(DisplayElementDTO nextRoute) {
		if (nextRoute == null) {
			Window.alert("DisplayElement is unexpectedly null");
			return;
		}
		switch (nextRoute.getType()) {
		case ROUTE:
			// Something we know what to do with
			if ("BikeTrain".equals(nextRoute.getClassName())) {
				// Window.alert("Retrieving "+displayElement);
				GetDisplayElementsAsync service = GetDisplayElementsAsync.Util
						.getInstance();
				AsyncCallback<BikeTrainDE> callback = new BikeTrainAsyncCallback();
				service.getBikeTrain(nextRoute, callback);
			} else if ("SuitabilitySegment".equals(nextRoute.getClassName())) {
				GetDisplayElementsAsync service = GetDisplayElementsAsync.Util
						.getInstance();
				AsyncCallback<SuitabilitySegmentDE> callback = new SuitabilitySegmentAsyncCallback();
				service.getSuitabilitySegment(nextRoute, callback);
			} else {
				Window.alert("Don't know what to do with: " + nextRoute);
			}
			break;
		case OVERLAY:
		case SEGMENTED:
			Window.alert("Unexpected type in DisplayElement: " + nextRoute);
			break;
		}
	}

	/**
	 * Knows what to do with a BikeTrain object when we get one.
	 * 
	 * @author jett
	 */
	public class BikeTrainAsyncCallback implements AsyncCallback<BikeTrainDE> {
		/**
		 * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
		 */
		public void onFailure(Throwable caught) {
			Window.alert("BikeTrainAsync is failure");
		}

		/**
		 * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
		 */
		public void onSuccess(BikeTrainDE result) {
			EncodedTrack encodedTrack = result.getEncodedTrack();
			if (encodedTrack == null) {
				Window.alert("Unable to find Track for " + result);
			}
			BikeTrainDTO bikeTrain = result.getBikeTrain();
			encodedTrack.setBikeTrain(bikeTrain);
			String routeName = bikeTrain.getRouteName();

			Route route = RouteFactory.getInstance(encodedTrack);
			encodedTrack.setRouteName(routeName);
			routeContainer.put(routeName, route);
			routeContainer.updateProgress();
		}
	}

	/**
	 * Handles retrieval of a DisplayElement specific to SuitabilitySegments.
	 * 
	 * @author jett
	 */
	public class SuitabilitySegmentAsyncCallback implements
			AsyncCallback<SuitabilitySegmentDE> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.
		 * Throwable)
		 */
		public void onFailure(Throwable caught) {
			Window.alert("Failure to retrieve Suitability Segment");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.
		 * Object)
		 */
		public void onSuccess(SuitabilitySegmentDE result) {
			EncodedTrack encodedTrack = result.getEncodedTrack();
			if (encodedTrack == null) {
				Window.alert("Unable to find Track for " + result);
			}
			String routeName = result.getRouteName();

			Route route = RouteFactory.getInstance(encodedTrack);
			encodedTrack.setRouteName(routeName);
			routeContainer.put(routeName, route);
			routeContainer.updateProgress();
		}

	}

	/**
	 * Callback class for a range of services requesting a list of route names
	 * to be retrieved from the server.
	 * 
	 * If the interface call fails, we're done.
	 * 
	 * If the interface call succeeds, we check that we got something and if so,
	 * we pass a request over to the route container with that list of route
	 * requests.
	 * 
	 * @author jett
	 */
	public final class RouteNamesAsyncCallback implements
			AsyncCallback<String[]> {

		public void onFailure(Throwable caught) {
			Window.alert("RouteNames Async: " + caught.toString());
			Window.setStatus("Done");
		}

		public void onSuccess(String[] routeList) {
			// Make sure we found something before trying to create
			// RouteRequests
			if (routeList == null) {
				if (tagList[0] == null) {
					Window.alert("No routes found");
				} else {
					Window.alert("No routes found for " + tagList[0]);
				}
				Window.setStatus("Done");
				return;
			}

			RouteRequestRouteName routeRequestRouteName = null;
			if (tagList[0] == null) {
				routeRequestRouteName = new RouteRequestRouteName(
						routeSourceName, routeList);
			} else {
				routeRequestRouteName = new RouteRequestRouteName(
						routeSourceName, routeList, tagList);
			}
			routeContainer.addRoutes(routeRequestRouteName);
		}
	}

	/**
	 * Handles the callback from the server after we've asked it to give us a
	 * list of the DisplayElements.
	 * 
	 * @author jett
	 */
	public final class DisplayElementsAsyncCallback implements
			AsyncCallback<List<DisplayElementDTO>> {

		/**
		 * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
		 */
		public void onFailure(Throwable caught) {
			Window.alert(caught.toString());
			Window.setStatus("Done");
		}

		/**
		 * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
		 */
		public void onSuccess(List<DisplayElementDTO> result) {
			if (result == null) {
				Window.alert("Empty Result");
			}
			// String msg = "Got "+result.size()+" elements: \n";
			// for (DisplayElementDTO element : result)
			// {
			// msg += element+", ";
			// }
			// Window.alert(msg);
			RouteRequest routeRequest = RouteRequestBase.getInstance(result);
			routeContainer.addRoutes(routeRequest);
		}

	}

}
