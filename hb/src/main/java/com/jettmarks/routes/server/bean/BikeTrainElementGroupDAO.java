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
 * Generated Jan 2, 2011 5:14:55 PM by Hibernate Tools 3.2.4.GA
 */
package com.jettmarks.routes.server.bean;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jettmarks.db.BaseDAO;

/*
 * Data Access Object (DAO) for domain model class BikeTrainElementGroup.
 * 
 * @see com.jettmarks.routes.client.bean.BikeTrainElementGroup
 * 
 * @author Hibernate Tools
 */
public class BikeTrainElementGroupDAO extends BaseDAO
{
  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(BikeTrainElementGroupDAO.class);

  // private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  public BikeTrainElementGroupDAO(Session session)
  {
    super(session);
  }

  public SessionFactory getSessionFactory()
  {
    return super.getSessionFactory();
  }

  public void persist(BikeTrainElementGroup transientInstance)
  {
    logger.debug("persisting BikeTrainElementGroup instance");
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

  public Integer save(BikeTrainElementGroup instance)
  {
    return (Integer) session.save(instance);
  }

  public void attachDirty(BikeTrainElementGroup instance)
  {
    logger.debug("attaching dirty BikeTrainElementGroup instance");
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

  public void attachClean(BikeTrainElementGroup instance)
  {
    logger.debug("attaching clean BikeTrainElementGroup instance");
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

  public void delete(BikeTrainElementGroup persistentInstance)
  {
    logger.debug("deleting BikeTrainElementGroup instance");
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

  public BikeTrainElementGroup merge(BikeTrainElementGroup detachedInstance)
  {
    logger.debug("merging BikeTrainElementGroup instance");
    try
    {
      BikeTrainElementGroup result = (BikeTrainElementGroup) session.merge(detachedInstance);
      logger.debug("merge successful");
      return result;
    }
    catch (RuntimeException re)
    {
      logger.error("merge failed", re);
      throw re;
    }
  }

  public BikeTrainElementGroup findById(com.jettmarks.routes.server.bean.BikeTrainElementGroup id)
  {
    logger.debug("getting BikeTrainElementGroup instance with id: " + id);
    try
    {
      BikeTrainElementGroup instance = (BikeTrainElementGroup) session.get(
          "com.jettmarks.routes.server.bean.BikeTrainElementGroup", id);
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
  public List<BikeTrainElementGroup> findByExample(BikeTrainElementGroup instance)
  {
    logger.debug("finding BikeTrainElementGroup instance by example");
    try
    {
      List<BikeTrainElementGroup> results = (List<BikeTrainElementGroup>) session.createCriteria(
                                                                                     "com.jettmarks.routes.server.bean.BikeTrainElementGroup")
                                                                                 .add(
                                                                                     create(instance))
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
