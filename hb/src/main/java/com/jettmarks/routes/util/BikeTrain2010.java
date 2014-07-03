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
 * Created May 31, 2010
 */
package com.jettmarks.routes.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jettmarks.db.HibernateUtil;
import com.jettmarks.routes.server.bean.BikeTrain;
import com.jettmarks.routes.server.bean.BikeTrainDAO;

/**
 * Utility to populate the Database with the various Bike Trains we had assembled
 * for Bike to Work Day 2010.
 *
 * @author jett
 */
public class BikeTrain2010
{
  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(BikeTrain2010.class);

  // List of the Instances
  private static List<String> instanceList = new ArrayList<String>();
  private static HashMap<String,BikeTrain> bikeTrainHash = 
    new HashMap<String,BikeTrain>();
  
  static 
  {
    instanceList.add("Candler-Park-to-CDC-Chamblee-via-Emory");
    instanceList.add("Decatur-Midtown-Bike-Train419983");
    instanceList.add("East-Lake-Rail-to-Emory");
    instanceList.add("EastAtlanta-to-Emory");
    instanceList.add("EastPoint-BikeTrain-2010");
    instanceList.add("Echo-Lake-to-Emory");
    instanceList.add("Emory-from-OakGrove");
    instanceList.add("Emory-to-Coke-via-PATH");
    instanceList.add("Emory-to-Coke");
    instanceList.add("Energizer-Stations-2010");
    instanceList.add("Howell-Mill-to-North-and-Northside");
    instanceList.add("North-Lake-to-Emory");
    instanceList.add("Oakhurst-Midtown");
    instanceList.add("Oakhust-to-CDC-Roybal-vis-Decatur-and-Emory");
    instanceList.add("Tucker-to-CDC-Chamblee-via-Century-Center");
    
    populateHash();
  }

  /**
   * Returns instance from HashMap populated from the database.
   * 
   * @param routeName
   * @return
   */
  public static BikeTrain getInstance(String routeName)
  {
    return bikeTrainHash.get(routeName);
  }

  /**
   * Walk through each of the route names and persist the BikeTrain object 
   * corresponding to that route name.
   * 
   * @return number of instances saved.
   */
  public int saveInstances()
  {
    int instanceCount = instanceList.size();
    Session session = HibernateUtil.getSession();
    BikeTrainDAO bikeTrainDAO = new BikeTrainDAO(session);
    Transaction tx = session.beginTransaction();
    for (String routeName : instanceList)
    {
      BikeTrain bikeTrain = getCodeInstance(routeName);
      bikeTrainDAO.save(bikeTrain);
    }
    session.flush();
    tx.commit();
    return instanceCount;
  }
  
  /**
   * @throws HibernateException
   */
  @SuppressWarnings("unchecked")
  private static void populateHash() throws HibernateException
  {
    BikeTrainDAO bikeTrainDAO = new BikeTrainDAO(HibernateUtil.getSession());
    for (String routeName : instanceList)
    {
      logger.debug("Retrieving instance for route "+routeName);
      BikeTrain btExample = new BikeTrain();
      btExample.setRouteName(routeName);
      List<BikeTrain> btMatching= bikeTrainDAO.findByExample(btExample);
      if (btMatching != null && btMatching.size() > 0)
      {
	      bikeTrainHash.put(routeName, btMatching.get(0));
      }
      btExample = null;
    }
  }

