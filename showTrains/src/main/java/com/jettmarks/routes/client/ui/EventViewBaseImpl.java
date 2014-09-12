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
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;
import com.jettmarks.routes.client.MapDetailViewGwtImpl;
import com.jettmarks.routes.client.bean.BikeTrainRoute;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.bean.Route;
import com.jettmarks.routes.client.rep.EventContainer;
import com.jettmarks.routes.client.ui.MarkerFactory.MarkerType;

public class EventViewBaseImpl extends MapDetailViewGwtImpl implements EventView {

    protected static LatLngBounds mapBounds = null;
    protected MapWidget mapWidget;
    protected List<Route> routes = new ArrayList<Route>();
    protected final CellList<Route> listWidget = new CellList<Route>(new RouteCell());
    protected String description;
    protected String displayGroupName;
    protected ScrollPanel scrollPanel;
    protected HeaderButtonBar headerButtonBar;

    public EventViewBaseImpl() {
    }

    /**
    * 
    */
    protected void setupHeader() {
        headerButtonBar = new HeaderButtonBar();
        title = new HTML();
        headerButtonBar.setTitle(title);
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

    @Override
    public void enableForwardButton(boolean isEnabled) {
        headerButtonBar.setRightButtonEnabled(isEnabled);
    }

    /**
     * @see com.jettmarks.routes.client.ui.EventView#getHomeButton()
     */
    @Override
    public HasTapHandlers getHomeButton() {
        return headerButtonBar.getHomeButton();
    }


    @Override
    public void selectRoute(Route route) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void showMapTab() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void showListTab() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void setRouteSelectedHandler(
	    CellSelectedHandler routeListCellSelectedHandler) {
        listWidget.addCellSelectedHandler(routeListCellSelectedHandler);
    }

    @Override
    public Route getRoute(int index) {
	return routes.get(index);
    }

    @Override
    public void resize() {
        // Pick up refreshed dgDto if we didn't get one at first
        if (description == null) {
            DisplayGroupDTO dgDto = EventContainer.getEvent(displayGroupName);
            if (dgDto != null) {
        	setDescription(dgDto.getDescription());
            }
        }
        if (mapBounds == null) {
            return;
        }
        mapWidget.fitBounds(mapBounds);
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
     * Hides details of figuring out where to put the markers on the route.
     * 
     * @param route
     * @param mapWidget2
     */
    void addBeginEndMarkers(Route route, MapWidget mapWidget2) {
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
}