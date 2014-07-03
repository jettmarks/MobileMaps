package com.jettmarks.routes.server.bean;

/**
 *   Copyright 2010 Jett Marks
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
 * Generated Nov 21, 2011 10:21:06 PM by Hibernate Tools 3.3.0.GA
 */
// default package

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

import org.hibernate.Session;
import com.jettmarks.db.HibernateUtil;
import com.jettmarks.db.BaseDAO;

/**
 * Data Access Object (DAO) for domain model class BikeTrainGroup.
 * @see .BikeTrainGroup
 * @author Hibernate Tools
 */
public class BikeTrainGroupDAO extends BaseDAO {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(BikeTrainGroupDAO.class);

	// private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public BikeTrainGroupDAO(Session session) {
		super(session);
	}

	public SessionFactory getSessionFactory() {
		return super.getSessionFactory();
	}

	public void persist(BikeTrainGroup transientInstance) {
		logger.debug("persisting BikeTrainGroup instance");
		try {
			session.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public Integer save(BikeTrainGroup instance) {
		return (Integer) session.save(instance);
	}

	public void attachDirty(BikeTrainGroup instance) {
		logger.debug("attaching dirty BikeTrainGroup instance");
		try {
			session.saveOrUpdate(instance);
			logger.debug("attach successful");
		} catch (RuntimeException re) {
			logger.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BikeTrainGroup instance) {
		logger.debug("attaching clean BikeTrainGroup instance");
		try {
			session.lock(instance, LockMode.NONE);
			logger.debug("attach successful");
		} catch (RuntimeException re) {
			logger.error("attach failed", re);
			throw re;
		}
	}

	public void delete(BikeTrainGroup persistentInstance) {
		logger.debug("deleting BikeTrainGroup instance");
		try {
			session.delete(persistentInstance);
			logger.debug("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	public BikeTrainGroup merge(BikeTrainGroup detachedInstance) {
		logger.debug("merging BikeTrainGroup instance");
		try {
			BikeTrainGroup result = (BikeTrainGroup) session
					.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public BikeTrainGroup findById(BikeTrainGroupId id) {
		logger.debug("getting BikeTrainGroup instance with id: " + id);
		try {
			BikeTrainGroup instance = (BikeTrainGroup) session.get(
					"BikeTrainGroup", id);
			if (instance == null) {
				logger.debug("get successful, no instance found");
			} else {
				logger.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<BikeTrainGroup> findByExample(BikeTrainGroup instance) {
		logger.debug("finding BikeTrainGroup instance by example");
		try {
			List<BikeTrainGroup> results = (List<BikeTrainGroup>) session
					.createCriteria("BikeTrainGroup").add(create(instance))
					.list();
			logger.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			logger.error("find by example failed", re);
			throw re;
		}
	}
}
