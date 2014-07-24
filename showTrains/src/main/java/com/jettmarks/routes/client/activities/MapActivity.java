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

import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.dialog.AlertDialog;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.DetailActivity;
import com.jettmarks.routes.client.DetailView;
import com.jettmarks.routes.client.bean.BikeTrainRoute;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.place.EventSelectionPlace;
import com.jettmarks.routes.client.place.RouteDetailsPlace;
import com.jettmarks.routes.client.rep.RouteContainerFactory;
import com.jettmarks.routes.client.rep.RouteContainerImpl;
import com.jettmarks.routes.client.rep.ServiceWrapper;
import com.jettmarks.routes.client.ui.EventView;

/**
 * Handles Activities of the Map including progress and turning each of the
 * routes into something we can view.
 * 
 * @author jett
 */
public class MapActivity extends DetailActivity
{
  /**
   * Records what type of request we're handling.
   * 
   * class.isInstance isn't supported in GWT.
   * 
   * @author jett
   */
  public enum RequestType {
    STRING, DISPLAY_ELEMENT, UNDETERMINED
  }

  private ClientFactory clientFactory = null;

  private BikeTrainRoute selectedRoute = null;

  /**
   * @param view
   * @param clientFactory
   */
  EventView mapView = null;

  public MapActivity(DetailView view, ClientFactory cf)
  {
    super(view, "");
    clientFactory = cf;

    if (view instanceof EventView)
    {
      final EventView eventView = (EventView) view;
      mapView = eventView;
      DisplayGroupDTO dispGroup = new DisplayGroupDTO();
      dispGroup.setDisplayName(eventView.getDisplayGroupName());

      // Kicks off reading the routes in the DisplayGroup under control
      // of the RouteContainer
      RouteContainerImpl rcImpl = (RouteContainerImpl) RouteContainerFactory.getRouteContainer();
      ServiceWrapper serviceWrapper = new ServiceWrapper(rcImpl);
      serviceWrapper.showRoutes(dispGroup);
    }
  }

  /**
   * Responsible for shutting down the button handlers registered here.
   * 
   * @see com.googlecode.mgwt.mvp.client.MGWTAbstractActivity#onStop()
   */
  @Override
  public void onStop()
  {
    super.onStop();
    cancelAllHandlerRegistrations();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.googlecode.mgwt.mvp.client.MGWTAbstractActivity#start(com.google.gwt
   * .user.client.ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
   */
  public void addRegistration(EventView eventView)
  {
    // super.start(panel, eventBus);
    addHandlerRegistration(eventView.getBackbutton().addTapHandler(
        new TapHandler()
        {

          @Override
          public void onTap(TapEvent event)
          {
            clientFactory.getPlaceController().goTo(new EventSelectionPlace());
          }

        }));

    addHandlerRegistration(eventView.getForwardbutton().addTapHandler(
        new TapHandler()
        {
          @Override
          public void onTap(TapEvent event)
          {
            selectedRoute = (BikeTrainRoute) RouteContainerFactory.getRouteContainer()
                                                                  .getSelectedRoute();
            if (selectedRoute != null)
            {
              clientFactory.getPlaceController().goTo(
                  new RouteDetailsPlace(selectedRoute));
            }
            else
            {
              AlertDialog noRouteSelectedAlert = new AlertDialog("No Train Selected",
                                                                 "Choose a Bike Train to view");
              noRouteSelectedAlert.show();
            }
          }
        }));
  }
}
