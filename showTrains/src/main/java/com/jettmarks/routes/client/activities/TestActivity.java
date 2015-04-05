/**
 *   Copyright 2015 Jett Marks
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
 * Created Mar 31, 2015
 */
package com.jettmarks.routes.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.ui.TestView;

/**
 * Description.
 *
 * @author jett
 *
 */
public class TestActivity extends MGWTAbstractActivity {
	protected ClientFactory clientFactory;
	protected TestView view;

	public TestActivity(ClientFactory cf) {
		this.clientFactory = cf;
	}

	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		if (view == null) {
			view = clientFactory.getTestView();
			view.getHeader().setText("Test Title");
		}
		panel.setWidget(view);
	}
}