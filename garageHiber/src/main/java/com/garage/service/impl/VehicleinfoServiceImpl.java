package com.garage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext; 

import com.garage.dao.impl.VehicleinfoDAOImpl;
import com.garage.exception.VehicleinfoException;
import com.garage.model.Vehicleinfo;

public class VehicleinfoServiceImpl implements com.garage.service.VehicleinfoService {

	@Autowired
	private ApplicationContext ctx;
  
	@Override
	public List<Vehicleinfo> allTypesService() throws VehicleinfoException {

		VehicleinfoDAOImpl infoOp = ctx.getBean(VehicleinfoDAOImpl.class);
		return infoOp.allTypesDAO();
	}
}
