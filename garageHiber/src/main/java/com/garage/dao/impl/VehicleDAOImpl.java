package com.garage.dao.impl;

import java.sql.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.garage.dao.TransactionManager;
import com.garage.dao.VehicleDAO; 
import com.garage.exception.VehicleException; 
import com.garage.model.SearchFilter;
import com.garage.model.Vehicle;
import com.garage.utils.SingletonHiberUtil;

public class VehicleDAOImpl implements VehicleDAO {

	private static final Log log = LogFactory.getLog(VehicleDAOImpl.class);

	@Autowired
	private ApplicationContext ctx;

	@SuppressWarnings({ "unchecked", "deprecation", "static-access" })
	@Override
	public List<Vehicle> searchVehicle(SearchFilter filter) throws VehicleException {

		List<Vehicle> toReturn = (List<Vehicle>) ctx.getBean("vehicleList");
		Session session = null;
		Transaction tx = null;
		try {
			session = ctx.getBean(SingletonHiberUtil.class).getSession();
			tx = session.beginTransaction();
			log.warn("Transaction started");
			Criteria criteria = session.createCriteria(Vehicle.class);
			if (filter.getIdVehicle() != 0) {
				criteria.add(Restrictions.eq("idvehicle", filter.getIdVehicle()));
			}
			if (filter.getLicensePlate() != null && filter.getLicensePlate() != "") {
				criteria.add(Restrictions.eq("licenseplate", filter.getLicensePlate()));
			}
			if (filter.getBrand() != null && filter.getBrand() != "") {
				criteria.add(Restrictions.eq("brand", filter.getBrand()));
			}
			toReturn = criteria.list();
			tx.commit();
			log.warn("Transaction committed");
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteVehicle(Vehicle vehicle) throws VehicleException {

		boolean status = false;
		List<Vehicle> vehicleList = (List<Vehicle>) ctx.getBean("vehicleList");
		try {
			TransactionManager<Vehicle> txMan = (TransactionManager<Vehicle>) ctx.getBean("txManVehicle");
			vehicleList = txMan.search(vehicle);
			for (Vehicle vehicles : vehicleList) {
				vehicle = null;
				vehicle = vehicles;
			}
			status = txMan.delete(vehicle);
		} catch (Exception e) {
			throw new VehicleException(e);
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean insertVehicle(Vehicle vehicle) throws VehicleException {

		boolean status = false;
		try {
			TransactionManager<Vehicle> txMan = (TransactionManager<Vehicle>) ctx.getBean("txManVehicle");
			status = txMan.insert(vehicle);
		} catch (Exception e) {
			throw new VehicleException(e);
		}
		return status;
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public List<Vehicle> availableVehicles(Date startDate, Date endDate) throws VehicleException {

		List<Vehicle> vehicleList = (List<Vehicle>) ctx.getBean("vehicleList");
		Session session = null;
		Transaction tx = null;
		try {
			session = ctx.getBean(SingletonHiberUtil.class).getSession();
			tx = session.beginTransaction();
			log.warn("Transaction started");
			Query<Vehicle> query = session.getNamedQuery("availableVehiclesProcedure").setParameter("paramDateStart",
					startDate).setParameter("paramDateEnd", endDate); 
			vehicleList = query.list();
			log.info("Called StoredProcedure: availableVehiclesProcedure");
			tx.commit();
			log.warn("Transaction committed");
		} catch (Exception e) {
			if (tx != null)
			tx.rollback();
			throw new VehicleException(e);
		}
		return vehicleList;
	}
}
