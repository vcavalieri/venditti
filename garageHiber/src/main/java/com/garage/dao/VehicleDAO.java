package com.garage.dao;

import java.util.List;

import com.garage.exception.VehicleException;
import com.garage.model.SearchFilter;
import com.garage.model.Vehicle;

public interface VehicleDAO {

	public boolean deleteVehicle(Vehicle vehicle) throws VehicleException;
	
	public boolean insertVehicle(Vehicle vehicle) throws VehicleException;
	
	public List<Vehicle> searchVehicle(SearchFilter filter) throws VehicleException;
	
}
