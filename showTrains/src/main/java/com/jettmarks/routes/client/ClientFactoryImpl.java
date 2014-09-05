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
import com.jettmarks.routes.client.activities.GetInvolvedView;
import com.jettmarks.routes.client.forms.FormsView;
import com.jettmarks.routes.client.forms.FormsViewGwtImpl;
import com.jettmarks.routes.client.ui.AboutView;
import com.jettmarks.routes.client.ui.AboutViewGwtImpl;
import com.jettmarks.routes.client.ui.EventSelectionView;
import com.jettmarks.routes.client.ui.EventSelectionViewGwtImpl;
import com.jettmarks.routes.client.ui.EventView;
import com.jettmarks.routes.client.ui.EventViewTabbedGwtImpl;
import com.jettmarks.routes.client.ui.GetInvolvedViewGwtImpl;
import com.jettmarks.routes.client.ui.HomeView;
import com.jettmarks.routes.client.ui.HomeViewGwtImpl;
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


    /*
     * (non-Javadoc)
     * 
     * @see com.jettmarks.routes.client.ClientFactory#getEventView()
     */
    @Override
    public EventView getEventView() {
	if (eventView == null) {
	    eventView = new EventViewTabbedGwtImpl();
	}
	return eventView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jettmarks.routes.client.ClientFactory#getRouteDetailsView()
     */
    @Override
    public RouteDetailsView getRouteDetailsView() {
	if (routeDetailsView == null) {
	    routeDetailsView = new RouteDetailsViewGwtImpl();
	}
	return routeDetailsView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jettmarks.routes.client.ClientFactory#getEventSelectionView()
     */
    @Override
    public EventSelectionView getEventSelectionView() {
	if (eventSelectionView == null) {
	    eventSelectionView = new EventSelectionViewGwtImpl();
	}
	return eventSelectionView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jettmarks.routes.client.ClientFactory#getHomeView()
     */
    @Override
    public HomeView getHomeView() {
	if (homeView == null) {
	    homeView = new HomeViewGwtImpl();
	}
	return homeView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jettmarks.routes.client.ClientFactory#getAboutView()
     */
    @Override
    public AboutView getAboutView() {
	if (aboutView == null) {
	    aboutView = new AboutViewGwtImpl();
	}
	return aboutView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jettmarks.routes.client.ClientFactory#getGetInvolvedView()
     */
    @Override
    public GetInvolvedView getGetInvolvedView() {
	if (getInvolvedView == null) {
	    getInvolvedView = new GetInvolvedViewGwtImpl();
	}
	return getInvolvedView;
    }

}
