package com.garage.service;

import java.util.List;

import com.garage.exception.VehicleinfoException;
import com.garage.model.Vehicleinfo;

public interface VehicleinfoService {
 
	public List<Vehicleinfo> allTypesService() throws VehicleinfoException;
	
}
