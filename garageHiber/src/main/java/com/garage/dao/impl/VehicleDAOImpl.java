package com.garage.dao.impl;

import java.util.ArrayList; 
import java.util.List; 

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example; 

import com.garage.dao.TransactionManager;
import com.garage.dao.VehicleDAO;
import com.garage.exception.VehicleException;
import com.garage.model.SearchFilter;
import com.garage.model.SingletonHiberUtil;
import com.garage.model.Vehicle;

public class VehicleDAOImpl implements VehicleDAO {

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Vehicle> searchVehicle(SearchFilter filter) throws VehicleException {

		List<Vehicle> toReturn = new ArrayList<Vehicle>();
		Session session = null;
		Transaction tx = null;
		try {
			session = SingletonHiberUtil.getSession();
			tx = session.beginTransaction();
			Vehicle example = new Vehicle();
			example.setLicenseplate(filter.getLicensePlate());
			Example myExample = Example.create(example);
			Criteria criteria = session.createCriteria(example.getClass()).add(myExample);
			toReturn = criteria.list(); 
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw e;
		}
		return toReturn;	
	}
	
	@Override
	public boolean deleteVehicle(Vehicle vehicle) throws VehicleException {

		boolean status = false;
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		try {
			TransactionManager<Vehicle> txMan = new TransactionManager<Vehicle>();
			vehicleList = txMan.search(vehicle);
			for (Vehicle vehicles : vehicleList) {
				vehicle = null;
				vehicle = vehicles;
			}
			status = txMan.delete(vehicle);
		} catch (Exception e) {
			e.printStackTrace();
			throw new VehicleException(e);
		}
		return status;
	}    

	@Override
	public boolean insertVehicle(Vehicle vehicle) throws VehicleException {

		boolean status = false;
		try {
			TransactionManager<Vehicle> txMan = new TransactionManager<Vehicle>();
			status = txMan.insert(vehicle);
		} catch (Exception e) {
			e.printStackTrace();
			throw new VehicleException(e);
		}
		return status;
	}
}
