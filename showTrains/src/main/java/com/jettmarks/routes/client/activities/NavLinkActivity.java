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

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.place.HomePlace;
import com.jettmarks.routes.client.ui.NavLinkView;

/**
 * Description.
 * 
 * @author jett
 */
public class NavLinkActivity extends MGWTAbstractActivity implements Activity {

    protected final ClientFactory clientFactory;

    /**
     * @param cf
     */
    public NavLinkActivity(ClientFactory cf) {
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
    }

    /**
     * @param view
     */
    protected void setNavHandlers(NavLinkView view) {
	addHandlerRegistration(view.getHomeButton().addTapHandler(
		new TapHandler() {

		    @Override
		    public void onTap(TapEvent event) {
			clientFactory.getPlaceController()
				.goTo(new HomePlace());
		    }

		}));

    }

}
