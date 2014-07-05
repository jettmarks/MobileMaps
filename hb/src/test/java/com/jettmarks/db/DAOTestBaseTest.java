package com.jettmarks.db;

import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;

public class DAOTestBaseTest {

	@Test
	public void testGetUserDBProperties() {
		DAOTestBase testBase = new DAOTestBase(){};
		Properties userDBProperties = testBase.getUserDBProperties();
		assertNotNull(userDBProperties);
		String url = userDBProperties.getProperty("url");
		assertNotNull(url);
	}

	@Test
	public void testGetMySQLDataSource() {
		DAOTestBase testBase = new DAOTestBase(){};
		Properties userDBProperties = testBase.getUserDBProperties();
		DataSource ds = testBase.getMySQLDataSource(userDBProperties);
		assertNotNull(ds);
	}

	@Test
	public void testInitializeContext() {
		DAOTestBase testBase = new DAOTestBase(){};
		testBase.initializeContext();
		
		// Check that the context has our object loaded
	}

}
