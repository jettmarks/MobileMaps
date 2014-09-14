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
 * Created May 26, 2014
 */
package com.jettmarks.routes.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;
import com.jettmarks.routes.client.DetailView;
import com.jettmarks.routes.client.bean.BikeTrainRoute;
import com.jettmarks.routes.client.bean.Route;

/**
 * An Event corresponds to a set of Bike Trains on a given date.
 * 
 * The view itself will have a Group that is used to bundle up the bike trains
 * for a given date and present the event in either a map, a list, or both.
 * 
 * @author jett
 */
public interface EventView extends DetailView {

    public abstract void setDisplayGroupName(String displayGroupName);

    public abstract String getDisplayGroupName();

    public abstract void setDescription(String description);

    public abstract String getDescription();

    public abstract void add(Route route);

    /**
     * @return
     */
    public abstract HasTapHandlers getForwardbutton();

    public abstract void enableForwardButton(boolean isEnabled);

    public abstract void selectRoute(Route route);

    /**
   * 
   */
    public abstract void clearMap();

    public abstract void showMapTab();

    public abstract void showListTab();

    /**
     * @param b
     */
    public abstract void enableBackButton(boolean b);

    public abstract HTML getHeaderTapHandlers();

    public abstract HasTapHandlers getHomeButton();

    public abstract void setRouteSelectedHandler(
	    CellSelectedHandler routeListCellSelectedHandler);

    /**
     * @param routes
     */
    void resize(List<BikeTrainRoute> routes);

    /**
     * @param newIndex
     */
    void selectRoute(Integer newIndex);

    /**
     * @param b
     */
    public abstract void enableHomeButton(boolean b);

}
