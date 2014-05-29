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
package com.jettmarks.routes.client.bean;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jettmarks.routes.server.bean.BikeTrain;

/**
 * Description.
 *
 * @author jett
 */
public class BikeTrainDTO implements Serializable, IsSerializable
{

	
/**
 * 
 */
public BikeTrainDTO() {
	// TODO Auto-generated constructor stub
}

/**
   * @return the id
   */
  public Integer getId()
  {
    return id;
  }
  /** *  */
  private static final long serialVersionUID = -4583005121192909435L;

  Integer id;
  
  private String routeName = null;
  private String leaderName = null;
  private String leaderEmail = null;
  private String leaderPhone = null;
  
  private String departureTime = null;
  private String arrivalTime = null;
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
  public static BikeTrainDTO getInstance(String routeName)
  {
    BikeTrainDTO newInstance;
    if ("Sample-Bike-Train".equals(routeName))
    {
      newInstance = new BikeTrainDTO();
	    newInstance.setArrivalTime("7:40 AM");
	    newInstance.setDepartureTime("7:00 AM");
	    newInstance.setLeaderEmail("dummy.email@nowhere.com");
	    newInstance.setLeaderName("David Byrne");
    }
    else if (routeName.startsWith("Emory-to-Coke"))
    {
      newInstance = new BikeTrainDTO();
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
   * For remembering that this instance already has an Id.
   * @param id2
   */
  public void setId(Integer id2)
  {
    this.id = id2;
  }

}
