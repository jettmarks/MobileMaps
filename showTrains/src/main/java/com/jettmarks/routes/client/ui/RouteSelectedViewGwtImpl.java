/**
 *   Copyright 2015 Jett Marks
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
 * Created Mar 28, 2015
 */
package com.jettmarks.routes.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.dialog.Dialogs.ButtonType;
import com.googlecode.mgwt.ui.client.dialog.Dialogs.OptionCallback;
import com.googlecode.mgwt.ui.client.dialog.Dialogs.OptionsDialogEntry;

/**
 * Uses a slide up button to let the user know they've selected a route and
 * presents a list of options for proceeding.
 *
 * @author jett
 *
 */
public class RouteSelectedViewGwtImpl implements RouteSelectedView {

	private String routeName;
	private List<OptionsDialogEntry> list;

	/**
	 * @return the routeName
	 */
	public String getRouteName() {
		return routeName;
	}

	/**
	 * @param routeName
	 *            the routeName to set
	 */
	@Override
	public void setRouteName(String routeName) {
		this.routeName = routeName;
		refreshOptionList();
	}

	/**
	 * 
	 */
	private void refreshOptionList() {
		list = null; // de-reference any previous list
		list = new ArrayList<OptionsDialogEntry>();
		list.add(new OptionsDialogEntry("View details for <br>" + routeName,
				ButtonType.NORMAL));
		list.add(new OptionsDialogEntry("Choose another Route",
				ButtonType.NORMAL));
		list.add(new OptionsDialogEntry("View List of Routes",
				ButtonType.NORMAL));
		Dialogs.options(list, new OptionCallback() {

			@Override
			public void onOptionSelected(int index) {
				switch (index) {
				case 1:
					Window.alert("Headed to the details page");
					break;
				case 2:
					Window.alert("Close and go back to what we were doing");
					break;
				case 3:
				default:
					Window.alert("switch to List view");
					break;
				}

			}
		});
	}

	public RouteSelectedViewGwtImpl() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jettmarks.routes.client.DetailView#getHeader()
	 */
	@Override
	public HasText getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jettmarks.routes.client.DetailView#getBackbuttonText()
	 */
	@Override
	public HasText getBackbuttonText() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jettmarks.routes.client.DetailView#getForwardbuttonText()
	 */
	@Override
	public HasText getForwardbuttonText() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jettmarks.routes.client.DetailView#getBackbutton()
	 */
	@Override
	public HasTapHandlers getBackbutton() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jettmarks.routes.client.DetailView#getMainButtonText()
	 */
	@Override
	public HasText getMainButtonText() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jettmarks.routes.client.DetailView#getMainButton()
	 */
	@Override
	public HasTapHandlers getMainButton() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
	 */
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}

}
