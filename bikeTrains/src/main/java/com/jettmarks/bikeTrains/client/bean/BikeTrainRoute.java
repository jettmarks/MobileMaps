/**
 *   Copyright 2010 Jett Marks
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
 * Created Apr 24, 2010
 */
package com.jettmarks.bikeTrains.client.bean;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.overlays.Marker;

/**
 * Description.
 * 
 * @author jett
 */
public class BikeTrainRoute extends DisplayOnlyRoute implements
	Comparable<BikeTrainRoute> {
    private BikeTrainDTO bikeTrain = null;

    /**
     * @return the bikeTrain
     */
    public BikeTrainDTO getBikeTrain() {
	return bikeTrain;
    }

    public BikeTrainRoute(String name, BikeTrainDTO bikeTrain) {
	super(name);
	this.bikeTrain = bikeTrain;
    }

    /**
     * Provides Route details not found on the route itself.
     * 
     * @see com.jettmarks.routes.client.bean.DisplayOnlyRoute#createInfoBubbleContent(java.lang.String)
     * @Override protected InfoWindowContent createInfoBubbleContent() { String
     *           temp = Double.toString(distance); String dStr =
     *           temp.substring(0, temp.indexOf('.')+3); if (infoContent ==
     *           null) { initInfoContent(dStr); } return new InfoWindowContent(
     *           infoContent.toString() ); }
     */

    /**
     * Sets up the string used for Information about this route in HTML.
     * 
     * @see com.jettmarks.routes.client.bean.DisplayOnlyRoute#initInfoContent(java.lang.String)
     */
    @Override
    protected void initInfoContent(String dStr) {
	infoContent = new StringBuffer();
	infoContent.append("Route Name: " + displayName == null ? name
		: displayName + "<BR>Distance: " + dStr + "<BR>Departs: "
			+ bikeTrain.getDepartureTime());

	if (bikeTrain.getArrivalTime() != null)
	    infoContent.append("<BR>Arrives: " + bikeTrain.getArrivalTime());

	if (this.extSourceURL == null) {
	    addBikelyCueSheet();
	} else {
	    addRWGPSCueSheet();
	}
	infoContent.append("<P>Leader: " + bikeTrain.getLeaderName());
	infoContent.append("<BR>Leader Email: " + bikeTrain.getLeaderEmail());
	if (bikeTrain.getLeaderPhone() != null) {
	    infoContent.append("<BR>Leader Phone: "
		    + bikeTrain.getLeaderPhone());
	}
	if (bikeTrain.getNotes() != null) {
	    infoContent.append("<BR>Note: " + bikeTrain.getNotes());
	}
    }

    /**
     * Cue Sheet specific to Ride with GPS.
     * 
     * Based on the base URL for the route.
     */
    private void addRWGPSCueSheet() {
	String baseUrl = this.encodedTrack.getSourceUrl();
	infoContent.append("<BR><a href=\"" + baseUrl + "/cue_sheet"
		+ "\" target=\"_blank\""
		+ ">Cue Sheet</a> (and waypoints if any)");
    }

    /**
   * 
   */
    private void addBikelyCueSheet() {
	infoContent
		.append("<BR><a href=\"http://www.bikely.com/cuesheet/route/"
			+ name + "\" target=\"_blank\""
			+ ">Cue Sheet</a> (and waypoints if any)");
    }

    /**
     * @return
     */
    public Marker getStartMarker() {
	return startMarker;
    }

    /**
     * @return
     */
    public Marker getEndMarker() {
	return endMarker;
    }

    /**
     * Calls super before adding the markers to the map.
     * 
     * @see com.jettmarks.routes.client.bean.Route#setMap(com.google.gwt.maps.client
     *      .MapWidget)
     */
    @Override
    public void setMap(MapWidget mapWidget) {
	super.setMap(mapWidget);
	startMarker.setMap(mapWidget);
	endMarker.setMap(mapWidget);
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(BikeTrainRoute arg0) {
	return this.getDisplayName().compareTo(arg0.getDisplayName());
    }

}
