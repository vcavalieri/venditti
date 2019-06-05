package com.garage.model;
// Generated May 24, 2019 10:38:24 AM by Hibernate Tools 5.2.12.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Vehicleinfo.
 * 
 * @see com.garage.model.Vehicleinfo
 * @author Hibernate Tools
 */
@Stateless
public class VehicleinfoHome {

	private static final Log log = LogFactory.getLog(VehicleinfoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Vehicleinfo transientInstance) {
		log.debug("persisting Vehicleinfo instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Vehicleinfo persistentInstance) {
		log.debug("removing Vehicleinfo instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Vehicleinfo merge(Vehicleinfo detachedInstance) {
		log.debug("merging Vehicleinfo instance");
		try {
			Vehicleinfo result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Vehicleinfo findById(int id) {
		log.debug("getting Vehicleinfo instance with id: " + id);
		try {
			Vehicleinfo instance = entityManager.find(Vehicleinfo.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
