/**
 *   Copyright 2014 Jett Marks
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
 * Created May 10, 2014
 * 
 * Depends on Google Map API 3.x.
 */
package com.jettmarks.routes.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.Point;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.maps.client.overlays.MarkerImage;
import com.google.gwt.maps.client.overlays.MarkerOptions;

/**
 * Factors out the construction of specific markers by an enumerated type.
 * 
 * @author jett
 */
public class MarkerFactory {
	public enum MarkerType {
		START_MARKER, END_MARKER, SNAP_MARKER
	};

  private static MarkerImage beginIcon = MarkerImage.newInstance(GWT.getModuleName()
                                                                 + "/images/dd-start.png");

  private static MarkerImage endIcon = MarkerImage.newInstance(GWT.getModuleName()
                                                               + "/images/dd-end.png");
//	private static MarkerImage crosshairIcon = MarkerImage
//			.newInstance("images/squareCrosshair.png");

	private static MarkerOptions beginOptions;
	private static MarkerOptions endOptions;
//	private static MarkerOptions crosshairOptions;

	static {
		beginIcon.setAnchor(Point.newInstance(10, 32));
		beginOptions = MarkerOptions.newInstance();
		beginOptions.setIcon(beginIcon);

		endIcon.setAnchor(Point.newInstance(10, 32));
		endOptions = MarkerOptions.newInstance();
		endOptions.setIcon(endIcon);

		// int[] mapArray =
		// { 0, 0, 32, 0, 32, 32, 0, 32 };
		// crosshairIcon.setImageMap(mapArray);
		// // crosshairIcon.setIconAnchor(Point.newInstance(16, 16));
		// crosshairIcon.setIconSize(Size.newInstance(32, 32));
		// crosshairIcon.setShadowURL(null);
		// crosshairOptions = MarkerOptions.newInstance(crosshairIcon);
	}

	/**
	 * Creates an instance of a Marker with the properties defined statically in
	 * this Class.
	 * 
	 * @param markerType
	 * @param point
	 * @return - Marker with the desired characteristics.
	 */
	public static Marker getInstance(MarkerType markerType, LatLng point) {
		Marker marker = null;
		switch (markerType) {
		case START_MARKER:
			beginOptions.setPosition(point);
			marker = Marker.newInstance(beginOptions);
			marker.setIcon(beginIcon);
			break;
		case END_MARKER:
			endOptions.setPosition(point);
			marker = Marker.newInstance(endOptions);
			marker.setIcon(endIcon);
			break;
		case SNAP_MARKER:
			// return new Marker(point, crosshairOptions);
		default:
		}
		return marker;
	}

}
