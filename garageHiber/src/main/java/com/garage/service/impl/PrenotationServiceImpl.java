package com.garage.service.impl;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext; 

import com.garage.dao.impl.PrenotationDAOImpl;
import com.garage.exception.PrenotationException;
import com.garage.model.Prenotation;
import com.garage.model.User;
import com.garage.model.Vehicle;
import com.garage.service.PrenotationService;
 
public class PrenotationServiceImpl implements PrenotationService {
	
	@Autowired
	private ApplicationContext ctx;
  
	@Override
	public String insertPrenotationService(int fk_user, String fk_vehicle, String rentStart, String rentEnd)
			throws PrenotationException {

		boolean result = false;
		int idVehi = 0;
		String message = null;
		PrenotationDAOImpl prenotationImpl = ctx.getBean(PrenotationDAOImpl.class);
		Vehicle vehicle = ctx.getBean(Vehicle.class);
		if (rentStart != null && rentEnd != null) {
			if (fk_vehicle != null) {
				idVehi = Integer.parseInt(fk_vehicle);
				vehicle.setIdvehicle(idVehi);
				User user = ctx.getBean(User.class);
				user.setIduser(fk_user);
				result = prenotationImpl.insertPrenotationDAO(user, vehicle, java.sql.Date.valueOf(rentStart),
						java.sql.Date.valueOf(rentEnd));
				if (result) {
					message = "Prenotation Inserted Succesfully!";
				} else {
					message = "Prenotation Not Inserted!";
				}
			} else {
				message = "Prenotation Not Inserted!";
			}
		}
		return message;
	} 
 
	@Override
	public String deletePrenotationService(Prenotation pren) throws PrenotationException {

		String message = null;
		PrenotationDAOImpl prenOp = ctx.getBean(PrenotationDAOImpl.class);
		boolean result = prenOp.deletePrenotationDAO(pren);
		if (result) {
			message = "Prenotation Succesfully Deleted!";
		} else {
			message = "Prenotation Deletion ERROR!";
		}
		return message;
	}
  
	@Override
	public List<Prenotation> myPrenotationService(User user) throws PrenotationException {
		PrenotationDAOImpl prenOp = ctx.getBean(PrenotationDAOImpl.class);
		return prenOp.myVehiclePrenotationsDAO(user);
	}
}
