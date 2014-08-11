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
 * Created Aug 9, 2014
 */
package com.jettmarks.auth;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.junit.Test;

import com.jettmarks.db.DAOTestBase;

/**
 * Description.
 * 
 * @author jett
 */
public class FBAuthDAOTest extends DAOTestBase {

    private static final Logger logger = Logger.getLogger(FBAuthDAOTest.class);

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

    /**
     * Test method for
     * {@link com.jettmarks.auth.FBAuthDAO#persist(com.jettmarks.auth.FBAuth)}.
     */
    @Test
    public void testSave() {
	FBAuth fbAuth = new FBAuth();
	fbAuth.setFirstName("firstName");
	fbAuth.setLastName("lastName");
	fbAuth.setValidatedId("some goofy string");
	fbAuth.setEmail("email@donotreply.com");

	FBAuthDAO fbAuthDao = new FBAuthDAO(session);
	Transaction tx = session.beginTransaction();
	Integer id = fbAuthDao.save(fbAuth);
	assertNotNull(id);
	assertTrue(id.intValue() > 0);
	logger.debug("Id of new row is: " + id);
	// session.flush();

	FBAuth retrievedAuth = fbAuthDao.findById(id);
	assertNotNull(retrievedAuth);
	assertEquals("firstName", retrievedAuth.getFirstName());

	// Clean up
	tx.rollback();
    }

    @Test
    public void testLookupByValidateId() {

	FBAuthDAO fbAuthDao = new FBAuthDAO(session);
	Transaction tx = session.beginTransaction();

	String validatedId = "some goofy string";
	List<FBAuth> authList = fbAuthDao.findFBAuthsByValidatedId(validatedId);

	assertNotNull(authList);
	assertTrue(authList.size() > 0);
	logger.info("Matches found:");
	for (FBAuth auth : authList) {
	    logger.info(auth);
	}

	// Clean up
	tx.rollback();
    }

}
