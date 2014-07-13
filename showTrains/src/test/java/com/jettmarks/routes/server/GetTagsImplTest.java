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
 * Created Jul 13, 2014
 */
package com.jettmarks.routes.server;

import org.junit.Test;

import com.jettmarks.routes.client.bean.DisplayGroupDTO;

/**
 * Description.
 * 
 * @author jett
 */
public class GetTagsImplTest
{

  /**
   * Test method for
   * {@link com.jettmarks.routes.server.GetTagsImpl#getBikeTrains()}.
   */
  @Test
  public void testGetBikeTrains()
  {
    GetTagsImpl tagsImpl = new GetTagsImpl();
    DisplayGroupDTO[] bikeTrainList = tagsImpl.getBikeTrains();
    System.out.println("\nList of Bike Train Groups:");
    for (DisplayGroupDTO dg : bikeTrainList)
    {
      System.out.println(dg);
    }
  }

}
