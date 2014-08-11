package com.jettmarks.auth;

// Generated Aug 9, 2014 8:35:06 PM by Hibernate Tools 3.4.0.CR1

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jettmarks.db.BaseDAO;

/**
 * DAO object for domain model class FBAuth.
 * 
 * @see com.jettmarks.auth.FBAuth
 * @author Hibernate Tools
 */
public class FBAuthDAO extends BaseDAO {

    private static final Log log = LogFactory.getLog(FBAuthDAO.class);

    /**
     * @param session
     */
    public FBAuthDAO(Session session) {
	super(session);
    }

    public SessionFactory getSessionFactory() {
	return super.getSessionFactory();
    }

    // private final SessionFactory sessionFactory = getSessionFactory();

    // protected SessionFactory getSessionFactory() {
    // try {
    // return (SessionFactory) new InitialContext()
    // .lookup("SessionFactory");
    // } catch (Exception e) {
    // log.error("Could not locate SessionFactory in JNDI", e);
    // throw new IllegalStateException(
    // "Could not locate SessionFactory in JNDI");
    // }
    // }

    public void persist(FBAuth transientInstance) {
	log.debug("persisting Fbauth instance");
	try {
	    session.persist(transientInstance);
	    log.debug("persist successful");
	} catch (RuntimeException re) {
	    log.error("persist failed", re);
	    throw re;
	}
    }

    public Integer save(FBAuth instance) {
	return (Integer) session.save(instance);
    }

    public void attachDirty(FBAuth instance) {
	log.debug("attaching dirty Fbauth instance");
	try {
	    session.saveOrUpdate(instance);
	    log.debug("attach successful");
	} catch (RuntimeException re) {
	    log.error("attach failed", re);
	    throw re;
	}
    }

    @SuppressWarnings("deprecation")
    public void attachClean(FBAuth instance) {
	log.debug("attaching clean Fbauth instance");
	try {
	    session.lock(instance, LockMode.NONE);
	    log.debug("attach successful");
	} catch (RuntimeException re) {
	    log.error("attach failed", re);
	    throw re;
	}
    }

    public void delete(FBAuth persistentInstance) {
	log.debug("deleting Fbauth instance");
	try {
	    session.delete(persistentInstance);
	    log.debug("delete successful");
	} catch (RuntimeException re) {
	    log.error("delete failed", re);
	    throw re;
	}
    }

    public FBAuth merge(FBAuth detachedInstance) {
	log.debug("merging Fbauth instance");
	try {
	    FBAuth result = (FBAuth) session.merge(detachedInstance);
	    log.debug("merge successful");
	    return result;
	} catch (RuntimeException re) {
	    log.error("merge failed", re);
	    throw re;
	}
    }

    public FBAuth findById(java.lang.Integer id) {
	log.debug("getting FBAuth instance with id: " + id);
	try {
	    FBAuth instance = (FBAuth) session.get("com.jettmarks.auth.FBAuth",
		    id);
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

    public List<FBAuth> findByExample(FBAuth instance) {
	log.debug("finding Fbauth instance by example");
	try {
	    List<FBAuth> results = (List<FBAuth>) session
		    .createCriteria("com.jettmarks.auth.FBAuth")
		    .add(create(instance)).list();
	    log.debug("find by example successful, result size: "
		    + results.size());
	    return results;
	} catch (RuntimeException re) {
	    log.error("find by example failed", re);
	    throw re;
	}
    }

    /**
     * @param validatedId
     * @return
     */
    public List<FBAuth> findFBAuthsByValidatedId(String validatedId) {
	FBAuth fbAuth = new FBAuth();
	fbAuth.setValidatedId(validatedId);
	List<FBAuth> authList = findByExample(fbAuth);
	return authList;
    }
}
