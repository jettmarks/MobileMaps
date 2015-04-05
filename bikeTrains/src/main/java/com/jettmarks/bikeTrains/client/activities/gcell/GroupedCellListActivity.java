/*
 * Copyright 2010 Daniel Kurka
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
package com.jettmarks.bikeTrains.client.activities.gcell;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.ui.client.widget.list.celllist.GroupingCellList.CellGroup;
import com.googlecode.mgwt.ui.client.widget.list.celllist.GroupingCellList.StandardCellGroup;
import com.jettmarks.bikeTrains.client.ClientFactory;
import com.jettmarks.bikeTrains.client.DetailActivity;
import com.jettmarks.bikeTrains.client.activities.gcell.GroupedCellListView.Content;
import com.jettmarks.bikeTrains.client.activities.gcell.GroupedCellListView.Header;

/**
 * @author Daniel Kurka
 *
 */
public class GroupedCellListActivity extends DetailActivity {
	protected static String[] labels = new String[] { "A", "B", "C", "D", "E",
			"F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
			"S", "T", "U", "V", "W", "X", "Y", "Z", "#" };

	private final ClientFactory clientFactory;

	public GroupedCellListActivity(ClientFactory clientFactory) {
		super(clientFactory.getGroupedCellListView(), "nav");
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		final GroupedCellListView view = clientFactory.getGroupedCellListView();

		view.getHeader().setText("Header List");

		view.render(buildList());

		panel.setWidget(view);
	}

	private List<CellGroup<Header, Content>> buildList() {
		ArrayList<CellGroup<Header, Content>> list = new ArrayList<CellGroup<Header, Content>>();

		for (int i = 0; i < labels.length; i++) {
			final Header header = new Header(labels[i]);
			final ArrayList<Content> arrayList = new ArrayList<Content>();

			// int max = (int) (Math.random() * 5);

			int max = 2;

			for (int j = 0; j < max; j++) {
				arrayList.add(new Content("" + j));
			}

			CellGroup<Header, Content> cellGroup = new StandardCellGroup<Header, Content>(
					header.getName(), header, arrayList);

			list.add(cellGroup);

		}

		return list;
	}
}