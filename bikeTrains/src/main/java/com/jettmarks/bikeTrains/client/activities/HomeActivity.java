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
package com.jettmarks.bikeTrains.client.activities;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.jettmarks.bikeTrains.client.ClientFactory;
import com.jettmarks.bikeTrains.client.NavLink;
import com.jettmarks.bikeTrains.client.ui.HomeView;
import com.jettmarks.bikeTrains.client.ui.NavLinkSelectedHandler;

/**
 * Description.
 * 
 * @author jett
 */
public class HomeActivity extends MGWTAbstractActivity implements Activity {

	private final ClientFactory clientFactory;
	private static HomeView view = null;

	/**
	 * @param cf
	 */
	public HomeActivity(ClientFactory cf) {
		this.clientFactory = cf;
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
			view = clientFactory.getHomeView();
			view.getHeader().setText("ABC Bike Trains - Home");
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
		// links.add(new NavLink("Find a Bike Train", new FindRoutePlace()));
		// links.add(new NavLink("Become a Conductor", new ConductorPlace()));
		// links.add(new NavLink("Get Involved", new GetInvolvedPlace()));
		// links.add(new NavLink("Resources", new ResourcesPlace()));
		links.add(new NavLink("About", new AboutPlace()));
		return links;
	}

}
