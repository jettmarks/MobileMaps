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
 * Created May 11, 2014
 */
package com.jettmarks.bikeTrains.client.bean;

import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.overlays.Polyline;

/**
   * 
   * @author jett
   * 
   */
  public class MyPolyline extends Polyline
  {
    /** Used to tie back to the enclosing Route. */
    String name;
  
    public MyPolyline(LatLng[] pts, String color, int zoom, String name)
    {
//      super(pts, color, zoom);
      this.name = name;
    }
  
    /**
     * 
     * @return
     */
    public String getName()
    {
      return this.name;
    }
  }