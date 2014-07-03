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
 * Created May 29, 2010
 */
package com.jettmarks.routes.bean;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jettmarks.db.DAOTestBase;
import com.jettmarks.routes.server.bean.BikeTrain;
import com.jettmarks.routes.server.bean.BikeTrainDAO;

/**
 * Description.
 *
 * @author jett
 */
public class BikeTrainDAOTest extends DAOTestBase
{
  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(BikeTrainDAOTest.class);

  /* (non-Javadoc)
   * @see junit.framework.TestCase#setUp()
   */
  protected void setUp() throws Exception
  {
    super.setUp();
  }

  /* (non-Javadoc)
   * @see junit.framework.TestCase#tearDown()
   */
  protected void tearDown() throws Exception
  {
    super.tearDown();
  }

  /**
   * Test method for {@link com.jettmarks.routes.bean.BikeTrainDAO#getSessionFactory()}.
   */
  public void testGetSessionFactory()
  {
    BikeTrainDAO bikeTrainDAO = new BikeTrainDAO(session);
    SessionFactory sessionFactory = bikeTrainDAO.getSessionFactory();
    assertNotNull(sessionFactory);
  }

  /**
   * Test method for {@link com.jettmarks.routes.bean.BikeTrainDAO#persist(com.jettmarks.routes.bean.BikeTrain)}.
   */
  public void testSave()
  {
    BikeTrainDAO bikeTrainDAO = new BikeTrainDAO(session);
    BikeTrain bikeTrain = new BikeTrain();
    
    bikeTrain.setDepartureTime("7:00 AM");
    bikeTrain.setArrivalTime("9:00 AM");
    bikeTrain.setLeaderName("David Byrne");
    bikeTrain.setLeaderEmail("d.byrne@nowhere.net");
    bikeTrain.setRouteName("Emory-to-GaTech");
    
    // Open a transaction we can rollback when we're done
    Transaction tx = session.beginTransaction();
    
    Integer id = bikeTrainDAO.save(bikeTrain);
    assertNotNull(id);
    assertTrue(id.intValue() > 0);
    logger.debug("Id of new row is: "+id);
    
    session.flush();
    BikeTrain retrievedBikeTrain = bikeTrainDAO.findById(id);
    assertNotNull(retrievedBikeTrain);
    assertEquals("David Byrne", retrievedBikeTrain.getLeaderName());
    assertEquals("Emory-to-GaTech", retrievedBikeTrain.getRouteName());
    
    // Clean up
    tx.rollback();
  }

  /**
   * Test method for {@link com.jettmarks.routes.bean.BikeTrainDAO#attachDirty(com.jettmarks.routes.bean.BikeTrain)}.
   */
  public void testAttachDirty()
  {
//    fail("Not yet implemented");
  }

  /**
   * Test method for {@link com.jettmarks.routes.bean.BikeTrainDAO#attachClean(com.jettmarks.routes.bean.BikeTrain)}.
   */
  public void testAttachClean()
  {
//    fail("Not yet implemented");
  }

  /**
   * Test method for {@link com.jettmarks.routes.bean.BikeTrainDAO#delete(com.jettmarks.routes.bean.BikeTrain)}.
   */
  @SuppressWarnings("unchecked")
  public void testDelete()
  {
    BikeTrainDAO bikeTrainDAO = new BikeTrainDAO(session);
    BikeTrain bikeTrainExample = new BikeTrain();
    bikeTrainExample.setRouteName("Emory-to-Coke-Test");
    
    Transaction tx = session.beginTransaction();
    List<BikeTrain> bikeTrainList = bikeTrainDAO.findByExample(bikeTrainExample);
    assertNotNull(bikeTrainList);
    assertEquals(1, bikeTrainList.size());
    
    BikeTrain bikeTrainToDelete = bikeTrainList.get(0);
    bikeTrainDAO.delete(bikeTrainToDelete);
    session.flush();
    
    bikeTrainList = bikeTrainDAO.findByExample(bikeTrainExample);
    assertNotNull(bikeTrainList);
    assertEquals(0, bikeTrainList.size());
    
    tx.rollback();
    
  }

  /**
   * Test method for {@link com.jettmarks.routes.bean.BikeTrainDAO#merge(com.jettmarks.routes.bean.BikeTrain)}.
   */
  @SuppressWarnings("unchecked")
  public void testMerge()
  {
    BikeTrainDAO bikeTrainDAO = new BikeTrainDAO(session);
    BikeTrain bikeTrainExample = new BikeTrain();
    bikeTrainExample.setRouteName("Emory-to-Coke-Test");
    
    Transaction tx = session.beginTransaction();
    List<BikeTrain> bikeTrainList = bikeTrainDAO.findByExample(bikeTrainExample);
    assertNotNull(bikeTrainList);
    assertEquals(1, bikeTrainList.size());
    
    BikeTrain bikeTrainToMerge = bikeTrainList.get(0);
    bikeTrainToMerge.setLeaderEmail("new.address@nosuch.edu");
    bikeTrainDAO.merge(bikeTrainToMerge);
    
    bikeTrainList = bikeTrainDAO.findByExample(bikeTrainExample);
    assertNotNull(bikeTrainList);
    assertEquals("new.address@nosuch.edu", bikeTrainList.get(0).getLeaderEmail());
    assertEquals(3, bikeTrainList.get(0).getId().intValue());
    
    tx.rollback();
  }

  /**
   * Test method for {@link com.jettmarks.routes.bean.BikeTrainDAO#findById(java.lang.Integer)}.
   */
  public void testFindById()
  {
    BikeTrainDAO bikeTrainDAO = new BikeTrainDAO(session);
    BikeTrain bikeTrainRetrieved = bikeTrainDAO.findById(3);
    
    assertNotNull(bikeTrainRetrieved);
    assertEquals("The Leader", bikeTrainRetrieved.getLeaderName());
    assertEquals("123 456-7890", bikeTrainRetrieved.getLeaderPhone());
  }

  /**
   * Test method for {@link com.jettmarks.routes.bean.BikeTrainDAO#findByExample(com.jettmarks.routes.bean.BikeTrain)}.
   */
  @SuppressWarnings("unchecked")
  public void testFindByExample()
  {
    BikeTrainDAO bikeTrainDAO = new BikeTrainDAO(session);
    BikeTrain bikeTrainExample = new BikeTrain();
    bikeTrainExample.setRouteName("Emory-to-Coke-Test");
    
    List<BikeTrain> bikeTrainList = bikeTrainDAO.findByExample(bikeTrainExample);
    assertEquals(1, bikeTrainList.size());
    assertEquals(3, bikeTrainList.get(0).getId().intValue());
  }

}
