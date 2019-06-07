package com.garage.dao;

import java.util.List;

import com.garage.exception.VehicleinfoException;
import com.garage.model.Vehicleinfo;

public interface VehicleinfoDAO {

	public List<Vehicleinfo> allTypesDAO() throws VehicleinfoException;

}
