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
package com.jettmarks.routes.client.bean;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author jett
 *
 */
public class Name implements IsSerializable
{
  private String givenName = "";
  private String familyName = "";
  private String formattedName = "";
  /**
   * @return the givenName
   */
  public String getGivenName()
  {
    return givenName;
  }
  /**
   * @param givenName the givenName to set
   */
  public void setGivenName(String givenName)
  {
    this.givenName = givenName;
  }
  /**
   * @return the familyName
   */
  public String getFamilyName()
  {
    return familyName;
  }
  /**
   * @param familyName the familyName to set
   */
  public void setFamilyName(String familyName)
  {
    this.familyName = familyName;
  }
  /**
   * @return the formattedName
   */
  public String getFormattedName()
  {
    return formattedName;
  }
  /**
   * @param formattedName the formattedName to set
   */
  public void setFormattedName(String formattedName)
  {
    this.formattedName = formattedName;
  }
  

}
