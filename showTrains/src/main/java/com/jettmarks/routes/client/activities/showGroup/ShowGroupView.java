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
package com.jettmarks.routes.client.activities.showGroup;

import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.jettmarks.routes.client.DetailView;
import com.jettmarks.routes.client.bean.Route;

public interface ShowGroupView extends DetailView {

  public abstract void setDisplayGroupName(String displayGroupName);

  public abstract String getDisplayGroupName();

  public abstract void setDescription(String description);

  public abstract String getDescription();
  
  public abstract void add(Route route);

  public abstract HeaderButton getViewDetailButton();

  /**
   * 
   */
  public abstract void resize();

}
