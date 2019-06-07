package com.garage.dao;

import java.sql.Date;
import java.util.List;
 
import com.garage.exception.VehicleException; 
import com.garage.model.SearchFilter;
import com.garage.model.Vehicle;

public interface VehicleDAO {

	public boolean deleteVehicleDAO(Vehicle vehicle) throws VehicleException;

	public boolean insertVehicleDAO(Vehicle vehicle) throws VehicleException;

	public List<Vehicle> searchVehicleDAO(SearchFilter filter) throws VehicleException;
	
	public List<Vehicle> availableVehiclesDAO(Date startDate, Date endDate) throws VehicleException;

}
