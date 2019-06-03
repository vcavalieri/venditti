package com.garage.dao;

import java.sql.Date;
import java.util.List;

import com.garage.exception.PrenotationException;
import com.garage.model.Prenotation;
import com.garage.model.User;
import com.garage.model.Vehicle;

public interface PrenotationDAO {

	public boolean deletePrenotation(Prenotation pren) throws PrenotationException;
	public boolean insertPrenotation(User user, Vehicle vehicle, Date rentStart, Date rentEnd) throws PrenotationException;
	public List<Prenotation> myVehiclePrenotations(User user) throws PrenotationException;
	public List<Prenotation> prenSpecificVehicle(Vehicle vehicle) throws PrenotationException;
	public List<Prenotation> availablePrenotation(java.sql.Date date) throws PrenotationException;
}
