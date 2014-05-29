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
package com.jettmarks.routes.client.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Holds the details of a Display Group.
 * 
 * The key information from a user perspective is the name.  This would be 
 * supplied via a parameter on a URL and passed to the server to request a
 * list of DisplayElements.  On the server side, the full details could be 
 * populated in a database and for that reason, this class shows up in two 
 * different packages: one for being part of the user interface (URL parm) and
 * one for being persisted to the DB.
 *
 * @author jett
 */
public class DisplayGroupDTO implements Serializable, IsSerializable
{
  /**
   * 
   */
  private static final long serialVersionUID = -2883085351317048923L;
  private Set<DisplayElementDTO> elements = new HashSet<DisplayElementDTO>(0);


  Integer id;
  
  /** User's key information. */
  private String displayName = null;
  
  /** Additional information in case the name isn't sufficient. */
  private String description = null;
  
  /** Date of the Event -- particularly for Bike Trains. */
  private Date eventDate = null;
  
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return "DisplayGroupDTO [id="
           + id
           + ", displayName="
           + displayName
           + ", description="
           + description
           + ", eventDate="
           + eventDate
           + "]";
  }

  /**
   * @return the eventDate
   */
  public Date getEventDate()
  {
    return eventDate;
  }

  /**
   * @param eventDate the eventDate to set
   */
  public void setEventDate(Date eventDate)
  {
    this.eventDate = eventDate;
  }

  /** 
   * Constructor recommended for users.
   * 
   * @param displayName
   */
  public DisplayGroupDTO(String displayName)
  {
    this.displayName = displayName;
  }
  
  /**
   * Constructor required by serialization.
   */
  public DisplayGroupDTO()
  {
  }

  /**
   * @return the displayName
   */
  public String getDisplayName()
  {
    return displayName;
  }

  /**
   * @param displayName the displayName to set
   */
  public void setDisplayName(String displayName)
  {
    this.displayName = displayName;
  }

  /**
   * @return the description
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description)
  {
    this.description = description;
  }

  /**
   * @return the elements
   */
  public Set<DisplayElementDTO> getElements()
  {
    return elements;
  }

  /**
   * @param elements the elements to set
   */
  public void setElements(Set<DisplayElementDTO> elements)
  {
    this.elements = elements;
  }

  /**
   * @return the id
   */
  public Integer getId()
  {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id)
  {
    this.id = id;
  }

}
