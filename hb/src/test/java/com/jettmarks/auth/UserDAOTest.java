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
 * Created Aug 10, 2014
 */
package com.jettmarks.auth;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.junit.Test;

import com.jettmarks.db.DAOTestBase;
import com.jettmarks.routes.bean.BikeTrainDAOTest;
import com.jettmarks.routes.server.bean.BikeTrain;

/**
 * Description.
 * 
 * @author jett
 */
public class UserDAOTest extends DAOTestBase {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
	    .getLogger(BikeTrainDAOTest.class);

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
	super.setUp();
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
	super.tearDown();
    }

    @Test
    public void testGetBikeTrains() {
	UserDAO uDao = new UserDAO(session);
	int userIdJett = 10;
	User userJett = uDao.findById(userIdJett);

	logger.info("Jett has participated in "
		+ userJett.getBikeTrains().size() + " bike trains:");
	for (BikeTrain bt : userJett.getBikeTrains()) {
	    logger.info(bt.toString());
	}

    }

    @Test
    public void testGetUserAuths() {
	UserDAO uDao = new UserDAO(session);
	int userIdJett = 10;
	User userJett = uDao.findById(userIdJett);

	logger.info("Jett has the following Auths "
		+ userJett.getFbAuths().size() + " for Facebook: ");
	for (FBAuth auth : userJett.getFbAuths()) {
	    logger.info(auth.toString());
	}
    }

    /**
     * Test method for
     * {@link com.jettmarks.auth.UserDAO#persist(com.jettmarks.auth.UserId)}.
     */
    @Test
    public void testPersist() {
	UserDAO uDao = new UserDAO(session);
	User user = new User();

	user.setFirstName("firstName");
	user.setLastName("lastName");
	user.setEmail("email@donotreply.com");
	user.setPhone("678-123-4567");

	// Open a transaction we can rollback when we're done
	Transaction tx = session.beginTransaction();

	Integer id = uDao.save(user);
	assertNotNull(id);
	assertTrue(id.intValue() > 0);
	logger.debug("Id of new row is: " + id);

	session.flush();
	User userRetrieved = uDao.findById(id);
	assertNotNull(userRetrieved);
	assertEquals("firstName", userRetrieved.getFirstName());
	assertEquals("lastName", userRetrieved.getLastName());

	// Clean up
	tx.rollback();
    }

}
