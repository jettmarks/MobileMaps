/**
 * 
 */
package com.jettmarks.routes.server.bean;

import com.jettmarks.routes.client.bean.DisplayElementDTO;

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
		de.setType(deDto.getType());
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
		deDto.setType(de.getType());
		return deDto;
	}

}
