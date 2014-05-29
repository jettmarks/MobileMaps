/**
 *   Copyright 2010 Jett Marks
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
 * Created Aug 13, 2010
 */
package com.jettmarks.routes.client.bean;

import java.util.List;

import com.google.gwt.user.client.Window;

/**
 * Description.
 *
 * @author jett
 */
public class RouteRequestDE extends RouteRequestBase implements RouteRequest
{
  List<DisplayElementDTO> displayElementList = null;
  
  /**
   * @param result
   */
  public RouteRequestDE(List<DisplayElementDTO> result)
  {
    super();
    displayElementList = result;
    setTotalTasks(result.size());
//    Window.alert("Total Tasks is "+getTotalTasks());
  }


  /* (non-Javadoc)
   * @see com.jettmarks.routes.client.bean.RouteRequest#next()
   */
  public Object next()
  {
    DisplayElementDTO de = displayElementList.get(requestedTasks++);
    if (de == null)
    {
      Window.alert("We've got a null inside next() call");
    }
    return de;
  }

}
