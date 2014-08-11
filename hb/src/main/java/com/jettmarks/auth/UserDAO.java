package com.jettmarks.auth;

// Generated Aug 9, 2014 8:35:06 PM by Hibernate Tools 3.4.0.CR1

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;

import com.jettmarks.db.BaseDAO;

/**
 * Home object for domain model class User.
 * 
 * @see com.jettmarks.auth.User
 * @author Hibernate Tools
 */
public class UserDAO extends BaseDAO {

    public UserDAO(Session session) {
	super(session);
    }

    private static final Log log = LogFactory.getLog(UserDAO.class);

    // private final SessionFactory sessionFactory = getSessionFactory();
    //
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

    public void persist(User transientInstance) {
	log.debug("persisting User instance");
	try {
	    session.persist(transientInstance);
	    log.debug("persist successful");
	} catch (RuntimeException re) {
	    log.error("persist failed", re);
	    throw re;
	}
    }

    public Integer save(User instance) {
	return (Integer) session.save(instance);
    }

    public void attachDirty(User instance) {
	log.debug("attaching dirty User instance");
	try {
	    session.saveOrUpdate(instance);
	    log.debug("attach successful");
	} catch (RuntimeException re) {
	    log.error("attach failed", re);
	    throw re;
	}
    }

    @SuppressWarnings("deprecation")
    public void attachClean(User instance) {
	log.debug("attaching clean User instance");
	try {
	    session.lock(instance, LockMode.NONE);
	    log.debug("attach successful");
	} catch (RuntimeException re) {
	    log.error("attach failed", re);
	    throw re;
	}
    }

    public void delete(User persistentInstance) {
	log.debug("deleting User instance");
	try {
	    session.delete(persistentInstance);
	    log.debug("delete successful");
	} catch (RuntimeException re) {
	    log.error("delete failed", re);
	    throw re;
	}
    }

    public User merge(User detachedInstance) {
	log.debug("merging User instance");
	try {
	    User result = (User) session.merge(detachedInstance);
	    log.debug("merge successful");
	    return result;
	} catch (RuntimeException re) {
	    log.error("merge failed", re);
	    throw re;
	}
    }

    public User findById(Integer id) {
	log.debug("getting User instance with id: " + id);
	try {
	    User instance = (User) session.get("com.jettmarks.auth.User", id);
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

    public List<User> findByExample(User instance) {
	log.debug("finding User instance by example");
	try {
	    @SuppressWarnings("unchecked")
	    List<User> results = (List<User>) session
		    .createCriteria("com.jettmarks.auth.User")
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
