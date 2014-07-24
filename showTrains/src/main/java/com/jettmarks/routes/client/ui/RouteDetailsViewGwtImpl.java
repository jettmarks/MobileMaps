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
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.FormListEntry;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.MEmailTextBox;
import com.googlecode.mgwt.ui.client.widget.MPhoneNumberTextBox;
import com.googlecode.mgwt.ui.client.widget.MTextArea;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.WidgetList;

/**
 * Description.
 * 
 * @author jett
 */
public class RouteDetailsViewGwtImpl implements RouteDetailsView
{
  protected LayoutPanel main;

  protected ScrollPanel scrollPanel;

  protected HeaderButton headerMainButton;

  protected HeaderButton headerBackButton;

  protected HeaderButton headerForwardButton;

  protected HTML title;

  protected HeaderPanel headerPanel;

  // Widget Elements
  private MTextBox tbLeaderName;

  private MEmailTextBox tbLeaderEmail;

  private MPhoneNumberTextBox tbPhone;

  private MTextBox tbArrival;

  private MTextBox tbDeparture;

  private MTextArea tbNotes;

  /**
   * Default Constructor builds the widget.
   * 
   */
  public RouteDetailsViewGwtImpl()
  {
    super();
    main = new LayoutPanel();
    scrollPanel = new ScrollPanel();

    main.setSize("100%", "100%");
    headerPanel = new HeaderPanel();
    title = new HTML();
    headerPanel.setCenterWidget(title);

    headerMainButton = new HeaderButton();
    headerMainButton.setRoundButton(true);

    headerBackButton = new HeaderButton();
    headerBackButton.setBackButton(true);
    // headerBackButton.setVisible(!MGWT.getOsDetection().isAndroid());

    headerForwardButton = new HeaderButton();
    headerForwardButton.setRoundButton(false);
    headerForwardButton.setForwardButton(true);

    headerPanel.setLeftWidget(headerBackButton);
    main.add(headerPanel);

    // Setup the Form Area
    WidgetList widgetList = setupForm();

    scrollPanel.add(widgetList);
    main.add(scrollPanel);
  }

  /**
   * Assembles the widgets needed to present the Bike Train data.
   * 
   * @return
   */
  WidgetList setupForm()
  {
    WidgetList widgetList = new WidgetList();
    widgetList.setRound(true);

    tbLeaderName = new MTextBox();
    tbLeaderEmail = new MEmailTextBox();
    tbPhone = new MPhoneNumberTextBox();
    tbDeparture = new MTextBox();
    tbArrival = new MTextBox();
    tbNotes = new MTextArea();
    tbNotes.setHeight("100px");

    widgetList.add(new FormListEntry("Leader Name", tbLeaderName));
    widgetList.add(new FormListEntry("Leader Email", tbLeaderEmail));
    widgetList.add(new FormListEntry("Leader Phone", tbPhone));
    widgetList.add(new FormListEntry("Departure", tbDeparture));
    widgetList.add(new FormListEntry("Arrival", tbArrival));
    widgetList.add(new Label("Notes"));
    widgetList.add(tbNotes);
    // widgetList.add(new FormListEntry("Notes", tbNotes));
    return widgetList;
  }

  /**
   * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
   */
  @Override
  public Widget asWidget()
  {
    return main;
  }

  /**
   * @see com.jettmarks.routes.client.DetailView#getHeader()
   */
  @Override
  public HasText getHeader()
  {
    return title;
  }

  /**
   * @see com.jettmarks.routes.client.DetailView#getBackbuttonText()
   */
  @Override
  public HasText getBackbuttonText()
  {
    return headerBackButton;
  }

  /**
   * @see com.jettmarks.routes.client.DetailView#getBackbuttonText()
   */
  @Override
  public HasText getForwardbuttonText()
  {
    return headerForwardButton;
  }

  /**
   * @see com.jettmarks.routes.client.DetailView#getBackbutton()
   */
  @Override
  public HasTapHandlers getBackbutton()
  {
    return headerBackButton;
  }

  /**
   * @see com.jettmarks.routes.client.DetailView#getMainButtonText()
   */
  @Override
  public HasText getMainButtonText()
  {
    return headerMainButton;
  }

  /**
   * @see com.jettmarks.routes.client.DetailView#getMainButton()
   */
  @Override
  public HasTapHandlers getMainButton()
  {
    return headerMainButton;
  }

  /**
   * @return the arrival
   */
  @Override
  public String getArrival()
  {
    return tbArrival.getText();
  }

  /**
   * @param arrival
   *          the arrival to set
   */
  @Override
  public void setArrival(String arrival)
  {
    tbArrival.setText(arrival);
  }

  /**
   * @return the departure
   */
  @Override
  public String getDeparture()
  {
    return tbDeparture.getText();
  }

  /**
   * @param departure
   *          the departure to set
   */
  @Override
  public void setDeparture(String departure)
  {
    tbDeparture.setText(departure);
  }

  /**
   * @return the leaderPhone
   */
  @Override
  public String getLeaderPhone()
  {
    return tbPhone.getText();
  }

  /**
   * @param leaderPhone
   *          the leaderPhone to set
   */
  @Override
  public void setLeaderPhone(String leaderPhone)
  {
    tbPhone.setText(leaderPhone);
  }

  /**
   * @return the leaderEmail
   */
  @Override
  public String getLeaderEmail()
  {
    return tbLeaderEmail.getText();
  }

  /**
   * @param leaderEmail
   *          the leaderEmail to set
   */
  @Override
  public void setLeaderEmail(String leaderEmail)
  {
    tbLeaderEmail.setText(leaderEmail);
  }

  /**
   * @return the leaderName
   */
  @Override
  public String getLeaderName()
  {
    return tbLeaderName.getText();
  }

  /**
   * @param leaderName
   *          the leaderName to set
   */
  @Override
  public void setLeaderName(String leaderName)
  {
    tbLeaderName.setText(leaderName);
  }

  /**
   * @return the routeName
   */
  @Override
  public String getRouteName()
  {
    // return routeName;
    return null;
  }

  /**
   * @param routeName
   *          the routeName to set
   */
  @Override
  public void setRouteName(String routeName)
  {
    // this.routeName = routeName;
  }

  /**
   * @return the displayName
   */
  @Override
  public String getDisplayName()
  {
    return null;
    // return displayName;
  }

  /**
   * @param displayName
   *          the displayName to set
   */
  @Override
  public void setDisplayName(String displayName)
  {
    // this.displayName = displayName;
  }

  /**
   * @return the notes
   */
  @Override
  public String getNotes()
  {
    // return notes;
    return tbNotes.getText();
  }

  /**
   * @param notes
   *          the notes to set
   */
  @Override
  public void setNotes(String notes)
  {
    tbNotes.setText(notes);
  }

}
