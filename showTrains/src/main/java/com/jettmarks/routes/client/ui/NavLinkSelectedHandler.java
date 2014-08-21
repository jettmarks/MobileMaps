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

import java.util.List;

import com.google.gwt.user.client.Window;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.NavLink;

/**
 * Description.
 * 
 * @author jett
 */
public class NavLinkSelectedHandler implements CellSelectedHandler {

    public List<NavLink> navLinkList;
    private ClientFactory clientFactory;

    public NavLinkSelectedHandler(List<NavLink> links,
	    ClientFactory clientFactory) {
	this.clientFactory = clientFactory;
	this.navLinkList = links;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler#
     * onCellSelected
     * (com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent)
     */
    @Override
    public void onCellSelected(CellSelectedEvent event) {
	NavLink link = navLinkList.get(event.getIndex());
	if (link != null && link.getPlace() != null) {
	    clientFactory.getPlaceController().goTo(link.getPlace());
	}

	if (link != null && link.getUrl() != null) {
	    Window.Location.assign(link.getUrl());
	}

    }

}
