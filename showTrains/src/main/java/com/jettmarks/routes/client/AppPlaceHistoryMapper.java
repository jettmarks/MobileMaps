/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.jettmarks.routes.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.jettmarks.routes.client.place.AboutPlace;
import com.jettmarks.routes.client.place.ConductPlace;
import com.jettmarks.routes.client.place.EventPlace;
import com.jettmarks.routes.client.place.FindRoutePlace;
import com.jettmarks.routes.client.place.GetInvolvedPlace;
import com.jettmarks.routes.client.place.ResourcesPlace;

/**
 * @author Daniel Kurka
 * 
 */
@WithTokenizers({ EventPlace.Tokenizer.class, AboutPlace.Tokenizer.class,
	FindRoutePlace.Tokenizer.class, ConductPlace.Tokenizer.class,
	GetInvolvedPlace.Tokenizer.class, ResourcesPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
