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

import java.util.List;

import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.jettmarks.routes.client.bean.BikeTrainRoute;
import com.jettmarks.routes.client.util.ScreenSize;

/**
 * Description.
 * 
 * @author jett
 */
public class EventViewMapOnlyGwtImpl extends EventViewBaseImpl implements
		EventView {

	private static int currentZoomLevel = 13;

	public EventViewMapOnlyGwtImpl() {
		main = new RootFlexPanel();
		main.setSize("100%", "100%");

		// Take care of the header for navigation
		setupHeader();
		mapWidget = prepareMap();
		main.add(headerButtonBar);
		main.add(mapWidget);
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
		mapWidget.setHeight(ScreenSize.getHeight() - 39 + "px");
		ScreenSize.addRegistration(mapWidget);
		return mapWidget;
	}

	/**
	 * @see com.jettmarks.routes.client.ui.EventViewBaseImpl#resize()
	 */
	@Override
	public void resize(List<BikeTrainRoute> routes) {
		super.resize(routes);
		if (mapBounds == null) {
			return;
		}
		mapWidget.fitBounds(mapBounds);
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

	/**
	 * @see com.jettmarks.routes.client.MapDetailViewGwtImpl#getBackbutton()
	 */
	@Override
	public HasTapHandlers getBackbutton() {
		// return headerButtonBar.getLeftButton();
		return null;
	}

	/**
	 * @see com.jettmarks.routes.client.ui.EventView#getForwardbutton()
	 */
	@Override
	public HasTapHandlers getForwardbutton() {
		// return headerButtonBar.getRightButton();
		return null;
	}

	public HasHandlers getHeaderTapHandlers() {
		return title;
		// return null;
	}

	/**
	 * @see com.jettmarks.routes.client.ui.EventView#enableBackButton(boolean)
	 */
	@Override
	public void enableBackButton(boolean isEnabled) {
		// headerButtonBar.setLeftButtonEnabled(isEnabled);
	}

	/**
	 * @see com.jettmarks.routes.client.ui.EventView#enableForwardButton()
	 */
	@Override
	public void enableForwardButton(boolean isEnabled) {
		// headerButtonBar.setRightButtonEnabled(isEnabled);
	}

	/**
	 * Responds to external activity telling us a route has been selected.
	 * 
	 * 
	 * @see com.jettmarks.routes.client.ui.EventView#selectRoute(com.jettmarks.routes.client.bean.Route)
	 * @Override public void selectRoute(Route route) { int index = 0; for
	 *           (Route r : routes) { String title = r.getDisplayName(); boolean
	 *           selected = (route != null) &&
	 *           title.equals(route.getDisplayName());
	 *           listWidget.setSelectedIndex(index++, selected); } if (route !=
	 *           null) { title.setText("View " + route.getDisplayName()); } else
	 *           { title.setText(getDescription()); } }
	 */

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
		// return headerButtonBar.getHomeButton();
		return null;
	}

	/**
	 * Responds to external activity telling us a route has been selected.
	 * 
	 * Map Only view doesn't have to worry about the list and the map has been
	 * covered by calls to the separate routes.
	 * 
	 * @see com.jettmarks.routes.client.ui.EventView#selectRoute(com.jettmarks.routes.client.bean.Route)
	 */
	@Override
	public void selectRoute(Integer newIndex) {
		currentIndex = newIndex;
	}
}
