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

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * @author jett
 *
 */
public class HibernateUtil
{
  /**
   * @return the sessionFactory
   */
  public static SessionFactory getSessionFactory()
  {
    return sessionFactory;
  }
  private static final SessionFactory sessionFactory;

  static {
      try {
          sessionFactory = 
            new AnnotationConfiguration().configure().buildSessionFactory();
      } catch (Throwable ex) {
          // Log exception!
          throw new ExceptionInInitializerError(ex);
      }
  }

  public static Session getSession()
  throws HibernateException {
    return sessionFactory.openSession();
//    return sessionFactory.getCurrentSession();
  }
  
  public static void shutdown() {
    // Close caches and connection pools
    getSessionFactory().close();
  }
}
