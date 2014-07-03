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
 * Created Oct 20, 2010
 */
package com.jettmarks.routes.server.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.jettmarks.routes.client.bean.SuitabilityRating;

/**
 * Specifics of a Suitability Element carrying a SuitabilityRating and the name
 * of the local route.
 *
 * @author jett
 */
@Entity
public class SuitabilitySegment implements Serializable
{

  /**
   * 
   */
  private static final long serialVersionUID = -4053203065563671699L;

  @Id 
  @GeneratedValue
  @Column
  Integer id;
  
  @Column
  private String routeName = null; 
  
  @Column
  @Enumerated(EnumType.STRING)
  private SuitabilityRating rating = SuitabilityRating.UNRATED;

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
   * @return the id
   */
  public Integer getId()
  {
    return id;
  }
  
}
