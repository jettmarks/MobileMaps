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
 * Created Jul 11, 2014
 */
package com.jettmarks.routes.client.activities;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.DetailActivity;
import com.jettmarks.routes.client.bean.BikeTrainRoute;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.place.EventPlace;
import com.jettmarks.routes.client.place.RouteDetailsPlace;
import com.jettmarks.routes.client.rep.RouteContainerFactory;
import com.jettmarks.routes.client.ui.RouteDetailsView;

/**
 * Description.
 * 
 * @author jett
 */
public class RouteDetailsActivity extends DetailActivity implements Activity
{
  private ClientFactory clientFactory = null;

  private BikeTrainRoute bikeTrainRoute = null;

  private String headerText = null;

  /**
   * @param detailView
   * @param eventId
   */
  public RouteDetailsActivity(Place newPlace, ClientFactory cf)
  {
    super(cf.getRouteDetailsView(), "");
    clientFactory = cf;

    if (newPlace instanceof RouteDetailsPlace)
    {
      RouteDetailsPlace place = (RouteDetailsPlace) newPlace;
      RouteDetailsView routeDetailsView = clientFactory.getRouteDetailsView();
      bikeTrainRoute = place.getRoute();
      if (bikeTrainRoute == null)
      {
        headerText = "Select a Route";
      }
      else
      {
        headerText = bikeTrainRoute.getDisplayName();
      }
      routeDetailsView.getMainButtonText().setText(headerText);

      addHandlerRegistration(routeDetailsView.getBackbutton().addTapHandler(
          new TapHandler()
          {
            @Override
            public void onTap(TapEvent event)
            {
              DisplayGroupDTO displayGroup = RouteContainerFactory.getRouteContainer()
                                                                  .getCurrentDisplayGroup();
              String displayName = displayGroup.getDisplayName();
              String description = displayGroup.getDescription();
              clientFactory.getPlaceController().goTo(
                  new EventPlace(displayName, description));
            }
          }));
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.activity.shared.Activity#mayStop()
   */
  @Override
  public String mayStop()
  {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.activity.shared.Activity#onCancel()
   */
  @Override
  public void onCancel()
  {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.activity.shared.Activity#onStop()
   */
  @Override
  public void onStop()
  {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client
   * .ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
   */
  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus)
  {
    super.start(panel, eventBus);
    final RouteDetailsView view = clientFactory.getRouteDetailsView();

    view.getBackbuttonText().setText("<");
    view.getHeader().setText(headerText);

    panel.setWidget(view);
  }

}