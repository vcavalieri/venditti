package com.garage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.garage.dao.TransactionManager;
import com.garage.dao.VehicleinfoDAO;
import com.garage.exception.VehicleinfoException;
import com.garage.model.Vehicleinfo;

public class VehicleinfoDAOImpl implements VehicleinfoDAO {

	@Override
	public List<Vehicleinfo> allTypes() throws VehicleinfoException {

		List<Vehicleinfo> infoList = new ArrayList<Vehicleinfo>();
		try {
			TransactionManager<Vehicleinfo> txMan = new TransactionManager<Vehicleinfo>();
			infoList = txMan.search(new Vehicleinfo());
		} catch (Exception e) {
			e.printStackTrace();
			throw new VehicleinfoException(e);
		}
		return infoList;
	}
}
