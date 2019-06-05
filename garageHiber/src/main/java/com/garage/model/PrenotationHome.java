package com.garage.model;
// Generated May 24, 2019 10:38:24 AM by Hibernate Tools 5.2.12.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Prenotation.
 * 
 * @see com.garage.model.Prenotation
 * @author Hibernate Tools
 */
@Stateless
public class PrenotationHome {

	private static final Log log = LogFactory.getLog(PrenotationHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Prenotation transientInstance) {
		log.debug("persisting Prenotation instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Prenotation persistentInstance) {
		log.debug("removing Prenotation instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Prenotation merge(Prenotation detachedInstance) {
		log.debug("merging Prenotation instance");
		try {
			Prenotation result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Prenotation findById(Integer id) {
		log.debug("getting Prenotation instance with id: " + id);
		try {
			Prenotation instance = entityManager.find(Prenotation.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
