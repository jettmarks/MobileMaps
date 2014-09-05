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
 * Created Aug 20, 2014
 */
package com.jettmarks.routes.client.activities;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.NavLink;
import com.jettmarks.routes.client.place.ConductPlace;
import com.jettmarks.routes.client.ui.NavLinkSelectedHandler;

/**
 * Description.
 * 
 * @author jett
 */
public class GetInvolvedActivity extends MGWTAbstractActivity implements
	Activity {

    private ClientFactory clientFactory;

    /**
     * @param clientFactory
     */
    public GetInvolvedActivity(ClientFactory cf) {
	this.clientFactory = cf;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.googlecode.mgwt.mvp.client.MGWTAbstractActivity#start(com.google.
     * gwt.user.client.ui.AcceptsOneWidget,
     * com.google.gwt.event.shared.EventBus)
     */
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	final GetInvolvedView view = clientFactory.getGetInvolvedView();
	view.getHeader().setText("Get Involved");
	List<NavLink> links = getLinks();
	view.getNavList().render(getLinks());
	view.getNavList().addCellSelectedHandler(
		new NavLinkSelectedHandler(links, clientFactory));
	panel.setWidget(view);
    }

    /**
     * @return
     */
    private List<NavLink> getLinks() {
	List<NavLink> links = new ArrayList<NavLink>();
	links.add(new NavLink("Create a Bike Train", new ConductPlace()));
	links.add(new NavLink("Become a Bike Buddy",
		"http://atlantabike.org/content/Bike-Buddies-here-help"));
	links.add(new NavLink("Volunteer",
		"http://www.atlantabike.org/volunteering"));
	links.add(new NavLink("Become a Member",
		"https://www.atlantabike.org/civicrm/contribute/transact?reset=1&id=1"));
	return links;
    }
}
