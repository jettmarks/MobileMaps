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

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.ui.ConductorView;

/**
 * Description.
 * 
 * @author jett
 */
public class ConductorActivity extends NavLinkActivity implements Activity {

    private static ConductorView view = null;

    /**
     * @param clientFactory
     */
    public ConductorActivity(ClientFactory cf) {
	super(cf);
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

    /**
     * @see com.google.gwt.activity.shared.Activity#onCancel()
     */
    @Override
    public void onCancel() {
	// TODO Auto-generated method stub

    }

    /**
     * @see com.google.gwt.activity.shared.Activity#onStop()
     */
    @Override
    public void onStop() {
	// TODO Auto-generated method stub

    }

    /**
     * @see com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client
     *      .ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
     */
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	if (view == null) {
	    view = clientFactory.getConductorView();
	    view.getHeader().setText("Serve as a Conductor");
	    setNavHandlers(view);
	}
	panel.setWidget(view);
    }

}
