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

import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.jettmarks.routes.client.bean.BikeTrainRoute;

/**
 * Description.
 * 
 * @author jett
 */
public class EventViewListOnlyGwtImpl extends EventViewBaseImpl implements
	EventView {

    private String description;

    private String displayGroupName;

    private ScrollPanel scrollPanel;

    public EventViewListOnlyGwtImpl() {
	main = new LayoutPanel();
	main.setSize("100%", "100%");

	// Take care of the header for navigation
	setupHeader();
	setupListPanel(listWidget);

	main.add(headerButtonBar);
	main.add(scrollPanel);
    }

    /**
     * @param listWidget
     */
    private void setupListPanel(CellList<BikeTrainRoute> listWidget) {
	scrollPanel = new ScrollPanel();
	scrollPanel.add(listWidget);
	scrollPanel.setSize("100%", "100%");
	// scrollPanel.setHeight(ScreenSize.getHeight() - 80 + "px");
	scrollPanel.setScrollingEnabledX(true);
    }

    /**
     * Called after last route has been loaded.
     */
    public void renderList(List<BikeTrainRoute> routes) {
	listWidget.render(routes);
	scrollPanel.refresh();
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

    /**
     * @see com.jettmarks.routes.client.ui.EventView#enableForwardButton()
     */
    @Override
    public void enableForwardButton(boolean isEnabled) {
	headerButtonBar.setRightButtonEnabled(isEnabled);
    }

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
     * @see com.jettmarks.routes.client.ui.EventView#getHomeButton()
     */
    @Override
    public HasTapHandlers getHomeButton() {
	return headerButtonBar.getHomeButton();
    }
}
