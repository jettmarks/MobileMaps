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
package com.jettmarks.routes.client.activities.showGroup;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.NavLink;
import com.jettmarks.routes.client.ui.HomeView;

/**
 * Description.
 * 
 * @author jett
 */
public class HomeActivity extends MGWTAbstractActivity implements Activity {

    private final ClientFactory clientFactory;

    /**
     * @param cf
     */
    public HomeActivity(ClientFactory cf) {
	this.clientFactory = cf;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.activity.shared.Activity#mayStop()
     */
    @Override
    public String mayStop() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.activity.shared.Activity#onCancel()
     */
    @Override
    public void onCancel() {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.activity.shared.Activity#onStop()
     */
    @Override
    public void onStop() {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client
     * .ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
     */
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	final HomeView view = clientFactory.getHomeView();
	view.getHeader().setText("Home");
	view.getNavList().render(getLinks());
	panel.setWidget(view);
    }

    /**
     * @return
     */
    private List<NavLink> getLinks() {
	List<NavLink> links = new ArrayList<NavLink>();
	links.add(new NavLink("Find a Bike Train"));
	links.add(new NavLink("Become a Conductor"));
	links.add(new NavLink("Resources"));
	links.add(new NavLink("Support"));
	links.add(new NavLink("About"));
	return links;
    }

}
