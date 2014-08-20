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
 * Created Aug 19, 2014
 */
package com.jettmarks.routes.client.ui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.jettmarks.routes.client.NavLink;

/**
 * Description.
 * 
 * @author jett
 */
public class HomeViewGwtImpl implements HomeView {

    private LayoutPanel main;
    private HTML title;
    private HeaderPanel headerPanel;
    private CellList<NavLink> linkCellList = null;
    private ScrollPanel scrollPanel;

    public HomeViewGwtImpl() {
	main = new LayoutPanel();
	title = new HTML();
	headerPanel = new HeaderPanel();
	headerPanel.setCenterWidget(title);
	scrollPanel = new ScrollPanel();
	linkCellList = new CellList<NavLink>(new NavLinkCell());
	scrollPanel.add(linkCellList);
	main.add(headerPanel);
	main.add(scrollPanel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jettmarks.routes.client.DetailView#getHeader()
     */
    @Override
    public HasText getHeader() {
	return title;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
     */
    @Override
    public Widget asWidget() {
	return main;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jettmarks.routes.client.ui.HomeView#getNavList()
     */
    @Override
    public CellList<NavLink> getNavList() {
	return linkCellList;
    }

}
