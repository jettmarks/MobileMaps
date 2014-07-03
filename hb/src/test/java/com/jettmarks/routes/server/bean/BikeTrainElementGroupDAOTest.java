/**
 * 
 */
package com.jettmarks.routes.server.bean;

import java.util.List;

import com.jettmarks.db.DAOTestBase;
import com.jettmarks.routes.server.bean.BikeTrainElementGroup;
import com.jettmarks.routes.server.bean.BikeTrainElementGroupDAO;

/**
 * @author jett
 *
 */
public class BikeTrainElementGroupDAOTest extends DAOTestBase {

	/**
	 * Test method for {@link com.jettmarks.routes.server.bean.BikeTrainElementGroupDAO#findByExample(BikeTrainElementGroup)}.
	 */
	public void testFindByExample() {
		BikeTrainElementGroupDAO dao = new BikeTrainElementGroupDAO(session);
		assertNotNull(dao);
		
		BikeTrainElementGroup btElementGroup = new BikeTrainElementGroup();
//		btElementGroup.setGroupDescription("Test Record");
//		btElementGroup.setGroupDescription("2010 Summer Bike Train");
//		btElementGroup.setGroupId(2);
		List<BikeTrainElementGroup> btList = dao.findByExample(btElementGroup);
		assertTrue(btList.size() > 0);
		
		System.out.println("Total records: "+btList.size());
		
		btElementGroup.setGroupDescription("Test Record");
		btList = dao.findByExample(btElementGroup);
		assertTrue(btList.size() > 0);
		
		System.out.println("Test records: "+btList.size());
		
	}

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
}
