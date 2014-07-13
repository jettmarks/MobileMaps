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

import com.google.code.p.gwtchismes.client.GWTCProgress;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.DetailActivity;
import com.jettmarks.routes.client.DetailView;
import com.jettmarks.routes.client.bean.BikeTrainRoute;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.bean.RouteRequest;
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

  private static RequestType requestType = RequestType.UNDETERMINED;

  private static RouteRequest currentRouteRequest = null;

  private static GWTCProgress gwtcProgress;

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

      // Only add handler for button if we have a button to handle
      if (eventView.getForwardbutton() != null)
      {
        addHandlerRegistration(eventView.getForwardbutton().addTapHandler(
            new TapHandler()
            {
              @Override
              public void onTap(TapEvent event)
              {
                selectedRoute = (BikeTrainRoute) RouteContainerFactory.getRouteContainer()
                                                                      .getSelectedRoute();
                clientFactory.getPlaceController().goTo(
                    new RouteDetailsPlace(selectedRoute));
              }
            }));
      }

      // Kicks off reading the routes in the DisplayGroup under control
      // of the RouteContainer
      RouteContainerImpl rcImpl = (RouteContainerImpl) RouteContainerFactory.getRouteContainer();
      rcImpl.openProgressBar(null); // Will be re-opened later with the proper
                                    // counts
      ServiceWrapper serviceWrapper = new ServiceWrapper(rcImpl);
      serviceWrapper.showRoutes(dispGroup);
    }
  }

}
