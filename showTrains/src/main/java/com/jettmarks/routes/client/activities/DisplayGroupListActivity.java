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
 * Created Apr 20, 2014
 */
package com.jettmarks.routes.client.activities;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.Topic;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.forms.FormsPlace;
import com.jettmarks.routes.client.place.EventPlace;
import com.jettmarks.routes.client.service.GetTagsAsync;

/**
 * Prepares the display of the Display Groups.
 * 
 * @author Jett Marks
 */
public class DisplayGroupListActivity extends MGWTAbstractActivity {

	private final ClientFactory clientFactory;

	private DisplayGroupListView view = null;

	private List<Topic> displayGroupTopics;

	private static List<DisplayGroupDTO> displayGroupList;

	/**
	 * Default constructor stores our clientFactory instance and invokes the
	 * service to populate our list of Display Groups (Bike Train Events).
	 * 
	 * @param clientFactory
	 */
	public DisplayGroupListActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

		GetTagsAsync tagService = GetTagsAsync.Util.getInstance();
		tagService
				.getBikeTrains(new GetDisplayGroupCallback<DisplayGroupDTO[]>());
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getDisplayGroupListView();

		view.setTitle("Bike Train Events");
		// view.setRightButtonText("New");

		addHandlerRegistration(view.getCellSelectedHandler()
				.addCellSelectedHandler(new CellSelectedHandler() {

					@Override
					public void onCellSelected(CellSelectedEvent event) {
						int index = event.getIndex();
						DisplayGroupDTO displayGroup = displayGroupList
								.get(index);

						EventPlace eventPlace = new EventPlace(displayGroup);
						clientFactory.getPlaceController().goTo(eventPlace);
						return;
					}
				}));

		addHandlerRegistration(view.getAboutButton().addTapHandler(
				new TapHandler() {

					@Override
					public void onTap(TapEvent event) {
						clientFactory.getPlaceController().goTo(
								new FormsPlace());
					}
				}));

		panel.setWidget(view);
	}

	/**
	 * Responds to the service callback giving us the list of Display Groups.
	 * 
	 * @author jett
	 */
	public class GetDisplayGroupCallback<T> implements
			AsyncCallback<DisplayGroupDTO[]> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.
		 * Throwable )
		 */
		public void onFailure(Throwable caught) {
			Window.alert("Failure in GetDisplayGroupCallback: " + caught);
		}

		/**
		 * Bring back the DisplayGroups and turn into a list of topics to
		 * present.
		 * 
		 * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
		 */
		@Override
		public void onSuccess(DisplayGroupDTO[] result) {
			displayGroupTopics = new ArrayList<Topic>();

			displayGroupList = new ArrayList<DisplayGroupDTO>();
			for (DisplayGroupDTO dg : result) {
				// For later use
				displayGroupList.add(dg);
				// For presenting in the view
				Topic topic = new Topic((dg.getDescription()), 5);
				displayGroupTopics.add(topic);
			}
			view.setTopics(displayGroupTopics);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.googlecode.mgwt.mvp.client.MGWTAbstractActivity#onStop()
	 */
	@Override
	public void onStop() {
		super.onStop();
		cancelAllHandlerRegistrations();
	}

}
