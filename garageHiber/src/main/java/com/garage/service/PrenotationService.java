package com.garage.service;

import java.util.List;

import com.garage.exception.PrenotationException;
import com.garage.model.Prenotation;
import com.garage.model.User;
import com.garage.model.Vehicle;

public interface PrenotationService {

	public String insertPrenotationService(int fk_user, String fk_vehicle, String rentStart, String rentEnd) throws PrenotationException;
	
	public String deletePrenotationService(Prenotation pren) throws PrenotationException;

	public List<Prenotation> myPrenotationService(User user) throws PrenotationException;
	
	public List<Prenotation> prenSpecificVehicleService(Vehicle vehicle) throws PrenotationException;
	
	public java.sql.Date parseDataToSql(String toParse) throws PrenotationException; 
	
}
