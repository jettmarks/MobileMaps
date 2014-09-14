package com.jettmarks.routes.client.ui;

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
import com.jettmarks.routes.client.bean.DisplayOnlyRoute;
import com.jettmarks.routes.client.bean.Route;
import com.jettmarks.routes.client.rep.EventContainer;
import com.jettmarks.routes.client.ui.MarkerFactory.MarkerType;

public class EventViewBaseImpl extends MapDetailViewGwtImpl implements
	EventView {

    protected static LatLngBounds mapBounds = null;
    protected MapWidget mapWidget;
    protected final CellList<BikeTrainRoute> listWidget = new CellList<BikeTrainRoute>(
	    new RouteCell());
    protected String description;
    protected String displayGroupName;
    protected ScrollPanel scrollPanel;
    protected HeaderButtonBar headerButtonBar;
    protected Integer currentIndex;

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
	DisplayOnlyRoute bikeRoute = (DisplayOnlyRoute) route;
	// Take care of the map
	LatLngBounds routeBounds = bikeRoute.getBounds();
	if (mapBounds == null) {
	    mapBounds = routeBounds;
	} else {
	    mapBounds.extend(routeBounds.getNorthEast());
	    mapBounds.extend(routeBounds.getSouthWest());
	}
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
     * @see com.jettmarks.routes.client.ui.EventView#enableHomeButton(boolean)
     */
    @Override
    public void enableHomeButton(boolean b) {
	headerButtonBar.setHomeButtonEnabled(b);
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

	if (route != null) {
	    title.setText("View " + route.getDisplayName());
	} else {
	    title.setText(getDescription());
	}
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

    // @Override
    // public Route getRoute(int index) {
    // return routes.get(index);
    // }
    /**
     * Invoked once all routes have been read from the async service.
     * 
     * @see com.jettmarks.routes.client.ui.EventView#resize()
     */
    @Override
    public void resize(List<BikeTrainRoute> routes) {
	// Pick up refreshed dgDto if we didn't get one at first
	if (description == null) {
	    DisplayGroupDTO dgDto = EventContainer.getEvent(displayGroupName);
	    if (dgDto != null) {
		setDescription(dgDto.getDescription());
	    }
	}
	// subclasses perform their own specific action
    }

    /**
     * Removes all routes from the map.
     * 
     * @see com.jettmarks.routes.client.ui.EventView#clearMap()
     * @Override public void clearMap() { }
     */

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

    /**
     * @see com.jettmarks.routes.client.ui.EventView#clearMap()
     */
    @Override
    public void clearMap() {
	mapBounds = null;
    }

    /**
     * Subclasses provide specific action.
     * 
     * @see com.jettmarks.routes.client.ui.EventView#selectRoute(java.lang.Integer)
     */
    @Override
    public void selectRoute(Integer newIndex) {
    }

}