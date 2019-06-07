package com.garage.dao;

import java.sql.Date;
import java.util.List;

import com.garage.exception.PrenotationException;
import com.garage.model.Prenotation;
import com.garage.model.User;
import com.garage.model.Vehicle;

public interface PrenotationDAO {

	public boolean deletePrenotationDAO(Prenotation pren) throws PrenotationException;

	public boolean insertPrenotationDAO(User user, Vehicle vehicle, Date rentStart, Date rentEnd)
			throws PrenotationException;

	public List<Prenotation> myVehiclePrenotationsDAO(User user) throws PrenotationException;
}
