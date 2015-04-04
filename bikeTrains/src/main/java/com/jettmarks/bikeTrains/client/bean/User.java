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
 * Created Oct 27, 2009
 */
package com.jettmarks.bikeTrains.client.bean;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author jett
 *
 */
public class User implements IsSerializable
{
  private Name name = new Name();
  private String email = "";
  private String openID = "";
  private String displayName = "";
  
  /**
   * @return the name
   */
  public Name getName()
  {
    return name;
  }
  /**
   * @param name the name to set
   */
  public void setName(Name name)
  {
    this.name = name;
  }
  /**
   * @return the email
   */
  public String getEmail()
  {
    return email;
  }
  /**
   * @param email the email to set
   */
  public void setEmail(String email)
  {
    this.email = email;
  }
  /**
   * @return the openID
   */
  public String getOpenID()
  {
    return openID;
  }
  /**
   * @param openID the openID to set
   */
  public void setOpenID(String openID)
  {
    this.openID = openID;
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
   * Constructs a <code>String</code> with all attributes
   * in name = value format.
   *
   * @return a <code>String</code> representation 
   * of this object.
   */
  public String toString()
  {
      final String TAB = "  ";
      
      StringBuffer retValue = new StringBuffer();
      
      retValue.append("User ( ")
          .append(super.toString()).append(TAB)
          .append("name = ").append(this.name).append(TAB)
          .append("email = ").append(this.email).append(TAB)
          .append("openID = ").append(this.openID).append(TAB)
          .append("displayName = ").append(this.displayName).append(TAB)
          .append(" )");
      
      return retValue.toString();
  }

}
