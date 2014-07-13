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
 * Created May 26, 2014
 */
package com.jettmarks.routes.client.ui;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.jettmarks.routes.client.DetailView;
import com.jettmarks.routes.client.bean.Route;

/**
 * An Event corresponds to a set of Bike Trains on a given date.
 * 
 * The view itself will have a Group that is used to bundle up the bike trains
 * for a given date and present the event in either a map, a list, or both.
 * 
 * @author jett
 */
public interface EventView extends DetailView
{
  public abstract void setDisplayGroupName(String displayGroupName);

  public abstract String getDisplayGroupName();

  public abstract void setDescription(String description);

  public abstract String getDescription();

  public abstract void add(Route route);

  /**
   * @return
   */
  public abstract HasTapHandlers getForwardbutton();

  /**
   * May want to refactor so RouteContainer doesn't have to know about it.
   */
  public abstract void resize();

  public abstract void enableForwardButton(boolean isEnabled);

  public abstract void selectRoute(Route route);

  /**
   * 
   */
  public abstract void clearMap();

}
