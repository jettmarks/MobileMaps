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

import javax.persistence.Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


/*
 * Description.
 * 
 * @author jett
 */
@Entity
public class BaseDAO
{

  protected Session session = null;

  /**
   * 
   */
  public BaseDAO()
  {
    super();
  }
  
  /**
   * Associates the session during the creation of the DAO.
   * 
   * @param session
   */
  public BaseDAO(Session session)
  {
    this();
    this.shareSession(session);
  }

  public Session startSession()
  {
    session = HibernateUtil.getSession();
    return session;
  }

  public void shareSession(Session session)
  {
    this.session = session;
  }
  
  public SessionFactory getSessionFactory()
  {
    return HibernateUtil.getSessionFactory();
  }
}