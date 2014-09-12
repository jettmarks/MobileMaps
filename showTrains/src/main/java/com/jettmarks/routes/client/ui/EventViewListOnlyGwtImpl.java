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
 * Created Jun 2, 2014
 */
package com.jettmarks.routes.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.maps.client.overlays.Marker;
import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;
import com.jettmarks.routes.client.bean.BikeTrainRoute;
import com.jettmarks.routes.client.bean.Route;
import com.jettmarks.routes.client.rep.RouteContainer;
import com.jettmarks.routes.client.rep.RouteContainerFactory;
import com.jettmarks.routes.client.ui.MarkerFactory.MarkerType;

/**
 * Description.
 * 
 * @author jett
 */
public class EventViewListOnlyGwtImpl extends EventViewBaseImpl implements
	EventView {
    private MapWidget mapWidget;

    private static LatLngBounds mapBounds = null;

    private List<Route> routes = new ArrayList<Route>();

    private String description;

    private String displayGroupName;

    private CellList<Route> listWidget;

    private ScrollPanel scrollPanel;
    private HeaderButtonBar headerButtonBar;

    public EventViewListOnlyGwtImpl() {
	// super();
	main = new LayoutPanel();
	main.setSize("100%", "100%");

	// Take care of the header for navigation
	setupHeader();
	listWidget = prepareList();
	setupListPanel(listWidget);

	main.add(headerButtonBar);
	main.add(scrollPanel);
    }


    /**
     * @param listWidget
     */
    private void setupListPanel(CellList<Route> listWidget) {
	scrollPanel = new ScrollPanel();
	scrollPanel.add(listWidget);
	scrollPanel.setSize("100%", "100%");
	// scrollPanel.setHeight(ScreenSize.getHeight() - 80 + "px");
	scrollPanel.setScrollingEnabledX(true);
    }

    /**
     * Setup the List of Bike Trains.
     * 
     * This is responsible for responding to the changes of selection performed
     * on the list tab. This should probably be moved out to the activity.
     * 
     * @return
     */
    private CellList<Route> prepareList() {
	CellList<Route> cellList = new CellList<Route>(new RouteCell());
	cellList.addCellSelectedHandler(new CellSelectedHandler() {

	    // TODO: Move out to the activity
	    @Override
	    public void onCellSelected(CellSelectedEvent event) {
		RouteContainer rc = RouteContainerFactory.getRouteContainer();
		// Turn off any highlighted route
		BikeTrainRoute previouslySelectedRoute = (BikeTrainRoute) rc
			.getSelectedRoute();
		if (previouslySelectedRoute != null) {
		    previouslySelectedRoute.toggleHighlight();
		}

		// Turn on the selected route
		Route route = routes.get(event.getIndex());
		BikeTrainRoute bikeTrainRoute = (BikeTrainRoute) route;

		bikeTrainRoute.toggleHighlight();

		// Make the announcement (which might be able to handle the
		// other tasks too)
		RouteContainerFactory.getRouteContainer().setSelectedRoute(
			route);
	    }

	});
	return cellList;
    }


    /**
     * Adjusts bounds, adds markers and puts it on the mapWidget that is part of
     * this view.
     * 
     * Passed as a generic Route, but understood to be a BikeTrainRoute.
     * 
     * @see com.jettmarks.routes.client.ui.EventView#add(com.jettmarks.routes.client
     *      .bean.Route)
     */
    @Override
    public void add(Route route) {
	BikeTrainRoute bikeRoute = (BikeTrainRoute) route;
	// Take care of the map
	LatLngBounds routeBounds = bikeRoute.getBounds();
	if (mapBounds == null) {
	    mapBounds = routeBounds;
	} else {
	    mapBounds.extend(routeBounds.getNorthEast());
	    mapBounds.extend(routeBounds.getSouthWest());
	}
	routes.add(bikeRoute);
	addBeginEndMarkers(route, mapWidget);
	bikeRoute.setMap(mapWidget);
	bikeRoute.highlight(false);
    }

    /**
     * Called after last route has been loaded.
     */
    public void renderList() {
	listWidget.render(routes);
	scrollPanel.refresh();
    }

    /**
     * Hides details of figuring out where to put the markers on the route.
     * 
     * @param route
     * @param mapWidget2
     */
    private void addBeginEndMarkers(Route route, MapWidget mapWidget2) {
	if (route.getPoints() == null)
	    return;
	int pointCount = route.getPoints().length;
	if (pointCount == 0)
	    return;

	LatLng beginLatLng = route.getPoints()[0];
	LatLng endLatLng = route.getPoints()[pointCount - 1];
	Marker beginMarker = MarkerFactory.getInstance(MarkerType.START_MARKER,
		beginLatLng);
	Marker endMarker = MarkerFactory.getInstance(MarkerType.END_MARKER,
		endLatLng);
	beginMarker.setMap(mapWidget2);
	endMarker.setMap(mapWidget2);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jettmarks.routes.client.ui.EventView#resize()
     */
    @Override
    public void resize() {
	renderList();
	scrollPanel.refresh();
	return;
    }

    /**
     * @return the dispGroupName
     */
    public String getDisplayGroupName() {
	return displayGroupName;
    }

    /**
     * @param dispGroupName
     *            the dispGroupName to set
     */
    public void setDisplayGroupName(String dispGroupName) {
	this.displayGroupName = dispGroupName;
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
	title.setText(description);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jettmarks.routes.client.MapDetailViewGwtImpl#getBackbutton()
     */
    @Override
    public HasTapHandlers getBackbutton() {
	return headerButtonBar.getLeftButton();
    }

    /**
     * @see com.jettmarks.routes.client.ui.EventView#getForwardbutton()
     */
    @Override
    public HasTapHandlers getForwardbutton() {
	return headerButtonBar.getRightButton();
    }

    public HTML getHeaderTapHandlers() {
	return title;
    }

    /**
     * @see com.jettmarks.routes.client.ui.EventView#enableBackButton(boolean)
     */
    @Override
    public void enableBackButton(boolean isEnabled) {
	headerButtonBar.setLeftButtonEnabled(isEnabled);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jettmarks.routes.client.ui.EventView#enableForwardButton()
     */
    @Override
    public void enableForwardButton(boolean isEnabled) {
	headerButtonBar.setRightButtonEnabled(isEnabled);
    }

    /**
     * Responds to external activity telling us a route has been selected.
     * 
     * 
     * @see com.jettmarks.routes.client.ui.EventView#selectRoute(com.jettmarks.routes.client.bean.Route)
     */
    @Override
    public void selectRoute(Route route) {
	int index = 0;
	for (Route r : routes) {
	    String title = r.getDisplayName();
	    boolean selected = (route != null)
		    && title.equals(route.getDisplayName());
	    listWidget.setSelectedIndex(index++, selected);
	}
	if (route != null) {
	    // headerPanel.setCenter("View " + route.getDisplayName());
	    title.setText("View " + route.getDisplayName());
	} else {
	    // headerPanel.setCenter(getDescription());
	    title.setText(getDescription());
	}
	// Popup with choice to view more detail about the route.
	// List<OptionsDialogEntry> list = new ArrayList<OptionsDialogEntry>();
	// list.add(new OptionsDialogEntry("View " + route.getDisplayName(),
	// ButtonType.CONFIRM));
	// list.add(new OptionsDialogEntry("Cancel", ButtonType.NORMAL));
	//
	// Dialogs.options(list, new OptionCallback() {
	//
	// @Override
	// public void onOptionSelected(int index) {
	// }
	// });
    }

    /**
     * Removes all routes from the map.
     * 
     * @see com.jettmarks.routes.client.ui.EventView#clearMap()
     */
    @Override
    public void clearMap() {
	for (Route r : routes) {
	    r.setMap(null);
	}
	routes.clear();
	mapBounds = null;
    }

    /**
     * @see com.jettmarks.routes.client.ui.EventView#showMapTab()
     */
    @Override
    public void showMapTab() {
	tabPanel.setSelectedChild(0);
    }

    /**
     * @see com.jettmarks.routes.client.ui.EventView#showListTab()
     */
    @Override
    public void showListTab() {
	tabPanel.setSelectedChild(1);
    }

    /**
     * @see com.jettmarks.routes.client.ui.EventView#getHomeButton()
     */
    @Override
    public HasTapHandlers getHomeButton() {
	return headerButtonBar.getHomeButton();
    }
}
