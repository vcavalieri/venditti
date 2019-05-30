package com.garage.service.impl;

import java.util.List;

import com.garage.dao.impl.VehicleDAOImpl;
import com.garage.exception.VehicleException;
import com.garage.model.SearchFilter;
import com.garage.model.Vehicle;
import com.garage.service.VehicleService;

public class VehicleServiceImpl implements VehicleService {

	@Override
	public String insertVehicleService(Vehicle vehicle) throws VehicleException {

		String message = null;
		VehicleDAOImpl vehicleOp = new VehicleDAOImpl();
		boolean result = vehicleOp.insertVehicle(vehicle);
		if (result) {
			message = "Vehicle Succesfully Inserted!";
		} else {
			message = "Vehicle Insertion Failed!";
		}
		return message;
	}

	@Override
	public String deleteVehicleService(Vehicle vehicle) throws VehicleException {

		String message = null;
		VehicleDAOImpl vehicleOp = new VehicleDAOImpl();
		boolean result = vehicleOp.deleteVehicle(vehicle);
		if (result) {
			message = "Vehicle Succesfully Deleted!";
		} else {
			message = "Vehicle Deletion Failed!";
		}
		return message;
	}

	@Override
	public List<Vehicle> searchVehicleService(SearchFilter filter) throws VehicleException {

		VehicleDAOImpl vehicleOp = new VehicleDAOImpl();
		return vehicleOp.searchVehicle(filter);
	}
}
