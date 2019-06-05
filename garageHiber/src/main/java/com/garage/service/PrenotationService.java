package com.garage.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.garage.exception.PrenotationException;
import com.garage.model.Prenotation;
import com.garage.model.User;
import com.garage.model.Vehicle;

public interface PrenotationService {

	public String insertPrenotationService(int fk_user, String fk_vehicle, String rentStart, String rentEnd)
			throws PrenotationException;

	public String deletePrenotationService(Prenotation pren) throws PrenotationException;

	public List<Prenotation> myPrenotationService(User user) throws PrenotationException;

	public List<Prenotation> prenSpecificVehicleService(Vehicle vehicle) throws PrenotationException;

	public List<Prenotation> availablePrenotationService(java.sql.Date date) throws PrenotationException;

	public static java.sql.Date parseDataToSql(String toParse) throws PrenotationException {
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
