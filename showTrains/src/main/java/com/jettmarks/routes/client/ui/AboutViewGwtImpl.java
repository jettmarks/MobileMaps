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
package com.jettmarks.routes.client.ui;

import com.google.gwt.user.client.ui.HTML;

/**
 * Description.
 * 
 * @author jett
 */
public class AboutViewGwtImpl extends NavLinkViewGwtImpl implements AboutView {
    private HTML aboutMessage = new HTML();

    public AboutViewGwtImpl() {
	aboutMessage
		.setText("Atlanta Bike Trains is a Volunteer Program of the "
			+ "Atlanta Bicycle Coalition.  ABC's mission is to improve the "
			+ "livability of our city through making it safer, more "
			+ "attractive and more fun to ride bikes for work, travel and "
			+ "pleasure.<p>Bike Trains recognize that we all face barriers "
			+ "to riding more, but finding a friendly companion to ride with doesn't have "
			+ "to be one of them.");
	scrollPanel.add(aboutMessage);
    }
}
