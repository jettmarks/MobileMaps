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
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.jettmarks.routes.client.activities.DisplayGroupListView;
import com.jettmarks.routes.client.activities.DisplayGroupListViewGwtImpl;
import com.jettmarks.routes.client.forms.FormsView;
import com.jettmarks.routes.client.forms.FormsViewGwtImpl;
import com.jettmarks.routes.client.ui.AboutView;
import com.jettmarks.routes.client.ui.AboutViewGwtImpl;
import com.jettmarks.routes.client.ui.ConductorView;
import com.jettmarks.routes.client.ui.ConductorViewGwtImpl;
import com.jettmarks.routes.client.ui.EventSelectionView;
import com.jettmarks.routes.client.ui.EventSelectionViewGwtImpl;
import com.jettmarks.routes.client.ui.EventView;
import com.jettmarks.routes.client.ui.EventViewBaseImpl;
import com.jettmarks.routes.client.ui.EventViewListOnlyGwtImpl;
import com.jettmarks.routes.client.ui.EventViewMapOnlyGwtImpl;
import com.jettmarks.routes.client.ui.EventViewTabbedGwtImpl;
import com.jettmarks.routes.client.ui.FindRouteView;
import com.jettmarks.routes.client.ui.FindRouteViewGwtImpl;
import com.jettmarks.routes.client.ui.GetInvolvedView;
import com.jettmarks.routes.client.ui.GetInvolvedViewGwtImpl;
import com.jettmarks.routes.client.ui.HomeView;
import com.jettmarks.routes.client.ui.HomeViewGwtImpl;
import com.jettmarks.routes.client.ui.ResourcesView;
import com.jettmarks.routes.client.ui.ResourcesViewGwtImpl;
import com.jettmarks.routes.client.ui.RouteDetailsView;
import com.jettmarks.routes.client.ui.RouteDetailsViewGwtImpl;

/**
 * @author Daniel Kurka
 * 
 */
public class ClientFactoryImpl implements ClientFactory {

    private EventBus eventBus;

    private PlaceController placeController;

    private FormsView formsView;

    private DisplayGroupListView displayGroupListView;

    private EventView eventView;

    private RouteDetailsView routeDetailsView;

    private EventSelectionView eventSelectionView;

    private HomeView homeView;

    private AboutView aboutView;

    private GetInvolvedView getInvolvedView;

    private FindRouteView findRouteView;

    private ConductorView conductorView;

    private ResourcesView resourcesView;

    private EventViewBaseImpl eventListView;

    private EventViewBaseImpl eventMapView;

    private EventViewBaseImpl eventTabbedView;

    public ClientFactoryImpl() {
	eventBus = new SimpleEventBus();

	placeController = new PlaceController(eventBus);

    }

    @Override
    public EventBus getEventBus() {
	return eventBus;
    }

    @Override
    public PlaceController getPlaceController() {
	return placeController;
    }

    @Override
    public FormsView getFormsView() {
	if (formsView == null) {
	    formsView = new FormsViewGwtImpl();
	}
	return formsView;
    }

    /**
     * @see com.jettmarks.routes.client.ClientFactory#getDisplayGroupListView()
     */
    @Override
    public DisplayGroupListView getDisplayGroupListView() {
	if (displayGroupListView == null) {
	    displayGroupListView = new DisplayGroupListViewGwtImpl();
	}
	return displayGroupListView;
    }

    /**
     * @see com.jettmarks.routes.client.ClientFactory#getEventView()
     */
    @Override
    public EventView getEventView() {
	if (eventView == null) {
	    eventView = new EventViewTabbedGwtImpl();
	}
	return eventView;
    }

    /**
     * @see com.jettmarks.routes.client.ClientFactory#getEventListView()
     */
    @Override
    public EventView getEventListView() {
	if (eventListView == null) {
	    eventListView = new EventViewListOnlyGwtImpl();
	}
	return eventListView;
    }

    /**
     * @see com.jettmarks.routes.client.ClientFactory#getEventMapView()
     */
    @Override
    public EventView getEventMapView() {
	if (eventMapView == null) {
	    eventMapView = new EventViewMapOnlyGwtImpl();
	}
	return eventMapView;
    }

    /**
     * @see com.jettmarks.routes.client.ClientFactory#getEventTabbedView()
     */
    @Override
    public EventView getEventTabbedView() {
	if (eventTabbedView == null) {
	    eventTabbedView = new EventViewTabbedGwtImpl();
	}
	return eventTabbedView;
    }

    /**
     * @see com.jettmarks.routes.client.ClientFactory#getRouteDetailsView()
     */
    @Override
    public RouteDetailsView getRouteDetailsView() {
	if (routeDetailsView == null) {
	    routeDetailsView = new RouteDetailsViewGwtImpl();
	}
	return routeDetailsView;
    }

    /**
     * @see com.jettmarks.routes.client.ClientFactory#getEventSelectionView()
     */
    @Override
    public EventSelectionView getEventSelectionView() {
	if (eventSelectionView == null) {
	    eventSelectionView = new EventSelectionViewGwtImpl();
	}
	return eventSelectionView;
    }

    /**
     * @see com.jettmarks.routes.client.ClientFactory#getHomeView()
     */
    @Override
    public HomeView getHomeView() {
	if (homeView == null) {
	    homeView = new HomeViewGwtImpl();
	}
	return homeView;
    }

    /**
     * @see com.jettmarks.routes.client.ClientFactory#getAboutView()
     */
    @Override
    public AboutView getAboutView() {
	if (aboutView == null) {
	    aboutView = new AboutViewGwtImpl();
	}
	return aboutView;
    }

    /**
     * @see com.jettmarks.routes.client.ClientFactory#getGetInvolvedView()
     */
    @Override
    public GetInvolvedView getGetInvolvedView() {
	if (getInvolvedView == null) {
	    getInvolvedView = new GetInvolvedViewGwtImpl();
	}
	return getInvolvedView;
    }

    /**
     * @see com.jettmarks.routes.client.ClientFactory#getFindRouteView()
     */
    @Override
    public FindRouteView getFindRouteView() {
	if (findRouteView == null) {
	    findRouteView = new FindRouteViewGwtImpl();
	}
	return findRouteView;
    }

    /**
     * @see com.jettmarks.routes.client.ClientFactory#getConductorView()
     */
    @Override
    public ConductorView getConductorView() {
	if (conductorView == null) {
	    conductorView = new ConductorViewGwtImpl();
	}
	return conductorView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jettmarks.routes.client.ClientFactory#getResourcesView()
     */
    @Override
    public ResourcesView getResourcesView() {
	if (resourcesView == null) {
	    resourcesView = new ResourcesViewGwtImpl();
	}
	return resourcesView;
    }

}
