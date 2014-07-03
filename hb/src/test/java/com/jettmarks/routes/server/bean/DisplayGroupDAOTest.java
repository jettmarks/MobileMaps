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

import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;

import com.jettmarks.db.DAOTestBase;
import com.jettmarks.routes.server.bean.DisplayElement;
import com.jettmarks.routes.server.bean.DisplayGroup;
import com.jettmarks.routes.server.bean.DisplayGroupDAO;

/**
 * Description.
 *
 * @author jett
 */
/**
 * Description.
 * 
 * @author jett
 */
/**
 * Description.
 * 
 * @author jett
 */
public class DisplayGroupDAOTest extends DAOTestBase
{
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
   * Test method for {@link com.jettmarks.routes.server.bean.DisplayGroupDAO#findByExample(com.jettmarks.routes.server.bean.DisplayGroup)}.
   */
  @SuppressWarnings("unchecked")
  public void testFindByExample()
  {
    DisplayGroupDAO displayGroupDAO = new DisplayGroupDAO(session);
    DisplayGroup displayGroupExample = new DisplayGroup("dbTest");
    
    List<DisplayGroup> displayGroupList = displayGroupDAO.findByExample(
        displayGroupExample);
    assertEquals(1, displayGroupList.size());
    assertEquals(1, displayGroupList.get(0).getId().intValue());
  }
  
  /**
   *  Test to see if an empty example returns all records.
   */
  @SuppressWarnings("unchecked")
  public void testGetAll()
  {
    DisplayGroupDAO dao = new DisplayGroupDAO(session);
    DisplayGroup dgExample = new DisplayGroup();
    
    List<DisplayGroup> displayGroupList = dao.findByExample(dgExample);
    assertTrue("Did not receive several items", displayGroupList.size() > 2);
    for (DisplayGroup dg : displayGroupList)
    {
      System.out.println(dg.getDisplayName());
    }
  }
    
  /**
   * Test method for checking that we can pull across the Many-to-Many 
   * relationship with DisplayElements.
   */
  @SuppressWarnings("unchecked")
  public void testManyToManyElements()
  {
    DisplayGroupDAO displayGroupDAO = new DisplayGroupDAO(session);
    DisplayGroup displayGroupExample = new DisplayGroup("dbTest");
    
    List<DisplayGroup> displayGroupList = displayGroupDAO.findByExample(
        displayGroupExample);
    DisplayGroup displayGroupTest = displayGroupList.get(0);
    Set<DisplayElement> elements = displayGroupTest.getElements();
    assertTrue(7 < elements.size());
  }
  
//  @SuppressWarnings("unchecked")
//  public void testAddNewElement()
//  {
//    DisplayGroupDAO dgDAO = new DisplayGroupDAO(session);
//    DisplayGroup dgSuitMapExample = new DisplayGroup("suitMap");
//    
//    Transaction tx = session.beginTransaction();
//    // Stick a new instance out there
//    List<DisplayGroup> dgList = dgDAO.findByExample(dgSuitMapExample);
//    DisplayGroup dgSuitMap = dgList.get(0);
//    DisplayElement ssde = new DisplayElement();
//    ssde.setClassName("SuitabilitySegment");
//    ssde.setType(DisplayElementType.ROUTE);
//    DisplayElementDAO deDao = new DisplayElementDAO(session);
//    deDao.save(ssde);
//    dgSuitMap.getElements().add(ssde);
//    dgDAO.save(dgSuitMap);
//    session.flush();
//    
//    // Check the instance was picked up
//    
//    tx.commit();
//  }
  
  /**
   * Test method for {@link com.jettmarks.routes.bean.BikeTrainDAO#getSessionFactory()}.
   */
  public void testGetSessionFactory()
  {
    DisplayGroupDAO displayGroupDAO = new DisplayGroupDAO(session);
    SessionFactory sessionFactory = displayGroupDAO.getSessionFactory();
    assertNotNull(sessionFactory);
  }

  /**
   * Temporary test to see what is going on with creating this new mapping.
   * 
   */
  @SuppressWarnings("unchecked")
  public void testCheckMapping()
  {
    DisplayGroupDAO displayGroupDAO = new DisplayGroupDAO(session);
    DisplayGroup displayGroupExample = new DisplayGroup("btSummer2010");
    
    List<DisplayGroup> displayGroupList = displayGroupDAO.findByExample(
        displayGroupExample);
    assertNotNull(displayGroupList);
    assertTrue(displayGroupList.size() > 0);
    
    Set<DisplayElement> elements = displayGroupList.get(0).getElements();
    assertNotNull(elements);
    assertTrue(elements.size() > 0);
  }
}
