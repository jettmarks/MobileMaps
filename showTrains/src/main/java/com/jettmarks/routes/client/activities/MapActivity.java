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
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.DetailActivity;
import com.jettmarks.routes.client.DetailView;
import com.jettmarks.routes.client.activities.showGroup.ShowGroupView;
import com.jettmarks.routes.client.bean.DisplayElementDTO;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.bean.Route;
import com.jettmarks.routes.client.bean.RouteRequest;
import com.jettmarks.routes.client.bean.RouteRequestRouteName;
import com.jettmarks.routes.client.place.RouteDetailsPlace;
import com.jettmarks.routes.client.rep.ServiceWrapper;
import com.jettmarks.routes.client.ui.EventView;
import com.jettmarks.routes.client.ui.RouteContainer;
import com.jettmarks.routes.client.util.RouteScheduledCommand;

/**
 * Handles Activities of the Map including progress and turning each of the
 * routes into something we can view.
 *
 * @author jett
 */
public class MapActivity extends DetailActivity implements RouteContainer
{
  /**
   * Records what type of request we're handling.
   *
   * class.isInstance isn't supported in GWT.
   * 
   * @author jett
   */
  public enum RequestType {
    STRING, 
    DISPLAY_ELEMENT,
    UNDETERMINED
  }

  private static RequestType requestType = RequestType.UNDETERMINED;
  private static RouteRequest currentRouteRequest = null;
  private static GWTCProgress gwtcProgress;
  private ClientFactory clientFactory = null;
  
  /**
   * @param view
   * @param clientFactory
   */
  EventView mapView = null;
  
  public MapActivity(DetailView view, ClientFactory cf)
  {
    super(view, "");
    clientFactory = cf;
    
    if (view instanceof EventView) {
      EventView eventView = (EventView) view;
      mapView = eventView;
      ServiceWrapper serviceWrapper = new ServiceWrapper(this);
      DisplayGroupDTO dispGroup = new DisplayGroupDTO();
      dispGroup.setDisplayName(eventView.getDisplayGroupName());
//      if (dispGroup.getDisplayName() == null || dispGroup.getDisplayName().length() == 0)
//      {
//        dispGroup.setDisplayName("bt1405-BTW");
//      }
      addHandlerRegistration(eventView.getViewDetailButton().addTapHandler(new TapHandler()
      {
        @Override
        public void onTap(TapEvent event)
        {
          clientFactory.getPlaceController().goTo(new RouteDetailsPlace());
        }
      }));
      
      serviceWrapper.showRoutes(dispGroup);
    }
  }

  /* (non-Javadoc)
   * @see com.jettmarks.routes.client.ui.RouteContainer#addRoutes(com.jettmarks.routes.client.bean.RouteRequestRouteName)
   */
  @Override
  public void addRoutes(RouteRequestRouteName routeRequestRouteName)
  {
    // TODO Auto-generated method stub
    
  }

  /**
   * @see com.jettmarks.routes.client.ui.RouteContainer#addRoutes(com.jettmarks.routes.client.bean.RouteRequest)
   */
  @Override
  public void addRoutes(RouteRequest routeRequest)
  {
    requestType = RequestType.DISPLAY_ELEMENT;
    currentRouteRequest = routeRequest;
    openProgressBar(routeRequest);
    
    ServiceWrapper serviceWrapper = new ServiceWrapper(this);
    serviceWrapper.requestElement((DisplayElementDTO)routeRequest.next());
  }

  /**
   * TODO: Complete this.
   * 
   * @param routeRequest
   */
  private static void openProgressBar(RouteRequest routeRequest)
  {
    
    gwtcProgress = new GWTCProgress(31, GWTCProgress.SHOW_AS_DIALOG
                                        | GWTCProgress.SHOW_TEXT
                                        | GWTCProgress.SHOW_NUMBERS);
    gwtcProgress.setText("Routes loaded:");
    gwtcProgress.setProgress(routeRequest.getCompletedTasks(),
        routeRequest.getTotalTasks());
    RootPanel.get().add(gwtcProgress);
    gwtcProgress.show();
  }

  /**
   * Called each time a single task is completed.
   */
  public void updateProgress()
  {
    // Not much to do if we load a single route at a time.
    if (currentRouteRequest == null)
    {
      mapView.resize();
      return;
    }
    
    if (!currentRouteRequest.hasNext())
    {
      Window.setStatus("Resizing");
      mapView.resize();
      Window.setStatus("Done");
      gwtcProgress.hide();
    }
    else
    // ready to ask for next route
    {
      // Display looks better when we make the asynch request after
      // updating the progress bar
      Object nextRoute = currentRouteRequest.next();
      gwtcProgress.setProgress(currentRouteRequest.getCompletedTasks(),
          currentRouteRequest.getTotalTasks());
      switch (requestType)
      {
      case DISPLAY_ELEMENT:
        Scheduler.get().scheduleDeferred(new RouteScheduledCommand((DisplayElementDTO)nextRoute) );
        break;
      case STRING:
//        routeReader.requestRoute((String)nextRoute,
//          ((RouteRequestRouteName)currentRouteRequest).getRouteSourceName(), 
//          ((RouteRequestRouteName)currentRouteRequest).getTagList());
//        break;
      default:
        Window.alert("Unknown Request Type");
        break;
      }
    }
  }
  
  /**
   * @see com.jettmarks.routes.client.ui.RouteContainer#put(java.lang.String, com.jettmarks.routes.client.bean.Route)
   */
  @Override
  public void put(String routeName, Route route)
  {
//    loadedRoutes.put(routeName, route);

    mapView.add(route);
  }

}
