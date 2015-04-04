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
 * Created Sep 1, 2014
 */
package com.jettmarks.bikeTrains.client.rep;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jettmarks.bikeTrains.client.bean.DisplayGroupDTO;
import com.jettmarks.bikeTrains.client.service.GetTagsAsync;

/**
 * Holds a list of current events.
 * 
 * @author jett
 */
public class EventContainer {
	private static HashMap<String, DisplayGroupDTO> eventMap = new HashMap<String, DisplayGroupDTO>();
	private static EventContainer INSTANCE = new EventContainer();
	private static boolean requestPending = false;

	public static DisplayGroupDTO getEvent(String eventName) {
		if (eventMap.containsKey(eventName)) {
			return eventMap.get(eventName);
		} else {
			if (!requestPending) {
				requestPending = true;
				GetTagsAsync tagService = GetTagsAsync.Util.getInstance();
				tagService.getBikeTrain(eventName,
						INSTANCE.new GetBikeTrainCallback<DisplayGroupDTO>());
			}
			return null;
		}
	}

	public static void putEvent(String eventName, DisplayGroupDTO event) {
		eventMap.put(eventName, event);
	}

	/**
	 * Description.
	 * 
	 * @author jett
	 */
	public class GetBikeTrainCallback<T> implements
			AsyncCallback<DisplayGroupDTO> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.
		 * Throwable)
		 */
		@Override
		public void onFailure(Throwable caught) {
			requestPending = false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.
		 * Object)
		 */
		@Override
		public void onSuccess(DisplayGroupDTO result) {
			requestPending = false;
			if (result != null) {
				String displayGroupName = result.getDisplayName();
				eventMap.put(displayGroupName, result);
			}
		}

	}
}
