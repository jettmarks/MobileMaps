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
 * Created Jul 26, 2014
 */
package com.jettmarks.routes.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.DetailActivity;
import com.jettmarks.routes.client.ui.EventView;

/**
 * Handles user activity for the main view when the map is still empty waiting
 * for a particular Bike Event to be chosen.
 * 
 * This is only useful for the Desktop Landscape platform/orientation.
 * 
 * @author jett
 */
public class EmptyMapActivity extends DetailActivity {

	private EventView view;
	private final ClientFactory clientFactory;

	/**
	 * @param clientFactory
	 */
	public EmptyMapActivity(ClientFactory clientFactory) {
		super(clientFactory.getEventView(), "");
		view = clientFactory.getEventView();
		this.clientFactory = clientFactory;
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
		super.onStop();
		cancelAllHandlerRegistrations();
	}

	/**
	 * Kicks off the Empty Map View with title and buttons disabled.
	 * 
	 * @see com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client
	 *      .ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
	 */
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.getTitle().setText("Select an Event");
		view.enableBackButton(false);
		view.enableForwardButton(false);
		setNavHandlers(view);
		panel.setWidget(view);
	}

	/**
	 * @param view2
	 */
	protected void setNavHandlers(EventView view2) {
		// Home Button
		// addHandlerRegistration(view2.getHomeButton().addTapHandler(
		// new TapHandler() {
		//
		// @Override
		// public void onTap(TapEvent event) {
		// clientFactory.getPlaceController()
		// .goTo(new HomePlace());
		// }
		//
		// }));

	}

}
