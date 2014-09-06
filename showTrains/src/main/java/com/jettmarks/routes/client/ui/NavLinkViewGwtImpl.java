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
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.jettmarks.routes.client.NavLink;

/**
 * Description.
 * 
 * @author jett
 */
public class NavLinkViewGwtImpl implements NavLinkView {
    public LayoutPanel main;
    public HTML title;
    public HeaderButtonBar headerPanel;
    public CellList<NavLink> linkCellList;
    public ScrollPanel scrollPanel;

    public NavLinkViewGwtImpl(CellList<NavLink> linkCellList) {
	this.linkCellList = linkCellList;
    }

    public NavLinkViewGwtImpl() {
	main = new LayoutPanel();
	title = new HTML();
	headerPanel = new HeaderButtonBar();
	headerPanel.setTitle(title);
	scrollPanel = new ScrollPanel();
	linkCellList = new CellList<NavLink>(new NavLinkCell());
	main.add(headerPanel);
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
}