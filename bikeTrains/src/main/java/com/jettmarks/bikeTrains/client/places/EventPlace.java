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
 * Created Jun 02, 2014
 */
package com.jettmarks.bikeTrains.client.places;

import java.util.Date;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.jettmarks.bikeTrains.client.bean.DisplayGroupDTO;
import com.jettmarks.bikeTrains.client.rep.EventContainer;

public class EventPlace extends Place {
	/** Event's Name, URL, and time. */
	private DisplayGroupDTO event;
	/** Human name for the Event; shows as the title. */
	private String description;

	/** Forms the URL for the Event. */
	private String displayGroupName;

	/** Date of the event. */
	private Date eventDate;

	/**
	 * An EventPlace specifies the displayGroupName which matches one-to-one
	 * with a date and a description of the event, as well as bundling the bike
	 * trains that are being conducted as part of that event.
	 * 
	 * @param displayGroupName
	 */
	public EventPlace(String displayGroupName) {
		super();
		this.displayGroupName = displayGroupName;
		DisplayGroupDTO dgDto = EventContainer.getEvent(displayGroupName);
		if (dgDto != null) {
			this.description = dgDto.getDescription();
			this.event = dgDto;
			this.eventDate = dgDto.getEventDate();
		}
	}

	/**
   * 
   */
	public EventPlace() {
	}

	/**
	 * @param dispGroup
	 * @param description
	 */
	public EventPlace(String dispGroup, String description) {
		super();
		this.displayGroupName = dispGroup;
		// this.description = description;
		DisplayGroupDTO dgDto = EventContainer.getEvent(displayGroupName);
		this.description = dgDto.getDescription();
		this.event = dgDto;
		this.eventDate = dgDto.getEventDate();
	}

	/**
	 * Sets all values for the EventPlace from the displayGroup chosen.
	 * 
	 * @param displayGroup
	 */
	public EventPlace(DisplayGroupDTO displayGroup) {
		super();
		this.event = displayGroup;
		this.displayGroupName = displayGroup.getDisplayName();
		this.description = displayGroup.getDescription();
		this.eventDate = displayGroup.getEventDate();
	}

	public static class Tokenizer implements PlaceTokenizer<EventPlace> {

		@Override
		public EventPlace getPlace(String token) {
			String displayGroupName = token;
			return new EventPlace(displayGroupName);
		}

		@Override
		public String getToken(EventPlace place) {
			return place.getDisplayGroupName();
		}

	}

	/**
	 * @param name
	 */
	public void setDisplayGroupName(String name) {
		this.displayGroupName = name;
	}

	/**
	 * @return
	 */
	public String getDisplayGroupName() {
		return displayGroupName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the event
	 */
	public DisplayGroupDTO getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(DisplayGroupDTO event) {
		this.event = event;
	}

	/**
	 * @return the eventDate
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate
	 *            the eventDate to set
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

}