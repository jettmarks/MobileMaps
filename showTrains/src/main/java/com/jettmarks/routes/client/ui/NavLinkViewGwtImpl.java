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

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.header.HeaderTitle;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexSpacer;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.jettmarks.routes.client.NavLink;

/**
 * Description.
 * 
 * @author jett
 */
public class NavLinkViewGwtImpl implements NavLinkView {
	protected RootFlexPanel main;
	protected HeaderTitle title;
	protected HeaderPanel headerPanel;
	protected CellList<NavLink> linkCellList;
	protected ScrollPanel scrollPanel;
	protected VerticalPanel imagePanel;

	public NavLinkViewGwtImpl(CellList<NavLink> linkCellList) {
		this.linkCellList = linkCellList;
	}

	public NavLinkViewGwtImpl() {
		main = new RootFlexPanel();
		// main.addStyleDependentName("navLink");

		title = new HeaderTitle();
		headerPanel = new HeaderPanel();
		// headerPanel.addStyleDependentName("navLink");
		headerPanel.add(new FlexSpacer());
		headerPanel.add(title);
		headerPanel.add(new FlexSpacer());

		imagePanel = new VerticalPanel();
		imagePanel.addStyleName("logo-image");
		scrollPanel = new ScrollPanel();
		linkCellList = new CellList<NavLink>(new NavLinkCell());
		main.add(headerPanel);
		main.add(imagePanel);
		main.add(scrollPanel);
	}

	@Override
	public HasText getHeader() {
		return title;
	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public CellList<NavLink> getNavList() {
		return linkCellList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jettmarks.routes.client.ui.NavLinkView#getHomeButton()
	 */
	@Override
	public HasTapHandlers getHomeButton() {
		// return headerPanel.getHomeButton();
		return null;
	}
}