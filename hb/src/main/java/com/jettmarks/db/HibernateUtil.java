/**
 *   Copyright 2009 Jett Marks
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
 * Created Nov 1, 2009
 */
package com.jettmarks.db;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jettmarks.routes.server.bean.BikeTrain;
import com.jettmarks.routes.server.bean.BikeTrainElementGroup;
import com.jettmarks.routes.server.bean.DisplayElement;
import com.jettmarks.routes.server.bean.DisplayGroup;
import com.jettmarks.routes.server.bean.SuitabilitySegment;

/**
 * @author jett
 * 
 */
public class HibernateUtil
{
  /**
   * Kept around for unit testing; SessionFactory is the useful object.
   */
  private static Configuration cfg = null;

  private static Properties defaultProperties = null;

  private static Properties jndiProperties = null;
  private static Properties connectionPoolProperties = null;

  private static EnvironmentType envType = EnvironmentType.TEST;

  /** JNDI names. */
  public final static String JNDI_DATA_SOURCE = "com.jettmarks.hb.dataSource";

  /**
   * @return the sessionFactory
   */
  public static SessionFactory getSessionFactory()
  {
    return sessionFactory;
  }

  private static final SessionFactory sessionFactory;

  static
  {
    try
    {
      // cfg = new AnnotationConfiguration().configure();
      cfg = new Configuration();
      determineConfiguration();
      addMappings();

      sessionFactory = cfg.buildSessionFactory();
    }
    catch (Throwable ex)
    {
      // Log exception!
      throw new ExceptionInInitializerError(ex);
    }
  }

  /**
   * 
   */
  static void determineConfiguration()
  {
    if (haveInitialContext())
    {
      // If we have a DataSource in a JNDI object, use this
      jndiProperties = new Properties();
      jndiProperties.put("hibernate.connection.datasource",
          "java:/comp/env/jdbc/db/routes");
      jndiProperties.put("hibernate.connection.driver_class",
          "com.mysql.jdbc.Driver");
      cfg.addProperties(jndiProperties);
    }
    else
    {
      // Configure our own DataSource
      defaultProperties = new Properties();
      defaultProperties.put("hibernate.connection.username", "appId");
      defaultProperties.put("hibernate.connection.password", "pushdata");
      defaultProperties.put("hibernate.connection.driver_class",
          "com.mysql.jdbc.Driver");
      // Allow System properties to override this default
      String url = System.getProperty("db.url", "jdbc:mysql://phoenix/routes?");
      defaultProperties.put("hibernate.connection.url", url);
      cfg.addProperties(defaultProperties);
    }
    
    // Connection Pool Properties
    connectionPoolProperties = new Properties();
    connectionPoolProperties.put("hibernate.connection.provider_class", "org.hibernate.connection.C3P0ConnectionProvider");
    connectionPoolProperties.put("hibernate.c3p0.acquire_increment", "1");    
    connectionPoolProperties.put("hibernate.c3p0.idle_test_period", "100");    
    connectionPoolProperties.put("hibernate.c3p0.max_size", "100");    
    connectionPoolProperties.put("hibernate.c3p0.max_statements", "0");    
    connectionPoolProperties.put("hibernate.c3p0.min_size", "10");    
    connectionPoolProperties.put("hibernate.c3p0.timeout", "100");    

    cfg.addProperties(connectionPoolProperties);
  }

  /**
   * @return
   */
  private static boolean haveInitialContext()
  {
    // TODO: Code lookup of the Initial Context
    return false;
  }

  /**
   * 
   */
  static void addMappings()
  {
    cfg.addAnnotatedClass(BikeTrain.class)
       .addAnnotatedClass(DisplayGroup.class)
       .addAnnotatedClass(BikeTrainElementGroup.class)
       .addAnnotatedClass(SuitabilitySegment.class)
       .addAnnotatedClass(DisplayElement.class);
  }

  public static Session getSession() throws HibernateException
  {
    return sessionFactory.openSession();
    // return sessionFactory.getCurrentSession();
  }

  public static void shutdown()
  {
    // Close caches and connection pools
    getSessionFactory().close();
  }

  static Configuration getConfiguration()
  {
    return cfg;
  }

  static Properties getDefaultProperties()
  {
    return defaultProperties;
  }

  /**
   * @return the jndiProperties
   */
  static Properties getJndiProperties()
  {
    return jndiProperties;
  }

  /**
   * @return the envType
   */
  static EnvironmentType getEnvType()
  {
    return envType;
  }

  /**
   * Intended for testing config changes; flushes out old Configuration and
   * generates a new one.
   * 
   * @return
   */
  static boolean refreshConfig()
  {
    return true;
  }
}
