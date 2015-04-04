/**
 * 
 */
package com.jettmarks.bikeTrains.server.bean;

import com.jettmarks.bikeTrains.client.bean.SuitabilityRating;
import com.jettmarks.bikeTrains.client.bean.SuitabilitySegmentDTO;
import com.jettmarks.routes.server.bean.SuitabilitySegment;

/**
 * Description.
 * 
 * @author jett
 */
public class SuitabilitySegmentWrapper {

	/**
	 * @param sSegment
	 * @return
	 */
	public static SuitabilitySegmentDTO getSuitabilitySegment(
			SuitabilitySegment sSegment) {
		SuitabilitySegmentDTO ss = new SuitabilitySegmentDTO();
		switch (sSegment.getRating()) {
		case GREEN:
			ss.setRating(SuitabilityRating.GREEN);
			break;
		case YELLOW:
			ss.setRating(SuitabilityRating.YELLOW);
			break;
		case RED:
			ss.setRating(SuitabilityRating.RED);
			break;
		case UNRATED:
			ss.setRating(SuitabilityRating.UNRATED);
			break;
		}
		ss.setRouteName(sSegment.getRouteName());
		return ss;
	}

}
