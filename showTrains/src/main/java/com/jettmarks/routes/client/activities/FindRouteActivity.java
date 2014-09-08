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
 * Created Aug 19, 2014
 */
package com.jettmarks.routes.client.activities;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.NavLink;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.place.EventPlace;
import com.jettmarks.routes.client.place.EventSelectionPlace;
import com.jettmarks.routes.client.ui.FindRouteView;
import com.jettmarks.routes.client.ui.NavLinkSelectedHandler;

/**
 * Description.
 * 
 * @author jett
 */
public class FindRouteActivity extends NavLinkActivity implements Activity {

    private static FindRouteView view = null;

    /**
     * @param cf
     */
    public FindRouteActivity(ClientFactory cf) {
	super(cf);
    }

    /**
     * @see com.google.gwt.activity.shared.Activity#mayStop()
     */
    @Override
    public String mayStop() {
	return null;
    }

    /**
     * @see com.google.gwt.activity.shared.Activity#onCancel()
     */
    @Override
    public void onCancel() {
    }

    /**
     * @see com.google.gwt.activity.shared.Activity#onStop()
     */
    @Override
    public void onStop() {
    }

    /**
     * @see com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client
     *      .ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
     */
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	if (view == null) {
	    view = clientFactory.getFindRouteView();
	    view.getHeader().setText("Find a Bike Train");
	    List<NavLink> links = getLinks();
	    setNavHandlers(view);
	    view.getNavList().render(getLinks());
	    view.getNavList().addCellSelectedHandler(
		    new NavLinkSelectedHandler(links, clientFactory));
	}
	panel.setWidget(view);
    }

    /**
     * @return
     */
    private List<NavLink> getLinks() {
	List<NavLink> links = new ArrayList<NavLink>();
	DisplayGroupDTO standingRoutes = new DisplayGroupDTO("btStanding");
	standingRoutes.setDescription("Standing Bike Trains");
	links.add(new NavLink("Join an upcoming Event",
		new EventSelectionPlace()));
	links.add(new NavLink("Accompany a 'Standing' Route", new EventPlace(
		standingRoutes)));
	links.add(new NavLink("Bike Buddies",
		"http://www.atlantabike.org/content/Bike-Buddies-here-help"));
	return links;
    }

}
