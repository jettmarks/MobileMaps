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
 * Created Jul 18, 2009
 */
package com.jettmarks.bikeTrains.server;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.jettmarks.bikeTrains.client.bean.EncodedTrack;
import com.jettmarks.bikeTrains.client.bean.FacilityType;
import com.jettmarks.bikeTrains.client.service.GetRoute;
import com.jettmarks.bikeTrains.server.bean.BikeTrainWrapper;
import com.jettmarks.bikeTrains.server.common.Facility;
import com.jettmarks.bikeTrains.server.common.Hill;
import com.jettmarks.bikeTrains.server.common.RemoteServiceServletSeparatePaths;
import com.jettmarks.gmaps.encoder.ParseGPX;
import com.jettmarks.gmaps.encoder.PolylineEncoder;
import com.jettmarks.gmaps.encoder.Track;
import com.jettmarks.gmaps.encoder.Trackpoint;
import com.jettmarks.routes.util.BikeTrain2010;

/**
 *
 * @author jett
 */
public class GetRouteImpl extends RemoteServiceServletSeparatePaths implements
		GetRoute {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GetRouteImpl.class);

	/** * */
	private static final long serialVersionUID = 4512829620636985694L;
	private static ParseGPX parser = new ParseGPX();

	/** If a Save Tag is not given, use this. */
	private static final String DEFAULT_SHOW_TAG = "master";

	/** The default tag for showing/presenting routes from LocalDrive. */
	private static String showTag = System.getProperty("show.tag",
			DEFAULT_SHOW_TAG);
	private String tags[] = new String[1];

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jettmarks.gmaps.client.service.GetRoute#getEncodedTrack(java.lang
	 * .String, java.lang.String)
	 */
	public EncodedTrack getEncodedTrack(String routeName, String routeSourceName) {
		showTag = System.getProperty("show.tag", DEFAULT_SHOW_TAG);
		tags[0] = showTag;
		return getEncodedTrack(routeName, routeSourceName, tags);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jettmarks.gmaps.client.service.GetRoute#getEncodedTrack(java.lang
	 * .String, java.lang.String, java.lang.String[])
	 */
	public EncodedTrack getEncodedTrack(String routeName,
			String routeSourceName, String[] tagList) {
		if (logger.isDebugEnabled()) {
			logger.debug("getEncodedTrack(" + routeName + ", "
					+ routeSourceName + ", " + tagList + ") - start");
		}

		RSSProxyImpl rssProxyImpl = new RSSProxyImpl();
		String rawTrack = rssProxyImpl.getRoute(routeName, routeSourceName,
				tagList);
		if (rawTrack == null) {
			logger.error("Unable to find route " + routeName + " at "
					+ routeSourceName);
			return null;
		}
		Track track = parser.getTrackFromGPX(rawTrack);
		PolylineEncoder encoder = new PolylineEncoder();
		HashMap<String, String> hash = encoder.dpEncode(track);

		EncodedTrack encodedTrack = new EncodedTrack();
		int pointsCount = track.getTrackpoints().size();
		double[] lats = new double[pointsCount];
		double[] lons = new double[pointsCount];
		int i = 0;
		for (Trackpoint tp : track.getTrackpoints()) {
			lats[i] = tp.getLatDouble();
			lons[i++] = tp.getLonDouble();
		}
		encodedTrack.setLats(lats);
		encodedTrack.setLons(lons);
		encodedTrack.setRouteName(routeName);
		encodedTrack.setEncodedLevels((String) hash.get("encodedLevels"));
		encodedTrack.setEncodedPoints((String) hash.get("encodedPoints"));
		encodedTrack.setBounds(encoder.getBounds());
		encodedTrack.setSourceUrl(track.getSourceUrl());
		encodedTrack.setDisplayName(track.getDisplayName());

		// Setup the first and last point of the route
		ArrayList<Trackpoint> trackPoints = track.getTrackpoints();
		Trackpoint firstPoint = trackPoints.get(0);
		Trackpoint lastPoint = trackPoints.get(trackPoints.size() - 1);
		encodedTrack.setStartLat(firstPoint.getLatDouble());
		encodedTrack.setStartLon(firstPoint.getLonDouble());
		encodedTrack.setEndLat(lastPoint.getLatDouble());
		encodedTrack.setEndLon(lastPoint.getLonDouble());

		// Specific settings based on the type of route
		encodedTrack.setHillCategory(Hill.getCategory(routeName));
		encodedTrack.setMaxSlope(Hill.getMaxSlope(routeName));
		encodedTrack.setDistance(Double.parseDouble((String) hash
				.get("TotalDistance")));
		encodedTrack.setFacilityType(Facility.getFacilityType(routeName));
		encodedTrack.setBikeTrain(BikeTrainWrapper
				.getBikeTrainDTO(BikeTrain2010.getInstance(routeName)));

		// Later, when we read from the KML, we'll be able to use the actual
		// routeSourceName, but for now we fake it out. If it has any
		// FacilityType,
		// we say that it is KML.
		if (encodedTrack.getFacilityType() != FacilityType.UNKNOWN) {
			encodedTrack.setRouteSourceName("KML");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getEncodedTrack(String, String, String[]) - end");
		}
		return encodedTrack;
	}
}
