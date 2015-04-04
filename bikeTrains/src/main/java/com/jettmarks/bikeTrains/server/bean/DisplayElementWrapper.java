/**
 * 
 */
package com.jettmarks.bikeTrains.server.bean;

import com.jettmarks.bikeTrains.client.bean.DisplayElementDTO;
import com.jettmarks.routes.client.bean.DisplayElementType;
import com.jettmarks.routes.server.bean.DisplayElement;

/**
 * Description.
 * 
 * @author jett
 */
public class DisplayElementWrapper {

	/**
	 * @param de
	 * @return
	 */
	public static DisplayElement getDisplayElement(DisplayElementDTO deDto) {
		DisplayElement de = new DisplayElement();
		de.setClassName(deDto.getClassName());
		de.setSourceId(deDto.getSourceId());
		switch (deDto.getType()) {
		case OVERLAY:
			de.setType(DisplayElementType.OVERLAY);
			break;
		case ROUTE:
			de.setType(DisplayElementType.ROUTE);
			break;
		case SEGMENTED:
			de.setType(DisplayElementType.SEGMENTED);
			break;
		}
		return de;
	}

	/**
	 * @param de
	 * @return
	 */
	public static DisplayElementDTO getDisplayElementDTO(DisplayElement de) {
		DisplayElementDTO deDto = new DisplayElementDTO();
		deDto.setClassName(de.getClassName());
		deDto.setSourceId(de.getSourceId());
		deDto.setId(de.getId());
		// deDto.setType(de.getType());
		switch (de.getType()) {
		case OVERLAY:
			deDto.setType(com.jettmarks.bikeTrains.client.bean.DisplayElementType.OVERLAY);
			break;
		case ROUTE:
			deDto.setType(com.jettmarks.bikeTrains.client.bean.DisplayElementType.ROUTE);
			break;
		case SEGMENTED:
			deDto.setType(com.jettmarks.bikeTrains.client.bean.DisplayElementType.SEGMENTED);
			break;
		}
		return deDto;
	}

}
