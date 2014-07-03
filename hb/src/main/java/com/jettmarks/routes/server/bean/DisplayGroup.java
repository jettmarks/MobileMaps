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
package com.jettmarks.routes.server.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
@Entity
public class DisplayGroup implements Serializable
{
  /**
   * 
   */
  private static final long serialVersionUID = -2883085351317048923L;
  private Set<DisplayElement> elements = new HashSet<DisplayElement>(0);


  Integer id;
  
  /** User's key information. */
  @Column
  private String displayName = null;
  
  /** Additional information in case the name isn't sufficient. */
  @Column
  private String description = null;
  
  /** Event Date of the Display Group (if any, mostly for Bike Trains). */
  @Column
  private Date eventDate = null;
  
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
  public DisplayGroup(String displayName)
  {
    this.displayName = displayName;
  }
  
  /**
   * Constructor required by serialization.
   */
  public DisplayGroup()
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
  @ManyToMany
  @JoinTable(name = "MAP_ELEMENT_GROUP", 
      joinColumns = {@JoinColumn(name = "GROUP_ID")},
      inverseJoinColumns = {@JoinColumn(name = "ELEMENT_ID")} )
  public Set<DisplayElement> getElements()
  {
    return elements;
  }

  /**
   * @param elements the elements to set
   */
  public void setElements(Set<DisplayElement> elements)
  {
    this.elements = elements;
  }

  /**
   * @return the id
   */
  @Id 
  @GeneratedValue
  @Column(name = "GROUP_ID")
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
