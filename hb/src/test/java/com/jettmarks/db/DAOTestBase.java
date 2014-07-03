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

import org.hibernate.Session;

import com.jettmarks.db.HibernateUtil;

import junit.framework.TestCase;

/*
 * Description.
 * 
 * @author jett
 */
public abstract class DAOTestBase extends TestCase
{

  protected Session session = null;

  /**
   * 
   */
  public DAOTestBase()
  {
    super();
  }

  /**
   * @param name
   */
  public DAOTestBase(String name)
  {
    super(name);
  }

  protected void setUp() throws Exception
  {
    super.setUp();
    session = HibernateUtil.getSession();
  }

  protected void tearDown() throws Exception
  {
    super.tearDown();
    session.close();
//    HibernateUtil.shutdown();
  }

}