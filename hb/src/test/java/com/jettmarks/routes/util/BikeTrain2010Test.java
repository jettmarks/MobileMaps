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
 * Created May 31, 2010
 */
package com.jettmarks.routes.util;

import junit.framework.TestCase;

import com.jettmarks.routes.server.bean.BikeTrain;


/**
 * Description.
 *
 * @author jett
 */
public class BikeTrain2010Test extends TestCase
{

  /**
   * Test method for {@link com.jettmarks.routes.util.BikeTrain2010#saveInstances()}.
   * 
   * Can be used to populate the database, but will gladly add a second copy if
   * records already exist.
   */
  public void testSaveInstances()
  {
//    BikeTrain2010 bikeTrainUtil = new BikeTrain2010();
//    int numberOfInstances = bikeTrainUtil.saveInstances();
//    assertTrue(numberOfInstances > 0);
  }

  public void testGetInstance()
  {
    BikeTrain btTest = BikeTrain2010.getInstance("Energizer-Stations-2010");
    assertNotNull(btTest);
    assertEquals("jettmarks@bellsouth.net", btTest.getLeaderEmail());
  }
}
