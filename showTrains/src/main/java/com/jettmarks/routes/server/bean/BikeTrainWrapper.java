/**
 * 
 */
package com.jettmarks.routes.server.bean;

import com.jettmarks.routes.client.bean.BikeTrainDTO;

/**
 * Takes a BikeTrain and turns it into a BikeTrainDTO.
 * 
 * @author jett
 */
public class BikeTrainWrapper {

	/**
	 * @param instance
	 */
	public static BikeTrainDTO getBikeTrainDTO(BikeTrain bt) {
		BikeTrainDTO btDto = new BikeTrainDTO();

		if (bt != null)
		{
			btDto.setId(bt.getId());
			btDto.setRouteName(bt.getRouteName());
			btDto.setLeaderName(bt.getLeaderName());
			btDto.setLeaderEmail(bt.getLeaderEmail());
			btDto.setLeaderPhone(bt.getLeaderPhone());
			btDto.setDepartureTime(bt.getDepartureTime());
			btDto.setArrivalTime(bt.getArrivalTime());
			btDto.setNotes(bt.getNotes());
		}
		return btDto;
	}

}
