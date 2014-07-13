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
package com.jettmarks.routes.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jettmarks.db.HibernateUtil;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.service.GetTags;
import com.jettmarks.routes.common.LocalDrive;
import com.jettmarks.routes.server.bean.DisplayGroup;
import com.jettmarks.routes.server.bean.DisplayGroupDAO;
import com.jettmarks.routes.server.bean.DisplayGroupWrapper;
import com.jettmarks.routes.server.common.RemoteServiceServletSeparatePaths;
import com.jettmarks.routes.util.BikeTrainGroups;

/**
 * Description.
 * 
 * @author jett
 */
public class GetTagsImpl extends RemoteServiceServletSeparatePaths implements
                                                                  GetTags
{
  static Collection<String> restrictedList = new ArrayList<String>();

  static
  {
    restrictedList.add("test");
    restrictedList.add("master");
    restrictedList.add("working");
    restrictedList.add("CyclingRides.kmz");
  }

  /**
   * 
   */
  private static final long serialVersionUID = 1821224152005800705L;

  /**
   * @see com.jettmarks.routes.client.service.GetTags#getTagList()
   */
  public String[] getTagList()
  {
    return LocalDrive.getTagList();
  }

  /**
   * @see com.jettmarks.routes.client.service.GetTags#getTagList(java.lang.String)
   * 
   *      Version of getTagList that restricts the tags based on whether the
   *      user has permission or not.
   * 
   *      Initial version just checks to see if the user has a token or not.
   * 
   */
  public String[] getTagList(String token)
  {
    // Abbreviated test to see if we can show the full list of tags
    boolean fullAccess = (token != null && token.equals("4048747114"));

    String[] fullList = LocalDrive.getTagList();
    List<String> limitedList = new ArrayList<String>();

    for (String tag : fullList)
    {
      if (fullAccess || !restrictedList.contains(tag))
      {
        limitedList.add(tag);
      }
    }
    String[] returnedList = new String[limitedList.size()];
    int i = 0;
    for (String tag : limitedList)
    {
      returnedList[i++] = tag;
    }
    return returnedList;
  }

  @SuppressWarnings("unchecked")
  public String[] getDisplayGroupList()
  {
    Session session = HibernateUtil.getSession();
    DisplayGroupDAO dao = new DisplayGroupDAO(session);
    DisplayGroup dgExample = new DisplayGroup();

    List<DisplayGroup> displayGroupList = dao.findByExample(dgExample);
    String[] returnList = new String[displayGroupList.size()];
    int i = 0;
    for (DisplayGroup dg : displayGroupList)
    {
      returnList[i++] = dg.getDisplayName();
    }
    return returnList;
  }

  /**
   * Out of the full list, return only the Bike Train Display Groups.
   * 
   * @return String Array containing the list of DisplayGroups that start with
   *         'bt'.
   */
  public String[] getBikeTrainDisplayGroupList()
  {
    String[] candidateList = getDisplayGroupList();
    String[] qualifiedList = new String[candidateList.length];
    int matchingItemCount = 0;
    for (String dg : candidateList)
    {
      if (dg.startsWith("bt") || dg.contains("challenge"))
      {
        qualifiedList[matchingItemCount++] = dg;
      }
    }
    String[] finalList = new String[matchingItemCount];
    for (int i = 0; i < matchingItemCount; i++)
    {
      finalList[i] = qualifiedList[i];
    }
    return finalList;
  }

  /**
   * Makes call to static method that returns the Display Group ID of the most
   * recent Display Group.
   * 
   * @see com.jettmarks.routes.client.service.GetTags#getLatestDisplayGroupID()
   */
  public int getLatestDisplayGroupID()
  {
    return BikeTrainGroups.getLatestDisplayGroupID();
  }

  /**
   * Named getDisplayGroups, but it actually picks up anything starting with
   * "bt" in the name.
   * 
   * @see com.jettmarks.routes.client.service.GetTags#getDisplayGroups()
   */
  @Override
  public DisplayGroupDTO[] getDisplayGroups()
  {
    Session session = HibernateUtil.getSession();
    DisplayGroupDAO dao = new DisplayGroupDAO(session);
    DisplayGroup dgExample = new DisplayGroup();

    List<DisplayGroup> displayGroupList = dao.findByExample(dgExample);
    DisplayGroupDTO[] returnList = new DisplayGroupDTO[displayGroupList.size()];
    int i = 0;
    for (DisplayGroup dg : displayGroupList)
    {
      if (dg.getDisplayName().startsWith("bt"))
      {
        returnList[i++] = DisplayGroupWrapper.getDTO(dg);
      }
    }
    return returnList;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.jettmarks.routes.client.service.GetTags#getActiveDisplayGroups()
   */
  @Override
  public DisplayGroupDTO[] getActiveDisplayGroups()
  {
    Session session = HibernateUtil.getSession();
    DisplayGroupDAO dao = new DisplayGroupDAO(session);

    List<DisplayGroup> displayGroupList = dao.findActive();
    DisplayGroupDTO[] returnList = new DisplayGroupDTO[displayGroupList.size()];
    int i = 0;
    for (DisplayGroup dg : displayGroupList)
    {
      if (dg.getDisplayName().startsWith("bt"))
      {
        returnList[i++] = DisplayGroupWrapper.getDTO(dg);
      }
    }
    return returnList;
  }

  /**
   * Currently takes advantage of the fact that getDisplayGroups currently
   * returns only bike trains.
   * 
   * The values are sorted according to the Event Date and descending so the
   * most recent event is at the front of the list.
   * 
   * The array had been sized for all Display Groups. It is resized here to
   * match the number of Bike Trains.
   * 
   * @see com.jettmarks.routes.client.service.GetTags#getBikeTrains()
   */
  @Override
  public DisplayGroupDTO[] getBikeTrains()
  {
    DisplayGroupDTO[] dgArray = getDisplayGroups();
    List<DisplayGroupDTO> dgList = new ArrayList<DisplayGroupDTO>();
    int count = 0;
    for (DisplayGroupDTO dg : dgArray)
    {
      if (dg != null)
      {
        dgList.add(dg);
        count++;
      }
    }
    // Sort the list in descending date order
    Collections.sort(dgList, new Comparator<DisplayGroupDTO>()
    {
      @Override
      public int compare(DisplayGroupDTO o1, DisplayGroupDTO o2)
      {
        if (o1 == null)
          return -1;
        if (o2 == null)
          return +1;
        long result = (o1.getEventDate().getTime() - o2.getEventDate()
                                                       .getTime());
        if (result == 0)
          return 0;
        return (result > 0) ? -1 : 1;
      }
    });

    DisplayGroupDTO[] dgResult = new DisplayGroupDTO[count];
    int i = 0;
    for (DisplayGroupDTO dg : dgList)
    {
      dgResult[i++] = dg;
    }
    return dgResult;
  }

  /**
   * Persists the pair of strings as a new Display Group.
   * 
   * @see com.jettmarks.routes.client.service.GetTags#saveDisplayGroup(java.lang.String,
   *      java.lang.String)
   */
  public int saveDisplayGroup(String description, String displayGroupName)
  {
    Session session = HibernateUtil.getSession();
    DisplayGroupDAO dao = new DisplayGroupDAO(session);
    DisplayGroup dgNew = new DisplayGroup();
    dgNew.setDescription(description);
    dgNew.setDisplayName(displayGroupName);
    Transaction tx = session.beginTransaction();
    int result = dao.save(dgNew);
    tx.commit();
    return result;
  }
}
