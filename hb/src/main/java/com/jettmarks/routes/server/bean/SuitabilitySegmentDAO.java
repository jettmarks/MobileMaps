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
 * Generated Oct 20, 2010 10:39:07 PM by Hibernate Tools 3.2.4.GA
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
 * Data Access Object (DAO) for domain model class SuitabilitySegment.
 * 
 * @see com.jettmarks.routes.server.bean.SuitabilitySegment
 * 
 * @author Hibernate Tools
 */
public class SuitabilitySegmentDAO extends BaseDAO
{
  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(SuitabilitySegmentDAO.class);

  // private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  public SuitabilitySegmentDAO(Session session)
  {
    super(session);
  }

  public SessionFactory getSessionFactory()
  {
    return super.getSessionFactory();
  }

  public void persist(SuitabilitySegment transientInstance)
  {
    logger.debug("persisting SuitabilitySegment instance");
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

  public Integer save(SuitabilitySegment instance)
  {
    return (Integer) session.save(instance);
  }

  public void attachDirty(SuitabilitySegment instance)
  {
    logger.debug("attaching dirty SuitabilitySegment instance");
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

  public void attachClean(SuitabilitySegment instance)
  {
    logger.debug("attaching clean SuitabilitySegment instance");
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

  public void delete(SuitabilitySegment persistentInstance)
  {
    logger.debug("deleting SuitabilitySegment instance");
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

  public SuitabilitySegment merge(SuitabilitySegment detachedInstance)
  {
    logger.debug("merging SuitabilitySegment instance");
    try
    {
      SuitabilitySegment result = (SuitabilitySegment) session.merge(detachedInstance);
      logger.debug("merge successful");
      return result;
    }
    catch (RuntimeException re)
    {
      logger.error("merge failed", re);
      throw re;
    }
  }

  public SuitabilitySegment findById(java.lang.Integer id)
  {
    logger.debug("getting SuitabilitySegment instance with id: " + id);
    try
    {
      SuitabilitySegment instance = (SuitabilitySegment) session.get(
          "com.jettmarks.routes.server.bean.SuitabilitySegment", id);
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
  public List findByExample(SuitabilitySegment instance)
  {
    logger.debug("finding SuitabilitySegment instance by example");
    try
    {
      List results = session.createCriteria(
          "com.jettmarks.routes.server.bean.SuitabilitySegment").add(
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
