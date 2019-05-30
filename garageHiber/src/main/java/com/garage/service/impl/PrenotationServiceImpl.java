package com.garage.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.garage.dao.impl.PrenotationDAOImpl;
import com.garage.exception.PrenotationException;
import com.garage.model.Prenotation;
import com.garage.model.User;
import com.garage.model.Vehicle;
import com.garage.service.PrenotationService;

public class PrenotationServiceImpl implements PrenotationService {

	@Override
	public String insertPrenotationService(int fk_user, String fk_vehicle, String rentStart, String rentEnd)
			throws PrenotationException {

		boolean result = false;

		int idVehi = 0;

		String message = null;

		java.sql.Date sqlStart = null;

		java.sql.Date sqlEnd = null;

		PrenotationDAOImpl prenotationImpl = new PrenotationDAOImpl();

		Vehicle vehicle = new Vehicle();

		if (rentStart != null && rentEnd != null) {

			List<Prenotation> prenList = new ArrayList<Prenotation>();

			List<Boolean> boolList = new ArrayList<Boolean>();

			sqlStart = parseDataToSql(rentStart);

			sqlEnd = parseDataToSql(rentEnd);

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

				User user = new User();
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

		PrenotationDAOImpl prenOp = new PrenotationDAOImpl();

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

		PrenotationDAOImpl prenOp = new PrenotationDAOImpl();

		return prenOp.myVehiclePrenotations(user);
	}

	@Override
	public List<Prenotation> prenSpecificVehicleService(Vehicle vehicle) throws PrenotationException {

		PrenotationDAOImpl prenOp = new PrenotationDAOImpl();

		return prenOp.prenSpecificVehicle(vehicle);
	}

	@Override
	public java.sql.Date parseDataToSql(String toParse) throws PrenotationException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String[] dateArr = { null, null, null };

		java.sql.Date parsedToSql = null;

		dateArr = toParse.split("/");

		if (dateArr != null) {

			toParse = dateArr[2] + "-" + dateArr[0] + "-" + dateArr[1];

			java.util.Date parsedToUtil = null;

			try {

				parsedToUtil = format.parse(toParse);

			} catch (ParseException e) {

				throw new PrenotationException(e);

			}

			parsedToSql = new java.sql.Date(parsedToUtil.getTime());

		}

		return parsedToSql;

	}

}
