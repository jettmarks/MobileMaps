/*
 * Copyright 2012 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.jettmarks.routes.client.forms;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.form.FormEntry;
import com.googlecode.mgwt.ui.client.widget.input.MTextBox;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.jettmarks.routes.client.DetailViewGwtImpl;

public class FormsViewGwtImpl extends DetailViewGwtImpl implements FormsView {
	private MTextBox mtbDescription = new MTextBox();

	private MTextBox mtbDisplayGroupName = new MTextBox();

	private Button saveButton = null;

	public FormsViewGwtImpl() {

		FlowPanel container = new FlowPanel();

		HTML header = new HTML("Display Group Details");
		// header.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle()
		// .getListCss().listHeader());

		container.add(header);

		WidgetList widgetList = new WidgetList();
		widgetList.setRound(true);

		// lets put in some widgets
		if (MGWT.getOsDetection().isPhone()) {
			widgetList.add(new FormEntry("Description", mtbDescription));
			widgetList.add(new FormEntry("Display Group Name",
					mtbDisplayGroupName));
		} else {
			widgetList.add(new FormEntry(
					"Description of Display Group (spaces allowed)",
					mtbDescription));
			widgetList.add(new FormEntry(
					"Display Group Name (used in URL; no spaces)",
					mtbDisplayGroupName));
		}

		saveButton = new Button("Save");
		widgetList.add(saveButton);

		container.add(widgetList);

	}

	/**
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return mtbDescription.getText();
	}

	/**
	 * @param description
	 *            the description to set
	 */
	@Override
	public void setDescription(String description) {
		mtbDescription.setText(description);
	}

	/**
	 * @return the displayGroupName
	 */
	@Override
	public String getDisplayGroupName() {
		return mtbDisplayGroupName.getText();
	}

	/**
	 * @param displayGroupName
	 *            the displayGroupName to set
	 */
	@Override
	public void setDisplayGroupName(String displayGroupName) {
		mtbDisplayGroupName.setText(displayGroupName);
	}

	/**
	 * @see com.jettmarks.routes.client.forms.FormsView#getSaveButton()
	 */
	@Override
	public HasTapHandlers getSaveButton() {
		return saveButton;
	}

	/**
	 * @see com.jettmarks.routes.client.DetailView#getForwardbuttonText()
	 */
	@Override
	public HasText getForwardbuttonText() {
		return null;
	}

}