  private BikeTrain getCodeInstance(String routeName)
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
    else if (routeName.startsWith("Energizer-Stations-2010"))
    {
      newInstance = new BikeTrain();
      newInstance.setArrivalTime("8:50 AM");
      newInstance.setDepartureTime("7:00 AM");
      newInstance.setLeaderEmail("jettmarks@bellsouth.net");
      newInstance.setLeaderName("Jett Marks");
      newInstance.setNotes("Hits five of this morning's 'Energizer Stations'.");
    }
    else if (routeName.startsWith("Emory-from-OakGrove"))
    {
      newInstance = new BikeTrain();
      newInstance.setArrivalTime("8:00 AM");
      newInstance.setDepartureTime("7:30 AM");
      newInstance.setLeaderEmail("lee.pasackow@emory.edu");
      newInstance.setLeaderName("Lee Pasackow");
      newInstance.setNotes("Will not ride if raining.");
    }
    else if (routeName.startsWith("EastPoint"))
    {
      newInstance = new BikeTrain();
      newInstance.setArrivalTime("8:30 AM");
      newInstance.setDepartureTime("7:00 AM");
      newInstance.setLeaderEmail("ali@atlantabike.org");
      newInstance.setLeaderName("Ali Mangkang");
      newInstance.setNotes("Stops at East Point and Oakland City MARTA.");
    }
    else if (routeName.startsWith("Tucker-to-CDC"))
    {
      newInstance = new BikeTrain();
      newInstance.setArrivalTime("8:15 AM");
      newInstance.setDepartureTime("7:00 AM");
      newInstance.setLeaderEmail("hfz9@cdc.gov");
      newInstance.setLeaderName("Elisa Restea");
      newInstance.setNotes("Makes a stop at Century Center.");
    }    
    else if (routeName.startsWith("Oakhust-to-CDC"))
    {
      newInstance = new BikeTrain();
      newInstance.setArrivalTime("8:30 AM");
      newInstance.setDepartureTime("7:45 AM");
      newInstance.setLeaderEmail("ftt6@cdc.gov & iow4@cdc.gov (respectively)");
      newInstance.setLeaderName("Ever Vega & Ben Lopman");
      newInstance.setNotes("Makes a stop in Decatur and the " +
          "Bike Emory Energizer Station.");
    }
    else if (routeName.startsWith("Oakhurst-Midtown"))
    {
      newInstance = new BikeTrain();
      newInstance.setArrivalTime("8:30 AM");
      newInstance.setDepartureTime("7:45 AM");
      newInstance.setLeaderEmail("corny73@aol.com");
      newInstance.setLeaderName("Corny");
      newInstance.setNotes("Makes a stop at the Edgewood/Krog Energizer Station.");
    }
    else if (routeName.startsWith("Candler-Park"))
    {
      newInstance = new BikeTrain();
      newInstance.setArrivalTime("8:45 AM");
      newInstance.setDepartureTime("7:00 AM");
      newInstance.setLeaderEmail("rpennotti@cdc.gov");
      newInstance.setLeaderName("Radha Pennotti");
      newInstance.setNotes("Includes stop at Bike Emory Energizer Station.");
    }
    else if (routeName.startsWith("Echo-Lake"))
    {
      newInstance = new BikeTrain();
      newInstance.setArrivalTime("7:50 AM");
      newInstance.setDepartureTime("7:10 AM");
      newInstance.setLeaderEmail("aryeh.stein@emory.edu");
      newInstance.setLeaderName("Aryeh Stein");
      newInstance.setNotes("Will not ride if raining");
    }
    else if (routeName.startsWith("East-Lake"))
    {
      newInstance = new BikeTrain();
      newInstance.setArrivalTime("7:20 AM");
      newInstance.setDepartureTime("7:00 AM");
      newInstance.setLeaderEmail("jnye@emory.edu");
      newInstance.setLeaderPhone("C: 651 325-7774");
      newInstance.setLeaderName("Jonathon Nye");
      newInstance.setNotes("Leaves from East Lake MARTA, north lot; " +
          "arrives Emory Energizer Station");
    }
    else if (routeName.startsWith("EastAtlanta"))
    {
      newInstance = new BikeTrain();
      newInstance.setArrivalTime("8:00 AM");
      newInstance.setDepartureTime("7:30 AM");
      newInstance.setLeaderEmail("jamie.smith@emory.edu");
      newInstance.setLeaderPhone("404 695-7975");
      newInstance.setLeaderName("Jamie Smith");
      newInstance.setNotes("If rains are heavy we will not meet.");
    }
    else if (routeName.startsWith("North-Lake"))
    {
      newInstance = new BikeTrain();
      newInstance.setArrivalTime("8:00 AM");
      newInstance.setDepartureTime("7:00 AM");
      newInstance.setLeaderEmail("ilg9@cdc.gov");
      newInstance.setLeaderName("Frances Tyrrell");
      newInstance.setNotes("Makes a stop at North Dekalb Mall and " +
          "Bike Emory Energizer Station.");
    }
    else if (routeName.startsWith("Decatur-Midtown-"))
    {
      newInstance = new BikeTrain();
      newInstance.setArrivalTime("8:40 AM");
      newInstance.setDepartureTime("7:45 AM");
      newInstance.setLeaderEmail("stephen@touset.org");
      newInstance.setLeaderName("Stephen Touset");
      newInstance.setNotes("Contact Stephen if you're interested in returning" +
          "<br> home along a similar route in the afternoon.");
    }
    else if (routeName.startsWith("Howell-Mill"))
    {
      newInstance = new BikeTrain();
      newInstance.setArrivalTime("8:30 AM");
      newInstance.setDepartureTime("9:00 AM");
      newInstance.setLeaderEmail("mtodd@highgroove.com");
      newInstance.setLeaderName("Matt Todd");
    }
    else
    {
      newInstance = null;
    }
    newInstance.setRouteName(routeName);
    return newInstance;
  }
}
