package com.garage.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.garage.dao.TransactionManager;
import com.garage.dao.VehicleinfoDAO;
import com.garage.exception.VehicleinfoException;
import com.garage.model.Vehicleinfo;

public class VehicleinfoDAOImpl implements VehicleinfoDAO {

	@Autowired
	private ApplicationContext ctx;

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicleinfo> allTypes() throws VehicleinfoException {

		List<Vehicleinfo> infoList = (List<Vehicleinfo>) ctx.getBean("vehicleinfoList");
		try {
			TransactionManager<Vehicleinfo> txMan = (TransactionManager<Vehicleinfo>) ctx.getBean("txManVehicleinfo");
			infoList = txMan.search(new Vehicleinfo());
		} catch (Exception e) {
			throw new VehicleinfoException(e);
		}
		return infoList;
	}
}
