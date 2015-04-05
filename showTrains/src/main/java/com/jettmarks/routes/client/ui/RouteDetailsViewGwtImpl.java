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
 * Created Jul 11, 2014
 */
package com.jettmarks.routes.client.ui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.image.NextitemImageButton;
import com.googlecode.mgwt.ui.client.widget.button.image.PreviousitemImageButton;
import com.googlecode.mgwt.ui.client.widget.form.FormEntry;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.input.MEmailTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MPhoneNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;

/**
 * Description.
 * 
 * @author jett
 */
public class RouteDetailsViewGwtImpl implements RouteDetailsView {
	protected RootFlexPanel main;

	protected ScrollPanel scrollPanel;

	// protected HeaderButton headerMainButton;

	// protected HeaderButton headerBackButton;
	protected PreviousitemImageButton headerBackButton;

	// protected HeaderButton headerForwardButton;
	protected NextitemImageButton headerForwardButton;

	protected HTML title;

	protected HeaderPanel headerPanel;

	// Widget Elements
	private MTextBox tbLeaderName;

	private MEmailTextBox tbLeaderEmail;

	private MPhoneNumberTextBox tbPhone;

	private MTextBox tbArrival;

	private MTextBox tbDeparture;

	// private MTextArea tbNotes;
	private HTML notesHTML;

	private LayoutPanel formPanel;

	private HeaderButtonBar headerButtonBar;

	/**
	 * Default Constructor builds the widget.
	 * 
	 */
	public RouteDetailsViewGwtImpl() {
		super();
		main = new RootFlexPanel();
		scrollPanel = new ScrollPanel();

		main.setSize("100%", "100%");

		setupHeader();
		main.add(headerButtonBar);

		// Setup the Form Area, less the Notes
		WidgetList widgetList = setupForm();

		formPanel = new LayoutPanel();
		formPanel.add(widgetList);
		notesHTML = new HTML();
		// notesHTML.setHeight("300px");
		widgetList.add(notesHTML);
		// formPanel.add(notesHTML);
		// scrollPanel.add(widgetList);
		scrollPanel.add(formPanel);
		main.add(scrollPanel);
	}

	/**
* 
*/
	private void setupHeader() {
		headerButtonBar = new HeaderButtonBar();
		enableBackButton(true);
		title = new HTML();
		headerButtonBar.setTitle(title);
	}

	/**
	 * Assembles the widgets needed to present the Bike Train data.
	 * 
	 * @return
	 */
	WidgetList setupForm() {
		WidgetList widgetList = new WidgetList();
		widgetList.setRound(true);

		tbLeaderName = new MTextBox();
		tbLeaderEmail = new MEmailTextBox();
		tbPhone = new MPhoneNumberTextBox();
		tbDeparture = new MTextBox();
		tbArrival = new MTextBox();
		// tbNotes = new MTextArea();
		// tbNotes.setHeight("100px");

		widgetList.add(new FormEntry("Leader Name", tbLeaderName));
		widgetList.add(new FormEntry("Leader Email", tbLeaderEmail));
		widgetList.add(new FormEntry("Leader Phone", tbPhone));
		widgetList.add(new FormEntry("Departure", tbDeparture));
		widgetList.add(new FormEntry("Arrival", tbArrival));
		widgetList.add(new Label("Notes"));
		// widgetList.add(tbNotes);
		return widgetList;
	}

	/**
	 * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
	 */
	@Override
	public Widget asWidget() {
		return main;
	}

	/**
	 * @see com.jettmarks.routes.client.DetailView#getHeader()
	 */
	@Override
	public HasText getHeader() {
		return title;
	}

	/**
	 * @see com.jettmarks.routes.client.DetailView#getBackbuttonText()
	 */
	@Override
	public HasText getBackbuttonText() {
		return headerBackButton;
	}

	/**
	 * @see com.jettmarks.routes.client.DetailView#getBackbuttonText()
	 */
	@Override
	public HasText getForwardbuttonText() {
		return headerForwardButton;
	}

	/**
	 * @see com.jettmarks.routes.client.ui.EventView#enableBackButton(boolean)
	 */
	public void enableBackButton(boolean isEnabled) {
		headerButtonBar.setLeftButtonEnabled(isEnabled);
	}

	/**
	 * @see com.jettmarks.routes.client.DetailView#getBackbutton()
	 */
	@Override
	public HasTapHandlers getBackbutton() {
		return headerBackButton;
		// return headerButtonBar.getLeftButton();
	}

	/**
	 * @see com.jettmarks.routes.client.DetailView#getMainButtonText()
	 */
	@Override
	public HasText getMainButtonText() {
		return title;
	}

	/**
	 * @see com.jettmarks.routes.client.DetailView#getMainButton()
	 */
	@Override
	public HasTapHandlers getMainButton() {
		// return headerMainButton;
		return null;
	}

	/**
	 * @see com.jettmarks.routes.client.ui.RouteDetailsView#getHeaderTapHandlers()
	 */
	@Override
	public HTML getHeaderTapHandlers() {
		return title;
	}

	/**
	 * @return the arrival
	 */
	@Override
	public String getArrival() {
		return tbArrival.getText();
	}

	/**
	 * @param arrival
	 *            the arrival to set
	 */
	@Override
	public void setArrival(String arrival) {
		tbArrival.setText(arrival);
	}

	/**
	 * @return the departure
	 */
	@Override
	public String getDeparture() {
		return tbDeparture.getText();
	}

	/**
	 * @param departure
	 *            the departure to set
	 */
	@Override
	public void setDeparture(String departure) {
		tbDeparture.setText(departure);
	}

	/**
	 * @return the leaderPhone
	 */
	@Override
	public String getLeaderPhone() {
		return tbPhone.getText();
	}

	/**
	 * @param leaderPhone
	 *            the leaderPhone to set
	 */
	@Override
	public void setLeaderPhone(String leaderPhone) {
		tbPhone.setText(leaderPhone);
	}

	/**
	 * @return the leaderEmail
	 */
	@Override
	public String getLeaderEmail() {
		return tbLeaderEmail.getText();
	}

	/**
	 * @param leaderEmail
	 *            the leaderEmail to set
	 */
	@Override
	public void setLeaderEmail(String leaderEmail) {
		tbLeaderEmail.setText(leaderEmail);
	}

	/**
	 * @return the leaderName
	 */
	@Override
	public String getLeaderName() {
		return tbLeaderName.getText();
	}

	/**
	 * @param leaderName
	 *            the leaderName to set
	 */
	@Override
	public void setLeaderName(String leaderName) {
		tbLeaderName.setText(leaderName);
	}

	/**
	 * @return the routeName
	 */
	@Override
	public String getRouteName() {
		// return routeName;
		return null;
	}

	/**
	 * @param routeName
	 *            the routeName to set
	 */
	@Override
	public void setRouteName(String routeName) {
		// this.routeName = routeName;
	}

	/**
	 * @return the displayName
	 */
	@Override
	public String getDisplayName() {
		return null;
		// return displayName;
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	@Override
	public void setDisplayName(String displayName) {
		// this.displayName = displayName;
	}

	/**
	 * @return the notes
	 */
	@Override
	public String getNotes() {
		return notesHTML.getText();
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	@Override
	public void setNotes(String notes) {
		notesHTML.setText(notes);
		scrollPanel.refresh();
	}

	/**
	 * @see com.jettmarks.routes.client.ui.RouteDetailsView#getHomeButton()
	 */
	@Override
	public HasTapHandlers getHomeButton() {
		return headerButtonBar.getHomeButton();
	}

}
