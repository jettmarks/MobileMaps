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

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.jettmarks.routes.client.activities.DisplayGroupListView;
import com.jettmarks.routes.client.forms.FormsView;
import com.jettmarks.routes.client.ui.AboutView;
import com.jettmarks.routes.client.ui.ConductorView;
import com.jettmarks.routes.client.ui.EventSelectionView;
import com.jettmarks.routes.client.ui.EventView;
import com.jettmarks.routes.client.ui.FindRouteView;
import com.jettmarks.routes.client.ui.GetInvolvedView;
import com.jettmarks.routes.client.ui.HomeView;
import com.jettmarks.routes.client.ui.ResourcesView;
import com.jettmarks.routes.client.ui.RouteDetailsView;

public interface ClientFactory {

    public EventBus getEventBus();

    public PlaceController getPlaceController();

    public FormsView getFormsView();

    public DisplayGroupListView getDisplayGroupListView();

    public EventView getEventView();

    public RouteDetailsView getRouteDetailsView();

    /**
     * @return
     */
    public EventSelectionView getEventSelectionView();

    /**
     * @return
     */
    public HomeView getHomeView();

    /**
     * @return
     */
    public AboutView getAboutView();

    /**
     * @return
     */
    public GetInvolvedView getGetInvolvedView();

    /**
     * @return
     */
    public FindRouteView getFindRouteView();

    /**
     * @return
     */
    public ConductorView getConductorView();

    /**
     * @return
     */
    public ResourcesView getResourcesView();

    /**
     * @return
     */
    public EventView getEventListView();

    /**
     * @return
     */
    public EventView getEventMapView();

}
