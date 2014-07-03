/**
 * Copyright 2010 Jett Marks
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
 * Generated Aug 10, 2010 7:34:16 PM by Hibernate Tools 3.2.4.GA
 */
package com.jettmarks.routes.server.bean;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.jettmarks.db.BaseDAO;

/*
 * Data Access Object (DAO) for domain model class DisplayElement.
 * 
 * @see com.jettmarks.routes.client.bean.DisplayElement
 * 
 * @author Hibernate Tools
 */
public class DisplayElementDAO extends BaseDAO
{
  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(DisplayElementDAO.class);

  // private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  public DisplayElementDAO(Session session)
  {
    super(session);
  }

  public SessionFactory getSessionFactory()
  {
    return super.getSessionFactory();
  }

  public void persist(DisplayElement transientInstance)
  {
    logger.debug("persisting DisplayElement instance");
    try
    {
      session.persist(transientInstance);
      logger.debug("persist successful");
    }
    catch (RuntimeException re)
    {
      logger.error("persist failed", re);
      throw re;
    }
  }

  public Integer save(DisplayElement instance)
  {
    return (Integer) session.save(instance);
  }

  public void attachDirty(DisplayElement instance)
  {
    logger.debug("attaching dirty DisplayElement instance");
    try
    {
      session.saveOrUpdate(instance);
      logger.debug("attach successful");
    }
    catch (RuntimeException re)
    {
      logger.error("attach failed", re);
      throw re;
    }
  }

  public void attachClean(DisplayElement instance)
  {
    logger.debug("attaching clean DisplayElement instance");
    try
    {
      session.lock(instance, LockMode.NONE);
      logger.debug("attach successful");
    }
    catch (RuntimeException re)
    {
      logger.error("attach failed", re);
      throw re;
    }
  }

  public void delete(DisplayElement persistentInstance)
  {
    logger.debug("deleting DisplayElement instance");
    try
    {
      session.delete(persistentInstance);
      logger.debug("delete successful");
    }
    catch (RuntimeException re)
    {
      logger.error("delete failed", re);
      throw re;
    }
  }

  public DisplayElement merge(DisplayElement detachedInstance)
  {
    logger.debug("merging DisplayElement instance");
    try
    {
      DisplayElement result = (DisplayElement) session.merge(detachedInstance);
      logger.debug("merge successful");
      return result;
    }
    catch (RuntimeException re)
    {
      logger.error("merge failed", re);
      throw re;
    }
  }

  public DisplayElement findById(java.lang.Integer id)
  {
    logger.debug("getting DisplayElement instance with id: " + id);
    try
    {
      DisplayElement instance = (DisplayElement) session.get(
          "com.jettmarks.routes.client.bean.DisplayElement", id);
      if (instance == null)
      {
        logger.debug("get successful, no instance found");
      }
      else
      {
        logger.debug("get successful, instance found");
      }
      return instance;
    }
    catch (RuntimeException re)
    {
      logger.error("get failed", re);
      throw re;
    }
  }

  @SuppressWarnings("unchecked")
  public List findByExample(DisplayElement instance)
  {
    logger.debug("finding DisplayElement instance by example");
    try
    {
      List results = session.createCriteria(
          "com.jettmarks.routes.client.bean.DisplayElement").add(
          Example.create(instance)).list();
      logger.debug("find by example successful, result size: " + results.size());
      return results;
    }
    catch (RuntimeException re)
    {
      logger.error("find by example failed", re);
      throw re;
    }
  }
}
