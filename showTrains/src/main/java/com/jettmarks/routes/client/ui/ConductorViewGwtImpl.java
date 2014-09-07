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

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;

/**
 * Description.
 * 
 * @author jett
 */
public class ConductorViewGwtImpl extends NavLinkViewGwtImpl implements
	ConductorView {
    private HTML aboutMessage = new HTML();

    public ConductorViewGwtImpl() {

	// Add in the relevant CSS for this view
	DOM.setElementAttribute(main.getElement(), "id", "about-view");
	scrollPanel.addStyleDependentName("message");

	aboutMessage
		.setHTML("<h1>Information Required</h1>"
			+ "To lead either a Standing Route or an "
			+ "Event Route (described below), you'll send <UL>"
			+ "<LI>The Event you'll be attending (or the Standing route), "
			+ "<LI>Your name, "
			+ "<LI>Contact info (phone and/or email),"
			+ "<LI>Either GPX file or turn-by-turn route description,"
			+ "<LI>Meeting point location,"
			+ "<LI>Departure time and expected arrival time."
			+ "</UL>"
			+ "Email this information the "
			+ "<a href='mailto:jett@atlantabike.org"
			+ "?subject=Bike+Train+Conductor'>ABC Bike Train coordinator</a>"
			+ "<p>More details found on the ABC website for "
			+ "<a href='http://www.atlantabike.org/biketrains'>Bike Trains</a>"
			+ "<h2>Standing Route</h2>"
			+ "<p>If you ride a particular route or set of routes on"
			+ " a regular basis, you may invite others to join you "
			+ "along your route.  When you publish your willingness "
			+ "to join up with others, you've established a "
			+ "'Standing Route'.  Your route will be mapped in this "
			+ "list: <a href='#EventPlace:btStanding'>Standing Bike Trains</a></p>\n"
			+ "<h2>Event Route</h2>"
			+ "If you'll be riding over to one of our events, you can"
			+ "publish your route as part of that event.  By "
			+ "publishing your willingness to join up with others "
			+ "also attending the event, you're route will be "
			+ "listed along with other Conductors who will be "
			+ "meeting you at the Event's location.");

	scrollPanel.add(aboutMessage);
	scrollPanel.refresh();
    }
}
