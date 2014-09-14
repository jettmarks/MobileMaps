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
 * Created Sep 1, 2014
 */
package com.jettmarks.routes.client.ui;

import java.util.Iterator;

import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ArrowLeftButton;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBarButtonBase;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBarSpacer;
import com.googlecode.mgwt.ui.client.widget.buttonbar.InfoButton;
import com.jettmarks.routes.client.css.AppBundle;
import com.jettmarks.routes.client.ui.widget.HomeButton;

/**
 * Replacement for ButtonBar where I can better manage the width of the central
 * text width.
 * 
 * Fix for Defect #201 - Long Title pushing right-button off the end.
 * 
 * @author jett
 */
public class HeaderButtonBar extends Composite implements HasWidgets {

    private FlowPanel main;
    protected final HeaderButtonBarCss css;

    // private ButtonBarButtonBase leftButton = new HomeButton();
    private ButtonBarButtonBase leftButton = new ArrowLeftButton();
    private ButtonBarButtonBase rightButton = new InfoButton();
    // private ButtonBarButtonBase rightButton = new ArrowRightButton();
    private HTML title;
    private Widget leftPlaceholder = new ButtonBarButtonBase(null);
    private Widget titlePlaceholder = new HTML("   ");
    private Widget rightPlaceholder = new ButtonBarButtonBase(null);

    private boolean leftButtonEnabled = false;
    private boolean rightButtonEnabled = false;
    private ButtonBarButtonBase homeButton = new HomeButton();
    private boolean homeButtonEnabled = true;

    /**
     * Constructor for HeaderButtonBar.
     */
    public HeaderButtonBar() {
	this(AppBundle.INSTANCE.headerButtonBarCss());
    }

    /**
     * <p>
     * Constructor for ButtonBar.
     * </p>
     * 
     * @param css
     *            a
     *            {@link com.googlecode.mgwt.ui.client.theme.base.ButtonBarCss}
     *            object.
     */
    public HeaderButtonBar(HeaderButtonBarCss css) {
	this.css = css;
	css.ensureInjected();
	main = new FlowPanel();
	initWidget(main);
	setStylePrimaryName("mgwt-ButtonBar");
	refresh();
    }

    /**
     * Adds the defined elements in order with placeholders for disabled
     * elements.
     */
    private void refresh() {
	main.clear();
	if (homeButtonEnabled) {
	    add(homeButton);
	}
	if (leftButton != null && leftButtonEnabled) {
	    add(leftButton);
	} else {
	    add(leftPlaceholder);
	}

	add(new ButtonBarSpacer());

	if (title != null) {
	    if (MGWT.getOsDetection().isTablet()) {
		title.setStylePrimaryName(this.css.titleDesktop());
	    } else {
		title.setStylePrimaryName(this.css.title());
	    }
	    add(title);
	} else {
	    add(titlePlaceholder);
	}

	add(new ButtonBarSpacer());

	if (rightButton != null && rightButtonEnabled) {
	    add(rightButton);
	} else {
	    add(rightPlaceholder);
	}
    }

    /**
     * @see com.google.gwt.user.client.ui.HasWidgets#clear()
     */
    @Override
    public void clear() {
	main.clear();
    }

    /**
     * @see com.google.gwt.user.client.ui.HasWidgets#iterator()
     */
    @Override
    public Iterator<Widget> iterator() {
	return main.iterator();
    }

    /**
     * @see com.google.gwt.user.client.ui.HasWidgets#remove(com.google.gwt.user.client.ui.Widget)
     */
    @Override
    public boolean remove(Widget w) {
	return main.remove(w);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.user.client.ui.HasWidgets#add(com.google.gwt.user.client
     * .ui.Widget)
     */
    @Override
    public void add(Widget w) {
	main.add(w);
    }

    /**
     * @return the main
     */
    public FlowPanel getMain() {
	return main;
    }

    /**
     * @param main
     *            the main to set
     */
    public void setMain(FlowPanel main) {
	this.main = main;
    }

    /**
     * @return the leftButton
     */
    public ButtonBarButtonBase getLeftButton() {
	return leftButton;
    }

    /**
     * @param leftButton
     *            the leftButton to set
     */
    public void setLeftButton(ButtonBarButtonBase leftButton) {
	this.leftButton = leftButton;
    }

    /**
     * @return the rightButton
     */
    public ButtonBarButtonBase getRightButton() {
	return rightButton;
    }

    /**
     * @param rightButton
     *            the rightButton to set
     */
    public void setRightButton(ButtonBarButtonBase rightButton) {
	this.rightButton = rightButton;
    }

    /**
     * @return the title
     */
    public HasHandlers getHeaderTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(HTML title) {
	this.title = title;
	refresh();
    }

    /**
     * @return the leftButtonEnabled
     */
    public boolean isLeftButtonEnabled() {
	return leftButtonEnabled;
    }

    /**
     * @param leftButtonEnabled
     *            the leftButtonEnabled to set
     */
    public void setLeftButtonEnabled(boolean leftButtonEnabled) {
	this.leftButtonEnabled = leftButtonEnabled;
	refresh();
    }

    /**
     * @return the rightButtonEnabled
     */
    public boolean isRightButtonEnabled() {
	return rightButtonEnabled;
    }

    /**
     * @param rightButtonEnabled
     *            the rightButtonEnabled to set
     */
    public void setRightButtonEnabled(boolean rightButtonEnabled) {
	this.rightButtonEnabled = rightButtonEnabled;
	refresh();
    }

    /**
     * @return the homeButtonEnabled
     */
    public boolean isHomeButtonEnabled() {
	return homeButtonEnabled;
    }

    /**
     * @param homeButtonEnabled
     *            the homeButtonEnabled to set
     */
    public void setHomeButtonEnabled(boolean homeButtonEnabled) {
	this.homeButtonEnabled = homeButtonEnabled;
	refresh();
    }

    /**
     * @return the homeButton
     */
    public ButtonBarButtonBase getHomeButton() {
	return homeButton;
    }
}
