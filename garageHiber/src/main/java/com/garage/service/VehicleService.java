package com.garage.service;

import java.util.List;

import com.garage.exception.VehicleException;
import com.garage.model.SearchFilter;
import com.garage.model.Vehicle;

public interface VehicleService {

	public String insertVehicleService(Vehicle vehicle) throws VehicleException;
	public String deleteVehicleService(Vehicle vehicle) throws VehicleException;
	public List<Vehicle> searchVehicleService(SearchFilter filter) throws VehicleException;
	
}
