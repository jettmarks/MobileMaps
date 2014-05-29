/**
 *   Copyright 2009 Jett Marks
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
 * Created Oct 10, 2009
 */
package com.jettmarks.routes.server.common;

import java.util.HashMap;

/**
 * Knows about Hills.
 * 
 * @author jett
 */
public class Hill
{
  private static HashMap<String, Integer> categories = 
    new HashMap<String, Integer>();
  
  private static HashMap<String, Number> maxSlopes = 
    new HashMap<String, Number>();
  
  static 
  {
    categories.put("HoustonMill", 6 );
    categories.put("Vickers", 6 );
    categories.put("Curtis", 4 );
    categories.put("Glengary", 5 );
    categories.put("Whitner", 8 );
    categories.put("Errol", 8 );
    categories.put("Northside", 7 );
    categories.put("Cochise", 9 );
    categories.put("Stillhouse", 10 );
    categories.put("OverlookParkway", 9 );
    categories.put("WWesleyRidge", 9 );
    categories.put("MaryGeorge", 9 );
    categories.put("Hillpine", 7 );
    
    maxSlopes.put("HoustonMill", 12.5 );
    maxSlopes.put("Vickers", 11.5 );
    maxSlopes.put("Curtis", 8.5 );
    maxSlopes.put("Glengary", 12.5 );
    maxSlopes.put("Whitner", 14.5 );
    maxSlopes.put("Errol", 14.5 );
    maxSlopes.put("Northside", 10.0 );
    maxSlopes.put("Cochise", 19.0 );
    maxSlopes.put("Stillhouse", 23.0 );
    maxSlopes.put("OverlookParkway", 14.5 );
    maxSlopes.put("WWesleyRidge", 22 );
    maxSlopes.put("MaryGeorge", 20 );
    maxSlopes.put("Hillpine", 10.5 );
  }
  
  public static int getCategory(String routeName)
  {
    if (!categories.containsKey(routeName))
    {
      return 0;
    }
    else
    {
      return categories.get(routeName);
    }
  }
  
  public static Number getMaxSlope(String routeName)
  {
    if (!maxSlopes.containsKey(routeName))
    {
      return 0.0;
    }
    else
    {
      return (maxSlopes.get(routeName));
    }
  }
}
