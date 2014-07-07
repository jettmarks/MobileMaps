package com.jettmarks.hb_test.server;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.jettmarks.db.HibernateUtil;
import com.jettmarks.routes.server.bean.BikeTrain;
import com.jettmarks.routes.server.bean.BikeTrainDAO;

public class DumpBikeTrainsToLog {

	private static final Logger logger = Logger
			.getLogger(DumpBikeTrainsToLog.class);
  private static BikeTrain bikeTrainRetrieved;

	static {
		testFindById();
	}

	/**
	 * Test method for
	 * {@link com.jettmarks.routes.bean.BikeTrainDAO#findById(java.lang.Integer)}
	 * .
	 */
	public static void testFindById() {
		Session session = HibernateUtil.getSession();
		BikeTrainDAO bikeTrainDAO = new BikeTrainDAO(session);
		bikeTrainRetrieved = bikeTrainDAO.findById(3);
    logger.info("Retrieved: " + bikeTrainRetrieved);
	}

  /**
   * @return the description from teh Bike Train (route name actually)
   */
  public String getDescription()
  {
    return bikeTrainRetrieved.getRouteName();
  }

}
