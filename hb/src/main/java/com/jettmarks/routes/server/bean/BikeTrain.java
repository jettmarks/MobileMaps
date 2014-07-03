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
 * Created Apr 24, 2010
 */
package com.jettmarks.routes.server.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Description.
 *
 * @author jett
 */
@Entity
public class BikeTrain implements Serializable
{
  /** *  */
  private static final long serialVersionUID = -4583005121192909435L;

  @Id 
  @GeneratedValue
  @Column
  private Integer id;
  
  @Column
  private String routeName = null;
  @Column
  private String leaderName = null;
  @Column
  private String leaderEmail = null;
  @Column
  private String leaderPhone = null;
  
  @Column
  private String departureTime = null;
  @Column
  private String arrivalTime = null;
  @Column
  private String notes = null;
  
/* (non-Javadoc)
   * @see com.jettmarks.routes.bean.IBikeTrain#getLeaderName()
   */
  public String getLeaderName()
  {
    return leaderName;
  }
  /* (non-Javadoc)
   * @see com.jettmarks.routes.bean.IBikeTrain#setLeaderName(java.lang.String)
   */
  public void setLeaderName(String leaderName)
  {
    this.leaderName = leaderName;
  }
  /* (non-Javadoc)
   * @see com.jettmarks.routes.bean.IBikeTrain#getLeaderEmail()
   */
  public String getLeaderEmail()
  {
    return leaderEmail;
  }
  /* (non-Javadoc)
   * @see com.jettmarks.routes.bean.IBikeTrain#setLeaderEmail(java.lang.String)
   */
  public void setLeaderEmail(String leaderEmail)
  {
    this.leaderEmail = leaderEmail;
  }
  /* (non-Javadoc)
   * @see com.jettmarks.routes.bean.IBikeTrain#getLeaderPhone()
   */
  public String getLeaderPhone()
  {
    return leaderPhone;
  }
  /* (non-Javadoc)
   * @see com.jettmarks.routes.bean.IBikeTrain#setLeaderPhone(java.lang.String)
   */
  public void setLeaderPhone(String leaderPhone)
  {
    this.leaderPhone = leaderPhone;
  }
  /* (non-Javadoc)
   * @see com.jettmarks.routes.bean.IBikeTrain#getDepartureTime()
   */
  public String getDepartureTime()
  {
    return departureTime;
  }
  /* (non-Javadoc)
   * @see com.jettmarks.routes.bean.IBikeTrain#setDepartureTime(java.lang.String)
   */
  public void setDepartureTime(String departureTime)
  {
    this.departureTime = departureTime;
  }
  /* (non-Javadoc)
   * @see com.jettmarks.routes.bean.IBikeTrain#getArrivalTime()
   */
  public String getArrivalTime()
  {
    return arrivalTime;
  }
  /* (non-Javadoc)
   * @see com.jettmarks.routes.bean.IBikeTrain#setArrivalTime(java.lang.String)
   */
  public void setArrivalTime(String arrivalTime)
  {
    this.arrivalTime = arrivalTime;
  }
  /**
   * @param routeName
   * @return
   */
  public static BikeTrain getInstance(String routeName)
  {
    BikeTrain newInstance;
    if ("Sample-Bike-Train".equals(routeName))
    {
      newInstance = new BikeTrain();
	    newInstance.setArrivalTime("7:40 AM");
	    newInstance.setDepartureTime("7:00 AM");
	    newInstance.setLeaderEmail("dummy.email@nowhere.com");
	    newInstance.setLeaderName("David Byrne");
    }
    else if (routeName.startsWith("Emory-to-Coke"))
    {
      newInstance = new BikeTrain();
	    newInstance.setArrivalTime("8:00 AM");
	    newInstance.setDepartureTime("7:30 AM");
	    newInstance.setLeaderEmail("mike@atlantabike.org");
	    newInstance.setLeaderName("Michael Selik");
	    newInstance.setNotes("No ride if it's raining.");
    }
    else
    {
      newInstance = null;
	  }
    return newInstance;
  }
  /* (non-Javadoc)
   * @see com.jettmarks.routes.bean.IBikeTrain#getNotes()
   */
  public String getNotes()
  {
    return notes;
  }
  /* (non-Javadoc)
   * @see com.jettmarks.routes.bean.IBikeTrain#setNotes(java.lang.String)
   */
  public void setNotes(String notes)
  {
    this.notes = notes;
  }
  /* (non-Javadoc)
   * @see com.jettmarks.routes.bean.IBikeTrain#getRouteName()
   */
  public String getRouteName()
  {
    return routeName;
  }
  /* (non-Javadoc)
   * @see com.jettmarks.routes.bean.IBikeTrain#setRouteName(java.lang.String)
   */
  public void setRouteName(String routeName)
  {
    this.routeName = routeName;
  }
  /**
   * @param id the id to set
   */
  public void setId(Integer id)
  {
    this.id = id;
  }
  /**
   * @return the id
   */
  public Integer getId()
  {
    return id;
  }

}
