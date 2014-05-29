/**
 * 
 */
package com.jettmarks.routes.server.bean;

import com.jettmarks.routes.client.bean.SuitabilitySegmentDTO;

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
		ss.setRating(sSegment.getRating());
		ss.setRouteName(sSegment.getRouteName());
		return ss;
	}

}
