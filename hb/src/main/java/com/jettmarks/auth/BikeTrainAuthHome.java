package com.jettmarks.auth;

// Generated Aug 9, 2014 8:35:06 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class BikeTrainAuth.
 * @see com.jettmarks.auth.BikeTrainAuth
 * @author Hibernate Tools
 */
public class BikeTrainAuthHome {

    private static final Log log = LogFactory.getLog(BikeTrainAuthHome.class);

    private final SessionFactory sessionFactory = getSessionFactory();

    protected SessionFactory getSessionFactory() {
	try {
	    return (SessionFactory) new InitialContext()
		    .lookup("SessionFactory");
	} catch (Exception e) {
	    log.error("Could not locate SessionFactory in JNDI", e);
	    throw new IllegalStateException(
		    "Could not locate SessionFactory in JNDI");
	}
    }

    public void persist(BikeTrainAuth transientInstance) {
	log.debug("persisting BikeTrainAuth instance");
	try {
	    sessionFactory.getCurrentSession().persist(transientInstance);
	    log.debug("persist successful");
	} catch (RuntimeException re) {
	    log.error("persist failed", re);
	    throw re;
	}
    }

    public void attachDirty(BikeTrainAuth instance) {
	log.debug("attaching dirty BikeTrainAuth instance");
	try {
	    sessionFactory.getCurrentSession().saveOrUpdate(instance);
	    log.debug("attach successful");
	} catch (RuntimeException re) {
	    log.error("attach failed", re);
	    throw re;
	}
    }

    public void attachClean(BikeTrainAuth instance) {
	log.debug("attaching clean BikeTrainAuth instance");
	try {
	    sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
	    log.debug("attach successful");
	} catch (RuntimeException re) {
	    log.error("attach failed", re);
	    throw re;
	}
    }

    public void delete(BikeTrainAuth persistentInstance) {
	log.debug("deleting BikeTrainAuth instance");
	try {
	    sessionFactory.getCurrentSession().delete(persistentInstance);
	    log.debug("delete successful");
	} catch (RuntimeException re) {
	    log.error("delete failed", re);
	    throw re;
	}
    }

    public BikeTrainAuth merge(BikeTrainAuth detachedInstance) {
	log.debug("merging BikeTrainAuth instance");
	try {
	    BikeTrainAuth result = (BikeTrainAuth) sessionFactory
		    .getCurrentSession().merge(detachedInstance);
	    log.debug("merge successful");
	    return result;
	} catch (RuntimeException re) {
	    log.error("merge failed", re);
	    throw re;
	}
    }

    public BikeTrainAuth findById(java.lang.Integer id) {
	log.debug("getting BikeTrainAuth instance with id: " + id);
	try {
	    BikeTrainAuth instance = (BikeTrainAuth) sessionFactory
		    .getCurrentSession().get(
			    "com.jettmarks.auth.BikeTrainAuth", id);
	    if (instance == null) {
		log.debug("get successful, no instance found");
	    } else {
		log.debug("get successful, instance found");
	    }
	    return instance;
	} catch (RuntimeException re) {
	    log.error("get failed", re);
	    throw re;
	}
    }

    public List<BikeTrainAuth> findByExample(BikeTrainAuth instance) {
	log.debug("finding BikeTrainAuth instance by example");
	try {
	    List<BikeTrainAuth> results = (List<BikeTrainAuth>) sessionFactory
		    .getCurrentSession()
		    .createCriteria("com.jettmarks.auth.BikeTrainAuth")
		    .add(create(instance)).list();
	    log.debug("find by example successful, result size: "
		    + results.size());
	    return results;
	} catch (RuntimeException re) {
	    log.error("find by example failed", re);
	    throw re;
	}
    }
}
