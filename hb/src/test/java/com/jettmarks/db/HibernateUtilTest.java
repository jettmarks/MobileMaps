/**
 *   Copyright 2014 Jett Marks
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
 * Created Jul 3, 2014
 */
package com.jettmarks.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;
import org.junit.Test;

import com.jettmarks.routes.server.bean.BikeTrain;
import com.jettmarks.routes.server.bean.BikeTrainDAO;

/**
 * Description.
 *
 * @author jett
 */
public class HibernateUtilTest extends DAOTestBase
{

  /**
   * Test method for {@link com.jettmarks.db.HibernateUtil#getSessionFactory()}.
   */
  @Test
  public void testGetSessionFactory()
  {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    assertNotNull(sessionFactory);
  }

  /**
   * Test method for {@link com.jettmarks.db.HibernateUtil#getSession()}.
   */
  @Test
  public void testGetSession()
  {
    Session session = HibernateUtil.getSession();
    assertNotNull(session);
    // HibernateUtil.shutdown();
  }

  /**
   * Test method for {@link com.jettmarks.db.HibernateUtil#getConfiguration()}.
   */
  @Test
  public void testGetConfiguration()
  {
    Configuration config = HibernateUtil.getConfiguration();
    assertNotNull(config);
    Iterator<PersistentClass> classMappingList = config.getClassMappings();
    assertNotNull(classMappingList);
    int classCount = 0;
    while (classMappingList.hasNext())
    {
      System.out.println("Mapped Class: " + classMappingList.next().toString());
      classCount++;
    }
    assertTrue("Expecting 5 mapped classes", classCount == 5);
  }

  /**
   * Test method for {@link com.jettmarks.db.HibernateUtil#getDefaultProperties()}.
   */
  @Test
  public void testGetDefaultProperties()
  {
    Properties properties = HibernateUtil.getDefaultProperties();
    assertNotNull(properties);

  }

  /**
   * Test method for {@link com.jettmarks.db.HibernateUtil#getJndiProperties()}.
   */
  @Test
  public void testGetJndiProperties()
  {
    Properties properties = HibernateUtil.getJndiProperties();
    assertNotNull(properties);
  }

  /**
   * Test method for {@link com.jettmarks.db.HibernateUtil#getEnvType()}.
   */
  @Test
  public void testGetEnvType()
  {
    EnvironmentType env = HibernateUtil.getEnvType();
    assertNotNull(env);
  }

  @Test
  public void testExerciseSQL()
  {
    Session session = HibernateUtil.getSession();

    BikeTrainDAO bikeTrainDAO = new BikeTrainDAO(session);
    BikeTrain bikeTrainRetrieved = bikeTrainDAO.findById(3);

    assertNotNull(bikeTrainRetrieved);
    assertEquals("The Leader", bikeTrainRetrieved.getLeaderName());
    assertEquals("123 456-7890", bikeTrainRetrieved.getLeaderPhone());

    session.close();
  }

}
