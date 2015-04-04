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
 * Created on Apr 19, 2009
 */
package com.jettmarks.bikeTrains.client.bean;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.Polyline;
import com.jettmarks.bikeTrains.client.ui.MarkerFactory;
import com.jettmarks.bikeTrains.client.ui.MarkerFactory.MarkerType;

/**
 * Extension of Route intended to display route details instead of being edited
 * when clicked.
 * 
 * Tells listeners when a route is clicked so they know which route is selected.
 * 
 * Also modifies the highlighted routes to indicate which is selected on the
 * map.
 * 
 * @author jett
 */
public class DisplayOnlyRoute extends Route {
	protected Marker startMarker = null;

	protected Marker endMarker = null;

	protected StringBuffer infoContent = null;

	private boolean highlightOn = false;

	protected static String previousRouteName = null;

	/**
	 * @param name
	 */
	public DisplayOnlyRoute(String name) {
		super(name);
	}

	/**
	 * @param encodedTrack
	 */
	public void setEncodedTrack(EncodedTrack encodedTrack) {
		super.setEncodedTrack(encodedTrack);

		/* polyline and selectedPolyline are created from the encodedTrack. */
		addPolyline(polyline);
		addPolyline(selectedPolyline);
		addPolyline(highlightedPolyline);
		// addMouseHandlers(polyline);
		LatLng point = LatLng.newInstance(encodedTrack.getStartLat(),
				encodedTrack.getStartLon());
		startMarker = MarkerFactory.getInstance(MarkerType.START_MARKER, point);
		point = LatLng.newInstance(encodedTrack.getEndLat(),
				encodedTrack.getEndLon());
		endMarker = MarkerFactory.getInstance(MarkerType.END_MARKER, point);
		// MapPanel.getMap().addOverlay(startMarker);
		// MapPanel.getMap().addOverlay(endMarker);
		deSelect();
		// highlight(false);
	}

	/**
	 * @see com.jettmarks.routes.client.bean.Route#addMouseHandlers(com.google.gwt.maps.client.overlay.Polyline)
	 * @deprecated - No longer having the Route handle it's own event.
	 */
	@Override
	protected void addMouseHandlers(Polyline p) {
		namePerPolyline.put(p, name);
		p.addClickHandler(new ClickMapHandler() {

			@Override
			public void onEvent(ClickMapEvent event) {
				toggleHighlight();
			}
		});
	}

	/**
	 * Handles the highlight for this and all other routes as well as setting up
	 * the new selected route.
	 * 
	 */
	public void toggleHighlight() {
		highlightOn ^= true;
		highlight(highlightOn);
		if (highlightOn) {
			// We've turned on this route; are there any to turn off?
			if (selectedRoute != null && selectedRoute != this) {
				// Need to turn off the previously selected route and name a new
				// one
				selectedRoute.highlight(false);
			}
			// New selected Route
			selectedRoute = this;
			// RouteContainerFactory.getRouteContainer().setSelectedRoute(this);
		} else {
			// Turning off the highlight on what had been the selected route
			selectedRoute = null;
			// RouteContainerFactory.getRouteContainer().setSelectedRoute(
			// (Integer) null);
		}
	}

	/**
	 * This handles the end markers which only exist in this branch of the class
	 * hierarchy.
	 * 
	 * @see com.jettmarks.routes.client.bean.Route#highlight(boolean)
	 */
	@Override
	public void highlight(boolean on) {
		super.highlight(on);
		highlightOn = on;

		// Workaround for android chrome; unable to use the 'boolean' passed in
		// !?!
		if (on) {
			if (startMarker != null)
				startMarker.setVisible(true);
			if (endMarker != null)
				endMarker.setVisible(true);
		} else {
			if (startMarker != null)
				startMarker.setVisible(false);
			if (endMarker != null)
				endMarker.setVisible(false);
		}
	}

	protected void addPolyline(Polyline p) {
		// MapPanel.getMap().addOverlay(p);
		//
		// namePerPolyline.put(p, name);
		//
		// // final InfoWindowContent content =
		// // createInfoBubbleContent();
		//
		// p.addPolylineClickHandler(new PolylineClickHandler()
		// {
		//
		// public void onClick(PolylineClickEvent event)
		// {
		// Polyline sender = event.getSender();
		// // LatLng point = event.getLatLng();
		// String routeName = namePerPolyline.get(sender);
		// if (selectedRoute != null)
		// {
		// selectedRoute.deSelect();
		// RoutePanel.highlight(previousRouteName, false);
		// InfoPanel.highlight(previousRouteName, false);
		// }
		// // RoutePanel.select(namePerPolyline.get(event.getSender()));
		// RoutePanel.select(routeName);
		// // MapPanel.getMap().getInfoWindow().open(point, content);
		// RoutePanel.highlight(routeName, true);
		// InfoPanel.highlight(routeName, true);
		// previousRouteName = routeName;
		// }
		// });
	}

	// public void setInfoBubbleContent(InfoWindowContent infoBubbleContent)
	// {
	// this.infoBubbleContent = infoBubbleContent;
	// }

	/**
	 * Creates a string in HTML that provides information about this Route.
	 * 
	 * Includes a link to bikely.com even though this route may not have come
	 * from there.
	 * 
	 * @param name
	 * @return protected InfoWindowContent createInfoBubbleContent() { String
	 *         temp = Double.toString(distance); String distanceStr =
	 *         temp.substring(0, temp.indexOf('.')+2);
	 * 
	 *         initInfoContent(distanceStr); return new
	 *         InfoWindowContent(infoContent.toString()); }
	 */

	/**
	 * @param data
	 *            .name
	 * @param dStr
	 *            - String representation of the route's distance.
	 */
	protected void initInfoContent(String dStr) {
		infoContent = new StringBuffer();
		if (this.encodedTrack.getSourceUrl() == null) {
			infoContent
					.append("Route Name: ")
					.append(name)
					.append("<BR><a href=\"http://www.bikely.com/maps/bike-path/")
					.append(name).append("\">On bikely.com</a><BR>Distance: ")
					.append(dStr);
		} else {
			infoContent.append("Route Name: ").append(name)
					.append("<BR><a href=\"")
					.append(encodedTrack.getSourceUrl())
					.append("\">On RideWithGPS.com</a><BR>Distance: ")
					.append(dStr);
		}
	}

	/**
	 * @return
	 */
	public String getInfoContent() {
		if (infoContent == null) {
			initInfoContent("0.00");
		}
		return infoContent.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jettmarks.routes.client.bean.Route#setMap(com.google.gwt.maps.client
	 * .MapWidget)
	 */
	@Override
	public void setMap(MapWidget mapWidget) {
		super.setMap(mapWidget);
		if (mapWidget != null) {
			namePerPolyline.put(polyline, name);
			namePerPolyline.put(highlightedPolyline, name);
			// Mouse handlers are now being set by the Activity.
			// addMouseHandlers(polyline);
			// addMouseHandlers(highlightedPolyline);
		}
	}

	/**
	 * Replacement for Adding the Click Handlers.
	 * 
	 * @param clickMapHandler
	 */
	public void addHandlerRegistration(ClickMapHandler clickMapHandler) {
		polyline.addClickHandler(clickMapHandler);
		highlightedPolyline.addClickHandler(clickMapHandler);
	}

}
