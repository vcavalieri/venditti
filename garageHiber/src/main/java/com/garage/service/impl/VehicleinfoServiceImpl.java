package com.garage.service.impl;

import java.util.List;

import com.garage.dao.impl.VehicleinfoDAOImpl;
import com.garage.exception.VehicleinfoException;
import com.garage.model.Vehicleinfo;

public class VehicleinfoServiceImpl implements com.garage.service.VehicleinfoService {

	@Override
	public List<Vehicleinfo> allInfoService() throws VehicleinfoException {
		
		VehicleinfoDAOImpl infoOp = new VehicleinfoDAOImpl();
		return infoOp.allTypes();	
	}
}
