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
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;

/**
 * Description.
 * 
 * @author jett
 */
public class RouteDetailsViewGwtImpl implements RouteDetailsView
{
  protected LayoutPanel main;

  protected HeaderButton headerMainButton;

  protected HeaderButton headerBackButton;

  protected HeaderButton headerForwardButton;

  protected HTML title;

  protected HeaderPanel headerPanel;

  public RouteDetailsViewGwtImpl()
  {
    super();
    main = new LayoutPanel();
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

}
