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
 * Created Oct 21, 2010
 */
package com.jettmarks.routes.server.bean;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import com.jettmarks.db.DAOTestBase;
import com.jettmarks.routes.client.bean.SuitabilityRating;
import com.jettmarks.routes.server.bean.SuitabilitySegment;
import com.jettmarks.routes.server.bean.SuitabilitySegmentDAO;


/**
 * Description.
 *
 * @author jett
 */
public class SuitabilitySegmentDAOTest extends DAOTestBase
{
  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(SuitabilitySegmentDAOTest.class);
  
  /* (non-Javadoc)
   * @see com.jettmarks.db.DAOTestBase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {
    // TODO Auto-generated method stub
    super.setUp();
  }

  /* (non-Javadoc)
   * @see com.jettmarks.db.DAOTestBase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception
  {
    // TODO Auto-generated method stub
    super.tearDown();
  }

  /**
   * Test method for {@link com.jettmarks.routes.server.bean.SuitabilitySegmentDAO#persist(com.jettmarks.routes.server.bean.SuitabilitySegment)}.
   */
  public void testSave()
  {
    SuitabilitySegmentDAO sSegmentDAO = new SuitabilitySegmentDAO(session);
    SuitabilitySegment sSegment = new SuitabilitySegment();
    
    sSegment.setRouteName("testSegment");
    sSegment.setRating(SuitabilityRating.GREEN);
    
    // Open a transaction we can rollback when we're done
    Transaction tx = session.beginTransaction();
    
    Integer id = sSegmentDAO.save(sSegment);
    assertNotNull(id);
    assertTrue(id.intValue() > 0);
    logger.info("ID of new row is: "+id);
    
    session.flush();
    SuitabilitySegment retrievedSegment = sSegmentDAO.findById(id);
    assertNotNull(retrievedSegment);
    assertEquals("testSegment", retrievedSegment.getRouteName());
    assertEquals(SuitabilityRating.GREEN, retrievedSegment.getRating());
    
    // Clean up
    tx.rollback();
    
  }

  /**
   * Test method for {@link com.jettmarks.routes.client.bean.SuitabilitySegmentDAO#findByExample(com.jettmarks.routes.client.bean.SuitabilitySegment)}.
   */
//  @SuppressWarnings("unchecked")
//  public void testFindByExample()
//  {
//    SuitabilitySegmentDAO sSegmentDAO = new SuitabilitySegmentDAO(session);
//    SuitabilitySegment sSegment = new SuitabilitySegment();
//    sSegment.setRouteName("Test-Route");
//    
//    List<SuitabilitySegment> sSegmentList = sSegmentDAO.findByExample(sSegment);
//    assertEquals(1, sSegmentList.size());
//    assertEquals(SuitabilityRating.UNRATED, sSegmentList.get(0).getRating());
//    
//  }

}
