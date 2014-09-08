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
 * Created Sep 7, 2014
 */
package com.jettmarks.routes.client.activities;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.NavLink;
import com.jettmarks.routes.client.place.ConductorPlace;
import com.jettmarks.routes.client.ui.NavLinkSelectedHandler;
import com.jettmarks.routes.client.ui.ResourcesView;

/**
 * Description.
 * 
 * @author jett
 */
public class ResourcesActivity extends NavLinkActivity implements Activity {

    private static ResourcesView view = null;

    /**
     * @param cf
     */
    public ResourcesActivity(ClientFactory cf) {
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
	    view = clientFactory.getResourcesView();
	    view.getHeader().setText("Resources");
	    setNavHandlers(view);
	    List<NavLink> links = getLinks();
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
	// links.add(new NavLink("ABC Programs running Bike Trains",
	// "http://atlantabike.org/BikeTrainPrograms"));

	links.add(new NavLink("Atlanta Bike Challenge",
		"https://www.lovetoride.net/atlanta/user_sessions/new?a=1&locale=en-US"));
	links.add(new NavLink("Become a Conductor", new ConductorPlace()));
	links.add(new NavLink("Bike Buddies",
		"http://www.atlantabike.org/content/Bike-Buddies-here-help"));
	links.add(new NavLink("Bike Commuters of Atlanta - Facebook Group",
		"https://www.facebook.com/groups/bikecommutersatl/"));
	links.add(new NavLink("Bike Suitability Maps",
		"http://www.atlantabike.org/DMAtlantaSuitabilityMap"));
	return links;
    }

}
