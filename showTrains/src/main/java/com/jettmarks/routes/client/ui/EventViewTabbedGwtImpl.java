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

import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.maps.client.overlays.Marker;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.BookmarkTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.SearchTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.Tab;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButtonBase;
import com.jettmarks.routes.client.MapDetailViewGwtImpl;
import com.jettmarks.routes.client.bean.BikeTrainRoute;
import com.jettmarks.routes.client.bean.Route;
import com.jettmarks.routes.client.ui.MarkerFactory.MarkerType;
import com.jettmarks.routes.client.util.ScreenSize;

/**
 * Description.
 * 
 * @author jett
 */
public class EventViewTabbedGwtImpl extends MapDetailViewGwtImpl implements
		EventView {
	private MapWidget mapWidget;

	private static LatLngBounds mapBounds = null;

	private HeaderButton viewDetailButton;

	private static int currentZoomLevel = 13;

	private List<Route> routes = new ArrayList<Route>();

  private String description;

  private String displayGroupName;

	public EventViewTabbedGwtImpl() {
		// Take care of the header for navigation
		setupHeader();
		mapWidget = prepareMap();
		CellList<Route> listWidget = prepareList();
		tabPanel.addTab(prepareMapTab(mapWidget));
		tabPanel.addTab(prepareListTab(listWidget));
	}

	/**
	 * @param listWidget
	 * @return
	 */
	private Tab prepareListTab(CellList<Route> listWidget) {
		Tab tab = new Tab();

		TabBarButtonBase button = new BookmarkTabBarButton();
		tab.setButton(button);
		tab.setWidget(listWidget);
		return tab;
	}

	/**
	 * @param mapWidget2
	 * @return
	 */
	private Tab prepareMapTab(MapWidget mapWidget2) {
		Tab tab = new Tab();

		TabBarButtonBase button = new SearchTabBarButton();
		tab.setButton(button);
		tab.setWidget(mapWidget2);
		return tab;
	}

	/**
	 * Setup the List of Bike Trains.
	 * 
	 * @return
	 */
	private CellList<Route> prepareList() {
		return new CellList<Route>(new RouteCell());
	}

	/**
	 * Setup the Map along with resize registration.
	 * 
	 * The mapWidget comes out of this.
	 */
	private MapWidget prepareMap() {
		MapWidget mapWidget;

		LatLng atlanta = LatLng.newInstance(33.757787d, -84.359741d);
		MapOptions opts = MapOptions.newInstance();
		opts.setZoom(currentZoomLevel);
		opts.setCenter(atlanta);
		opts.setMapTypeId(MapTypeId.ROADMAP);
		opts.setScaleControl(true);

		mapWidget = new MapWidget(opts);
		// Only the height has to be spec'd
		mapWidget.setSize("100%", "100%");
		mapWidget.setHeight(ScreenSize.getHeight() - 40 + "px");
		ScreenSize.addRegistration(mapWidget);
		return mapWidget;
	}

	/**
   * 
   */
	private void setupHeader() {
		viewDetailButton = new HeaderButton();
		viewDetailButton.setForwardButton(true);
		viewDetailButton.setText("Details");
		headerPanel.setRightWidget(viewDetailButton);
	}


  /**
   * Adjusts bounds, adds markers and puts it on the mapWidget that is part of
   * this view.
   * 
   * @see com.jettmarks.routes.client.ui.EventView#add(com.jettmarks.routes.client
   *      .bean.Route)
   */
	@Override
	public void add(Route route) {
		LatLngBounds routeBounds = route.getBounds();
		if (mapBounds == null) {
			mapBounds = routeBounds;
		} else {
			mapBounds.extend(routeBounds.getNorthEast());
			mapBounds.extend(routeBounds.getSouthWest());
		}
		routes.add(route);
    ((BikeTrainRoute) route).getStartMarker().setMap(mapWidget);
    ((BikeTrainRoute) route).getEndMarker().setMap(mapWidget);
    // addBeginEndMarkers(route, mapWidget);
		route.getPolyline().setMap(mapWidget);
	}

  /**
   * Hides details of figuring out where to put the markers on the route.
   * 
   * @param route
   * @param mapWidget2
   */
	private void addBeginEndMarkers(Route route, MapWidget mapWidget2) {
		if (route.getPoints() == null) return;
		int pointCount = route.getPoints().length;
		if (pointCount == 0) return;
		
		LatLng beginLatLng = route.getPoints()[0];
		LatLng endLatLng = route.getPoints()[pointCount-1];
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
	 * @see com.jettmarks.routes.client.ui.EventView#getViewDetailButton()
	 */
	@Override
	public HeaderButton getViewDetailButton() {
		return viewDetailButton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jettmarks.routes.client.ui.EventView#resize()
	 */
	@Override
	public void resize() {
		if (mapBounds == null) {
			return;
		}
		mapWidget.fitBounds(mapBounds);
	}

  /**
   * @return the dispGroupName
   */
  public String getDisplayGroupName()
  {
    return displayGroupName;
  }

  /**
   * @param dispGroupName
   *          the dispGroupName to set
   */
  public void setDisplayGroupName(String dispGroupName)
  {
    this.displayGroupName = dispGroupName;
  }

  /**
   * @return the description
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * @param description
   *          the description to set
   */
  public void setDescription(String description)
  {
    this.description = description;
  }

}
