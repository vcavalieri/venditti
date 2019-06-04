package com.garage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.garage.dao.impl.VehicleDAOImpl;
import com.garage.exception.VehicleException;
import com.garage.model.SearchFilter;
import com.garage.model.Vehicle;
import com.garage.service.VehicleService;

public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private ApplicationContext ctx;
	
	@Override
	public String insertVehicleService(Vehicle vehicle) throws VehicleException {

		String message = null;
		VehicleDAOImpl vehicleOp = ctx.getBean(VehicleDAOImpl.class);
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
		VehicleDAOImpl vehicleOp = ctx.getBean(VehicleDAOImpl.class);
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

		VehicleDAOImpl vehicleOp = ctx.getBean(VehicleDAOImpl.class);
		return vehicleOp.searchVehicle(filter);
	}
} 
