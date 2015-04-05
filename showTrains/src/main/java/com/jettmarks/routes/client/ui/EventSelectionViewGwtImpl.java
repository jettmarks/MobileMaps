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
 * Created Jul 26, 2014
 */
package com.jettmarks.routes.client.ui;

import java.util.List;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.image.NextitemImageButton;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.header.HeaderTitle;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.celllist.HasCellSelectedHandler;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressBar;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressIndicator;
import com.jettmarks.routes.client.BasicCell;
import com.jettmarks.routes.client.Topic;

/**
 * Description.
 * 
 * @author jett
 */
public class EventSelectionViewGwtImpl implements EventSelectionView {
	private RootFlexPanel main;

	// private HeaderButton forwardButton;
	private NextitemImageButton forwardButton;

	private HeaderPanel headerPanel;
	private HeaderTitle headerPanelTitle = new HeaderTitle();

	private CellList<Topic> cellList;

	private ProgressIndicator progressIndicator = null;

	private ScrollPanel scrollPanel;

	/** Buffers the space around the ProgressBar. */
	private FlowPanel progressBarPanel;

	public EventSelectionViewGwtImpl() {
		main = new RootFlexPanel();

		headerPanel = new HeaderPanel();

		forwardButton = new NextitemImageButton();
		// forwardButton.setForwardButton(true);
		// headerPanel.setRightWidget(forwardButton);
		main.add(headerPanel);

		cellList = new CellList<Topic>(new BasicCell<Topic>() {

			@Override
			public String getDisplayString(Topic model) {
				return model.getName();
			}

			@Override
			public boolean canBeSelected(Topic model) {
				return true;
			}
		});

		// cellList.getCellList().setRound(true);

		// Setup the Progress Bar/Indicator based on phone or tablet
		if ((MGWT.getOsDetection().isTablet())) {
			progressBarPanel = new FlowPanel();
			progressBarPanel.getElement().getStyle().setMarginTop(20, Unit.PX);
			progressBarPanel.add(new ProgressBar());
			main.add(progressBarPanel);
		} else {
			progressIndicator = new ProgressIndicator();
			progressIndicator.getElement().setAttribute("style",
					"margin:auto; margin-top: 50px");
			main.add(progressIndicator);
		}

		// This is where the list of Events will go
		scrollPanel = new ScrollPanel();
		scrollPanel.setWidget(cellList);
		scrollPanel.setScrollingEnabledX(true);
		main.add(scrollPanel);
	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public void setTitle(String text) {
		headerPanelTitle.setText(text);
	}

	@Override
	public void setRightButtonText(String text) {
		forwardButton.setText(text);
	}

	@Override
	public HasTapHandlers getAboutButton() {
		return forwardButton;
	}

	@Override
	public HasCellSelectedHandler getCellSelectedHandler() {
		return cellList;
	}

	@Override
	public void setTopics(List<Topic> createTopicsList) {
		cellList.render(createTopicsList);
		if ((MGWT.getOsDetection().isTablet())) {
			progressBarPanel.removeFromParent();
		} else {
			progressIndicator.removeFromParent();
		}
		scrollPanel.refresh();
	}

}
