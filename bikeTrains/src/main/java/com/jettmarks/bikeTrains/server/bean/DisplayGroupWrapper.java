/**
 * 
 */
package com.jettmarks.bikeTrains.server.bean;

import java.util.HashSet;
import java.util.Set;

import com.jettmarks.bikeTrains.client.bean.DisplayElementDTO;
import com.jettmarks.bikeTrains.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.server.bean.DisplayElement;
import com.jettmarks.routes.server.bean.DisplayGroup;

/**
 * Turns DisplayGroup into DisplayGroupDTO.
 * 
 * @author jett
 */
public class DisplayGroupWrapper {

	/**
	 * @param displayGroupDto
	 * @return
	 */
	public static DisplayGroup getDisplayGroup(DisplayGroupDTO displayGroupDto) {
		DisplayGroup dg = new DisplayGroup();
		dg.setDescription(displayGroupDto.getDescription());
		dg.setDisplayName(displayGroupDto.getDisplayName());
		dg.setEventDate(displayGroupDto.getEventDate());
		Set<DisplayElement> displayElements = new HashSet<DisplayElement>();
		for (DisplayElementDTO de : displayGroupDto.getElements()) {
			displayElements.add(DisplayElementWrapper.getDisplayElement(de));
		}
		dg.setElements(displayElements);
		return dg;
	}

	/**
	 * @param dg
	 * @return
	 */
	public static DisplayGroupDTO getDTO(DisplayGroup dg) {
		DisplayGroupDTO dgDto = new DisplayGroupDTO();
		dgDto.setDescription(dg.getDescription());
		dgDto.setDisplayName(dg.getDisplayName());
		dgDto.setId(dg.getId());
		dgDto.setEventDate(dg.getEventDate());
		return dgDto;
	}

}
