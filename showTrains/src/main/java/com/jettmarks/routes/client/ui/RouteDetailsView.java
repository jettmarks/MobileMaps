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

import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.jettmarks.routes.client.DetailView;

/**
 * Describes the values we display on the RouteDetailsView.
 * 
 * @author jett
 */
public interface RouteDetailsView extends DetailView {

    public abstract HTML getHeaderTapHandlers();

    public abstract void setNotes(String notes);

    public abstract String getNotes();

    public abstract void setDisplayName(String displayName);

    public abstract String getDisplayName();

    public abstract void setRouteName(String routeName);

    public abstract String getRouteName();

    public abstract void setLeaderName(String leaderName);

    public abstract String getLeaderName();

    public abstract void setLeaderEmail(String leaderEmail);

    public abstract String getLeaderEmail();

    public abstract void setLeaderPhone(String leaderPhone);

    public abstract String getLeaderPhone();

    public abstract void setDeparture(String departure);

    public abstract String getDeparture();

    public abstract void setArrival(String arrival);

    public abstract String getArrival();

    /**
     * @return
     */
    public abstract HasTapHandlers getHomeButton();

}
