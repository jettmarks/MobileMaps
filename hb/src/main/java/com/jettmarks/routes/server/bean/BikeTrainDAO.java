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
 * Generated May 31, 2010 11:10:51 AM by Hibernate Tools 3.2.4.GA
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
 * Data Access Object (DAO) for domain model class BikeTrain.
 * 
 * @see com.jettmarks.routes.client.bean.BikeTrain
 * 
 * @author Hibernate Tools
 */
public class BikeTrainDAO extends BaseDAO
{
  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(BikeTrainDAO.class);

  // private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  public BikeTrainDAO(Session session)
  {
    super(session);
  }

  public SessionFactory getSessionFactory()
  {
    return super.getSessionFactory();
  }

  public void persist(BikeTrain transientInstance)
  {
    logger.debug("persisting BikeTrain instance");
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

  public Integer save(BikeTrain instance)
  {
    return (Integer) session.save(instance);
  }

  public void attachDirty(BikeTrain instance)
  {
    logger.debug("attaching dirty BikeTrain instance");
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

  @SuppressWarnings("deprecation")
  public void attachClean(BikeTrain instance)
  {
    logger.debug("attaching clean BikeTrain instance");
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

  public void delete(BikeTrain persistentInstance)
  {
    logger.debug("deleting BikeTrain instance");
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

  public BikeTrain merge(BikeTrain detachedInstance)
  {
    logger.debug("merging BikeTrain instance");
    try
    {
      BikeTrain result = (BikeTrain) session.merge(detachedInstance);
      logger.debug("merge successful");
      return result;
    }
    catch (RuntimeException re)
    {
      logger.error("merge failed", re);
      throw re;
    }
  }

  public BikeTrain findById(java.lang.Integer id)
  {
    logger.debug("getting BikeTrain instance with id: " + id);
    try
    {
      BikeTrain instance = (BikeTrain) session.get(
          "com.jettmarks.routes.server.bean.BikeTrain", id);
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
  public List findByExample(BikeTrain instance)
  {
    logger.debug("finding BikeTrain instance by example");
    try
    {
      List results = session.createCriteria(
                                "com.jettmarks.routes.server.bean.BikeTrain")
                            .add(Example.create(instance))
                            .list();
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
