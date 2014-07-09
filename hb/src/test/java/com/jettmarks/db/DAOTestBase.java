/**
 * Copyright 2009 Jett Marks
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Created Dec 11, 2009
 */
package com.jettmarks.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import junit.framework.TestCase;

import org.hibernate.Session;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/*
 * Description.
 * 
 * @author jett
 */
public abstract class DAOTestBase extends TestCase {

	protected Session session = null;

	/**
   * 
   */
	public DAOTestBase() {
		super();
	}

	/**
	 * @param name
	 */
	public DAOTestBase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();

		initializeContext();
		session = HibernateUtil.getSession();
	}

	/**
 * 
 */
	void initializeContext() {

		Properties userDBProperties = getUserDBProperties();
		MysqlDataSource ds = getMySQLDataSource(userDBProperties);

		// This introduces a dependency on Tomcat libraries
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.naming.java.javaURLContextFactory");
		System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
		String jndiName = "java:/comp/env/jdbc/db/routes";
		InitialContext ic = null;
		// See if the context is already available
		try {
			ic = new InitialContext();
			ic.lookup(jndiName);
			return; // We have the context; no need to initialize
		} catch (NamingException e) {
			System.out
					.println("Initializing context for DataSource under the name "
							+ jndiName);
		}
		try {
			ic.createSubcontext("java:");
			ic.createSubcontext("java:/comp");
			ic.createSubcontext("java:/comp/env");
			ic.createSubcontext("java:/comp/env/jdbc");

			ic.bind("java:/comp/env/jdbc/db/routes", ds);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param userDBProperties
	 * @return
	 */
	MysqlDataSource getMySQLDataSource(Properties userDBProperties) {
		MysqlDataSource ds;
		ds = new MysqlDataSource();
		ds.setURL(userDBProperties.getProperty("url"));
		ds.setUser(userDBProperties.getProperty("username"));
		ds.setPassword(userDBProperties.getProperty("password"));
		return ds;
	}

	/**
	 * @return
	 */
	Properties getUserDBProperties() {
		Properties userDBProperties;
		String dbPropFileName = "db.properties";
		userDBProperties = new Properties();
		try {
			InputStream inputStream = DAOTestBase.class
					.getResourceAsStream(dbPropFileName);
			if (inputStream == null) {
				System.err.println("Database Properties file not found: "
						+ dbPropFileName);
			} else {
				userDBProperties.load(inputStream);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return userDBProperties;
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		session.close();
		// HibernateUtil.shutdown();
	}

}