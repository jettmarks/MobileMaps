/**
 *   Copyright 2011 Jett Marks
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
 * Created Aug 27, 2011
 */
package com.jettmarks.routes.util;

import junit.framework.TestCase;

/**
 * Exercises BikeTrainGroups to show the last added Bike Train Group.
 *
 * @author jett
 */
public class BikeTrainGroupsTest extends TestCase
{

  /**
   * Test method for {@link com.jettmarks.routes.util.BikeTrainGroups#getLatestDisplayGroupID()}.
   */
  public void testGetLatestDisplayGroupID()
  {
    System.out.println("Latest BikeTrainGroup: "+BikeTrainGroups.getLatestDisplayGroupID());
//    assertTrue(BikeTrainGroups.getLatestDisplayGroupID() == 7);
  }

}
