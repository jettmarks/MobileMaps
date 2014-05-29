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
 * Created Oct 21, 2010
 */
package com.jettmarks.routes.client.rep;

import com.jettmarks.routes.client.bean.DisplayElementDTO;
import com.jettmarks.routes.client.bean.EncodedTrack;
import com.jettmarks.routes.client.bean.SuitabilityRating;
import com.jettmarks.routes.client.bean.SuitabilitySegmentDTO;


/**
 * Description.
 *
 * @author jett
 */
public class SuitabilitySegmentDE extends DisplayElementDTO
{
  /**
   * 
   */
  private static final long serialVersionUID = 2118919085006771503L;
  private SuitabilityRating rating = SuitabilityRating.UNRATED;
  private String routeName = null;
  private EncodedTrack encodedTrack = null;
  
  // Empty Constructor
  public SuitabilitySegmentDE() {};
  
  
  public SuitabilitySegmentDE(SuitabilitySegmentDTO sSegment)
  {
    setRating(sSegment.getRating());
    setRouteName(sSegment.getRouteName());
  }


  /**
   * @return the rating
   */
  public SuitabilityRating getRating()
  {
    return rating;
  }


  /**
   * @param rating the rating to set
   */
  public void setRating(SuitabilityRating rating)
  {
    this.rating = rating;
  }


  /**
   * @return the routeName
   */
  public String getRouteName()
  {
    return routeName;
  }


  /**
   * @param routeName the routeName to set
   */
  public void setRouteName(String routeName)
  {
    this.routeName = routeName;
  }


  /**
   * @param et
   */
  public void setEncodedTrack(EncodedTrack et)
  {
    this.encodedTrack = et;
  }


  /**
   * @return the encodedTrack
   */
  public EncodedTrack getEncodedTrack()
  {
    return encodedTrack;
  }

}
