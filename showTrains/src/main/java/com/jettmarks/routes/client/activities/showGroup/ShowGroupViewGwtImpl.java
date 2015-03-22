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
 * Created Apr 20, 2014
 */
package com.jettmarks.routes.client.activities.showGroup;

import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.user.client.ui.HasText;
import com.googlecode.mgwt.ui.client.widget.header.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.jettmarks.routes.client.DetailViewGwtImpl;
import com.jettmarks.routes.client.bean.Route;
import com.jettmarks.routes.client.util.ScreenSize;

public class ShowGroupViewGwtImpl extends DetailViewGwtImpl implements
		ShowGroupView {
	private MTextBox mtbDescription = new MTextBox();

	private MTextBox mtbDisplayGroupName = new MTextBox();

	private MapWidget mapWidget;

	private HeaderButton viewDetailButton;

	private static LatLngBounds mapBounds = null;

	/**
	 * @deprecated - Replaced by the EventSelectionView
	 */
	public ShowGroupViewGwtImpl() {
		com.googlecode.mgwt.ui.client.widget.header.HeaderButton

		// Take care of the header for navigation
		viewDetailButton = new HeaderButton();
		viewDetailButton.setForwardButton(true);
		viewDetailButton.setText("Details");
		headerPanel.setRightWidget(viewDetailButton);

		LatLng atlanta = LatLng.newInstance(33.757787d, -84.359741d);
		MapOptions opts = MapOptions.newInstance();
		opts.setZoom(14);
		opts.setCenter(atlanta);
		opts.setMapTypeId(MapTypeId.ROADMAP);
		opts.setScaleControl(true);

		mapWidget = new MapWidget(opts);
		mapWidget.setSize("100%", "100%");
		ScreenSize.addRegistration(mapWidget);

		mapWidget.setHeight(ScreenSize.getHeight() - 40 + "px");
		mapPanel.add(mapWidget);

	}

	/**
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return mtbDescription.getText();
	}

	/**
	 * @param description
	 *            the description to set
	 */
	@Override
	public void setDescription(String description) {
		mtbDescription.setText(description);
		title.setHTML(description);
	}

	/**
	 * @return the displayGroupName
	 */
	@Override
	public String getDisplayGroupName() {
		return mtbDisplayGroupName.getText();
	}

	/**
	 * @param displayGroupName
	 *            the displayGroupName to set
	 */
	@Override
	public void setDisplayGroupName(String displayGroupName) {
		mtbDisplayGroupName.setText(displayGroupName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jettmarks.routes.client.activities.showGroup.ShowGroupView#add(com.
	 * jettmarks.routes.client.bean.Route)
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
		route.getPolyline().setMap(mapWidget);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jettmarks.routes.client.activities.showGroup.ShowGroupView#
	 * getViewDetailButton()
	 */
	@Override
	public HeaderButton getViewDetailButton() {
		return viewDetailButton;
	}

	/**
	 * Called when we want to fit all the routes within the visible portion of
	 * the map.
	 */
	public void resize() {
		if (mapBounds == null) {
			return;
		}

		mapWidget.fitBounds(mapBounds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jettmarks.routes.client.DetailView#getForwardbuttonText()
	 */
	@Override
	public HasText getForwardbuttonText() {
		// TODO Auto-generated method stub
		return null;
	}

}
