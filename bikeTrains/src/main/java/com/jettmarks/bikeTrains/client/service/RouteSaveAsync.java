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
 * Created on Apr 5, 2009
 */
package com.jettmarks.bikeTrains.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jettmarks.bikeTrains.client.bean.BikeTrainDTO;
import com.jettmarks.routes.client.bean.SuitabilityRating;

/**
 * 
 * @author jett
 * 
 */
public interface RouteSaveAsync {
	/** Save route contents as particular file specified as a resource string. */
	public void saveAs(String fileResource, String fileContents,
			AsyncCallback<Integer> callback);

	/** Save route contents as named file under specified tagList. */
	public void saveAs(String fileResource, String[] tagList,
			String fileContents, AsyncCallback<Integer> callback);

	void saveAs(String fileResource, String[] tagList, String fileContents,
			SuitabilityRating rating, AsyncCallback<Integer> callback);

	void saveBikeTrain(String fileResource, String fileContents,
			BikeTrainDTO bikeTrain, Integer displayGroupId,
			AsyncCallback<Integer> callback);

	void updateBikeTrain(BikeTrainDTO bikeTrain, AsyncCallback<Integer> callback);

	void copyBikeTrain(BikeTrainDTO bikeTrain, Integer displayGroupId,
			AsyncCallback<Integer> callback);

}
