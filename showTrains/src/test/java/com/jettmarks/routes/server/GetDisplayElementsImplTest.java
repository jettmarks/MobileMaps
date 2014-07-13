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
 * Created Aug 10, 2010
 */
package com.jettmarks.routes.server;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.jettmarks.routes.client.bean.DisplayElementDTO;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.rep.RouteOnlyDE;

/**
 * Description.
 * 
 * @author jett
 */
public class GetDisplayElementsImplTest extends TestCase
{

  /**
   * Test method for
   * {@link com.jettmarks.routes.server.GetDisplayElementsImpl#getElementList(com.jettmarks.routes.client.ui.DisplayGroupDTO)}
   * .
   */
  @Test
  public void testGetElementList()
  {
    GetDisplayElementsImpl getDisplayElementsImpl = new GetDisplayElementsImpl();
    assertNotNull(getDisplayElementsImpl);
    DisplayGroupDTO displayGroup = new DisplayGroupDTO();
    displayGroup.setDisplayName("test");
    assertNotNull(displayGroup);

    List<DisplayElementDTO> result = getDisplayElementsImpl.getElementList(displayGroup);
    assertTrue(result.size() == 3);

    displayGroup = new DisplayGroupDTO();
    displayGroup.setDisplayName("something unexpected");
    result = getDisplayElementsImpl.getElementList(displayGroup);
    assertNull(result);
  }

  /*
   * public void testGetBikeTrain() { GetDisplayElementsImpl
   * getDisplayElementsImpl = new GetDisplayElementsImpl();
   * assertNotNull(getDisplayElementsImpl); DisplayElementDTO de = new
   * DisplayElementDTO(); de.setClassName("BikeTrain"); de.setSourceId(new
   * Integer(3));
   * 
   * BikeTrainDE bikeTrainDE = getDisplayElementsImpl.getBikeTrain(de);
   * assertNotNull(bikeTrainDE); BikeTrainDTO bikeTrain =
   * bikeTrainDE.getBikeTrain(); assertNotNull(bikeTrain);
   * assertEquals("The Leader", bikeTrain.getLeaderName());
   * 
   * EncodedTrack encodedTrack = bikeTrainDE.getEncodedTrack();
   * assertNotNull(encodedTrack); }
   */

  /**
   * Exercise the code for obtaining the full list of bike trains in the
   * database.
   * 
   * Route Only List.
   */
  public void testGetBikeTrainGroups()
  {
    GetDisplayElementsImpl gde = new GetDisplayElementsImpl();
    assertNotNull(gde);

    System.out.println("\nList of Routes");
    for (RouteOnlyDE routeOnlyDE : gde.getRouteOnlyList())
    {
      System.out.println(routeOnlyDE.getRouteName());
    }
  }
}
