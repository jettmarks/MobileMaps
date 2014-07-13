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
package com.jettmarks.routes.client.activities.showGroup;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.DetailActivity;
import com.jettmarks.routes.client.activities.MapActivity;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.place.EventPlace;
import com.jettmarks.routes.client.place.HomePlace;
import com.jettmarks.routes.client.rep.RouteContainer;
import com.jettmarks.routes.client.rep.RouteContainerFactory;
import com.jettmarks.routes.client.ui.EventView;

public class ShowGroupActivity extends DetailActivity
{

  private final ClientFactory clientFactory;

  private EventView view;

  private RouteContainer routeContainer;

  public ShowGroupActivity(Place newPlace, ClientFactory clientFactory)
  {
    super(clientFactory.getShowGroupView(), "nav");
    // if (newPlace instanceof ShowGroupPlace) {
    // ShowGroupPlace place = (ShowGroupPlace)newPlace;
    // String description = place.getDescription();
    // String displayGroupName = place.getDisplayGroupName();
    // ShowGroupView view = clientFactory.getShowGroupView();
    // view.setDisplayGroupName(displayGroupName);
    // view.setDescription(description);
    // } else if (newPlace instanceof EventPlace){
    if (newPlace instanceof EventPlace)
    {
      EventPlace place = (EventPlace) newPlace;
      String description = place.getDescription();
      String displayGroupName = place.getDisplayGroupName();
      view = clientFactory.getEventView();
      view.setDisplayGroupName(displayGroupName);
      view.setDescription(description);
      DisplayGroupDTO displayGroup = new DisplayGroupDTO();
      displayGroup.setDisplayName(displayGroupName);
      displayGroup.setDescription(description);
      routeContainer = RouteContainerFactory.getRouteContainer();
      routeContainer.setView(view);
      routeContainer.setCurrentDisplayGroup(displayGroup);
    }
    this.clientFactory = clientFactory;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus)
  {
    super.start(panel, eventBus);
    // view.getMainButtonText().setText("Nav");
    view.getBackbuttonText().setText("<");
    view.getForwardbuttonText().setText(">");

    if (routeContainer.displayGroupHasChanged())
    {
      // Instantiation is sufficient
      new MapActivity(view, clientFactory);
    }
    panel.setWidget(view);
  }

  /**
   * Callback response for saving a Display Group.
   * 
   * After saving, we go to the Home Place.
   * 
   * @author jett
   */
  public class SaveDisplayGroupCallback<T> implements AsyncCallback<Integer>
  {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable
     * )
     */
    @Override
    public void onFailure(Throwable caught)
    {
      // Not sure what to do here
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
     */
    @Override
    public void onSuccess(Integer result)
    {
      Window.alert("Saved Display Group as ID: " + result);
      clientFactory.getPlaceController().goTo(new HomePlace());
    }

  }

  /**
   * Responsible for removing the Handler Registrations.
   * 
   * @see com.googlecode.mgwt.mvp.client.MGWTAbstractActivity#onStop()
   */
  @Override
  public void onStop()
  {
    super.onStop();
    cancelAllHandlerRegistrations();
  }

}
