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
 * Created Aug 9, 2010
 */
package com.jettmarks.routes.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;

/**
 * Service to obtain the tag names available on this instance.
 * 
 * Generally, this will be the list of directories available, but once expanded
 * to include the database objects, will pull from that source as well.
 * 
 * @author jett
 */

public interface GetTags extends RemoteService
{
  /** Retrieve list of existing tags. */
  public String[] getTagList();

  /** Retrieve list of tags available to user identified by token. */
  public String[] getTagList(String token);

  /** Retrieve list of DisplayGroups available for use. */
  public String[] getDisplayGroupList();

  /** Retrieve the current (latest) Display Group ID. */
  public int getLatestDisplayGroupID();

  /** Get array of all Display Groups. */
  public DisplayGroupDTO[] getDisplayGroups();

  /** Get array of active Display Groups. */
  public DisplayGroupDTO[] getActiveDisplayGroups();

  /** Get array of active Display Groups. */
  public DisplayGroupDTO[] getBikeTrains();

  /** Save New DisplayGroup. */
  public int saveDisplayGroup(String description, String displayGroupName);
}
