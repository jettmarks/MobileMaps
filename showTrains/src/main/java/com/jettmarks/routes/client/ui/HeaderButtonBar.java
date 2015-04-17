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
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.ButtonBase;
import com.googlecode.mgwt.ui.client.widget.button.image.NextImageButton;
import com.googlecode.mgwt.ui.client.widget.button.image.PreviousImageButton;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.header.HeaderTitle;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.jettmarks.routes.client.css.AppBundle;
import com.jettmarks.routes.client.ui.widget.HomeButton;

/**
 * Replacement for ButtonBar where I can better manage the width of the central
 * text width.
 * 
 * Fix for Defect #201 - Long Title pushing right-button off the end.
 * 
 * After moving to mGWT 2.0, found that this would be useful to implement some
 * of the functionality the app had gotten accustomed to: flex spacers and the
 * button management.
 * 
 * @author jett
 */
public class HeaderButtonBar extends HeaderPanel implements HasText {

	private FlowPanel main;
	protected final HeaderButtonBarCss css;

	// private ButtonBarButtonBase leftButton = new HomeButton();
	private ButtonBase leftButton = new PreviousImageButton();
	private ButtonBase rightButton = new NextImageButton();
	// private ButtonBarButtonBase rightButton = new ArrowRightButton();
	private HeaderTitle title = new HeaderTitle();
	private Widget leftPlaceholder = new FlexSpacer();
	private Widget titlePlaceholder = new HeaderTitle("   ");
	private Widget rightPlaceholder = new FlexSpacer();

	private boolean leftButtonEnabled = true;
	private boolean rightButtonEnabled = true;
	// private ButtonBase homeButton = new HomeImageButton();

	// Something goofball until I can assemble my own Home Button:
	private ButtonBase homeButton = new HomeButton();
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
		// initWidget(main);

		// Destroying visibility at this time
		// setStylePrimaryName("mgwt-ButtonBar");

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

		add(new FlexSpacer());

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

		add(new FlexSpacer());

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
	 * .ui.Widget) //
	 */
	// Flips back and forth depending on whether we contain or extend
	// @Override
	// public void add(Widget w) {
	// main.add(w);
	// }

	/**
	 * @return the main public FlowPanel getMain() { return main; }
	 */

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
	public ButtonBase getLeftButton() {
		return leftButton;
	}

	/**
	 * @param leftButton
	 *            the leftButton to set
	 */
	public void setLeftButton(ButtonBase leftButton) {
		this.leftButton = leftButton;
	}

	/**
	 * @return the rightButton
	 */
	public ButtonBase getRightButton() {
		return rightButton;
	}

	/**
	 * @param rightButton
	 *            the rightButton to set
	 */
	public void setRightButton(ButtonBase rightButton) {
		this.rightButton = rightButton;
	}

	/**
	 * @return the title
	 */
	public HasHandlers getTitleHandlers() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(HeaderTitle title) {
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
	public ButtonBase getHomeButton() {
		return homeButton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.HasText#getText()
	 */
	@Override
	public String getText() {
		return title.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.HasText#setText(java.lang.String)
	 */
	@Override
	public void setText(String text) {
		title.setText(text);
	}

	/**
	 * Parent class will provide the hover text; we center our text in header.
	 * 
	 * @see com.google.gwt.user.client.ui.UIObject#setTitle(java.lang.String)
	 * 
	 *      Use this only if I need to; getTitle is more significant to
	 *      override.
	 * 
	 @Override public void setTitle(String title) { super.setTitle(title);
	 *           setText(title); }
	 */

	/**
	 * Provide our HeaderTitle instead of the string that our parent would
	 * provide.
	 */
	public HasText getHeaderTitle() {
		return this.title;
	}
}
