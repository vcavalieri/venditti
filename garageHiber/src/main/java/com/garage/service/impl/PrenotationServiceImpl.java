package com.garage.service.impl;

import java.sql.Date;
import java.util.ArrayList;
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

	@SuppressWarnings("unchecked")
	@Override
	public String insertPrenotationService(int fk_user, String fk_vehicle, String rentStart, String rentEnd)
			throws PrenotationException {

		boolean result = false;
		int idVehi = 0;
		String message = null;
		java.sql.Date sqlStart = null;
		java.sql.Date sqlEnd = null;
		PrenotationDAOImpl prenotationImpl = ctx.getBean(PrenotationDAOImpl.class);
		Vehicle vehicle = ctx.getBean(Vehicle.class);
		if (rentStart != null && rentEnd != null) {
			List<Prenotation> prenList = (List<Prenotation>) ctx.getBean("prenList");
			List<Boolean> boolList = new ArrayList<Boolean>();
			sqlStart = PrenotationService.parseDataToSql(rentStart);
			sqlEnd = PrenotationService.parseDataToSql(rentEnd);
			if (fk_vehicle != null) {
				vehicle.setIdvehicle(idVehi);
				prenList = prenSpecificVehicleService(vehicle);
				idVehi = Integer.parseInt(fk_vehicle);
				for (Prenotation prenotation : prenList) {
					if (prenotation.getVehicle().getIdvehicle() == Integer.parseInt(fk_vehicle)) {
						if (prenotation.getRentend().before(sqlStart)) {
							if (sqlEnd.after(prenotation.getRentstart())) {
								boolList.add(true);
							} else {
								boolList.add(false);
							}
						} else {
							boolList.add(false);
						}
					}
				}
				int count = 0;
				for (Boolean bool : boolList) {
					if (bool.equals(true)) {
						count++;
					}
				}
				User user = ctx.getBean(User.class);
				user.setIduser(fk_user);
				if (count == boolList.size()) {
					result = prenotationImpl.insertPrenotation(user, vehicle, sqlStart, sqlEnd);
					if (result) {
						message = "Prenotation Inserted Succesfully!";
					} else {
						message = "Prenotation Not Inserted!";
					}
				} else {
					message = "Prenotation Not Inserted!";
				}
			}
		}
		return message;
	}

	@Override
	public String deletePrenotationService(Prenotation pren) throws PrenotationException {

		String message = null;
		PrenotationDAOImpl prenOp = ctx.getBean(PrenotationDAOImpl.class);
		boolean result = prenOp.deletePrenotation(pren);
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
		return prenOp.myVehiclePrenotations(user);
	}

	@Override
	public List<Prenotation> prenSpecificVehicleService(Vehicle vehicle) throws PrenotationException {
		PrenotationDAOImpl prenOp = ctx.getBean(PrenotationDAOImpl.class);
		return prenOp.prenSpecificVehicle(vehicle);
	}

	@Override
	public List<Prenotation> availablePrenotationService(Date date) throws PrenotationException {
		PrenotationDAOImpl prenOp = ctx.getBean(PrenotationDAOImpl.class);
		return prenOp.availablePrenotation(date);
	}
}
