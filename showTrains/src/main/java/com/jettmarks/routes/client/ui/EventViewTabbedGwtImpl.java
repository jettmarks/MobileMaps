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

import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.tabbar.Tab;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButtonBase;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;
import com.jettmarks.routes.client.bean.BikeTrainRoute;
import com.jettmarks.routes.client.ui.tab.ListTabBarButton;
import com.jettmarks.routes.client.ui.tab.MapTabBarButton;
import com.jettmarks.routes.client.util.ScreenSize;

/**
 * Description.
 * 
 * @author jett
 */
public class EventViewTabbedGwtImpl extends EventViewBaseImpl implements
		EventView {

	private static int currentZoomLevel = 13;

	public EventViewTabbedGwtImpl() {
		main = new LayoutPanel();
		main.setSize("100%", "100%");

		// Take care of the header for navigation
		setupHeader();
		mapWidget = prepareMap();
		tabPanel = new TabPanel();
		tabPanel.addTab(prepareMapTab(mapWidget));
		tabPanel.addTab(prepareListTab(listWidget));

		main.add(headerButtonBar);
		main.add(tabPanel);
	}

	/**
	 * @param mapWidget2
	 * @return
	 */
	private Tab prepareMapTab(MapWidget mapWidget2) {
		Tab tab = new Tab();

		TabBarButtonBase button = new MapTabBarButton();
		tab.setButton(button);
		tab.setWidget(mapWidget2);
		return tab;
	}

	/**
	 * @param listWidget
	 * @return
	 */
	private Tab prepareListTab(CellList<BikeTrainRoute> listWidget) {
		Tab tab = new Tab();
		scrollPanel = new ScrollPanel();
		scrollPanel.add(listWidget);
		scrollPanel.setSize("100%", "100%");
		scrollPanel.setHeight(ScreenSize.getHeight() - 80 + "px");
		scrollPanel.setScrollingEnabledX(true);

		TabBarButtonBase button = new ListTabBarButton();
		tab.setButton(button);
		tab.setWidget(scrollPanel);
		return tab;
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
		mapWidget.setHeight(ScreenSize.getHeight() - 89 + "px");
		ScreenSize.addRegistration(mapWidget);
		return mapWidget;
	}

	/**
	 * Called after last route has been loaded. public void
	 * renderList(List<Route> routes) { listWidget.render(routes);
	 * scrollPanel.refresh(); }
	 */

	/**
	 * Responds to external activity telling us a route has been selected.
	 * 
	 * @see com.jettmarks.routes.client.ui.EventView#selectRoute(com.jettmarks.routes.client.bean.Route)
	 * @Override public void selectRoute(Route route, int index) { for (int i =
	 *           0; i<listWidget.) { String title = r.getDisplayName(); boolean
	 *           selected = (route != null) &&
	 *           title.equals(route.getDisplayName());
	 *           listWidget.setSelectedIndex(index++, selected); } if (route !=
	 *           null) { title.setText("View " + route.getDisplayName()); } else
	 *           { title.setText(getDescription()); } }
	 */

	/**
	 * Responds to external activity telling us a route has been selected.
	 * 
	 * Null value for newIndex will turn off all selections.
	 * 
	 * @see com.jettmarks.routes.client.ui.EventView#selectRoute(com.jettmarks.routes.client.bean.Route)
	 */
	@Override
	public void selectRoute(Integer newIndex) {
		if (currentIndex != null) {
			listWidget.setSelectedIndex(currentIndex, false);
		}
		if (newIndex != null) {
			listWidget.setSelectedIndex(newIndex, true);
		}
		currentIndex = newIndex;
	}

	/**
	 * Called when the async routes have all been added; the list specifically
	 * will render the List (generate HTML).
	 * 
	 * @see com.jettmarks.routes.client.ui.EventViewBaseImpl#resize()
	 */
	@Override
	public void resize(List<BikeTrainRoute> routes) {
		super.resize(routes);
		renderList(routes);
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
	 * Called after last route has been loaded.
	 */
	public void renderList(List<BikeTrainRoute> routes) {
		listWidget.render(routes);
		scrollPanel.refresh();
	}
}
