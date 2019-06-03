package com.garage.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.garage.dao.PrenotationDAO;
import com.garage.dao.TransactionManager;
import com.garage.exception.PrenotationException;
import com.garage.model.Prenotation;
import com.garage.model.SingletonHiberUtil;
import com.garage.model.User;
import com.garage.model.Vehicle;

public class PrenotationDAOImpl implements PrenotationDAO {

	@Override
	public boolean deletePrenotation(Prenotation pren) throws PrenotationException {

		boolean status = false;
		try {
			TransactionManager<Prenotation> txMan = new TransactionManager<Prenotation>();
			List<Prenotation> prenList = txMan.search(pren);
			for (Prenotation prens : prenList) {
				pren = null;
				pren = prens;
			}
			status = txMan.delete(pren);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PrenotationException(e);
		}
		return status;
	}
  
	@Override
	public boolean insertPrenotation(User user, Vehicle vehicle, Date rentStart, Date rentEnd)
			throws PrenotationException {

		boolean status = false;
		Prenotation pren = new Prenotation();
		pren.setUser(user);
		pren.setVehicle(vehicle);
		pren.setRentstart(rentStart);
		pren.setRentend(rentEnd);
		try {
			TransactionManager<Prenotation> txMan = new TransactionManager<Prenotation>();
			status = txMan.insert(pren);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PrenotationException(e);
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prenotation> myVehiclePrenotations(User user) throws PrenotationException {

		List<Prenotation> prenList = new ArrayList<Prenotation>();
		Session session = null;
		Transaction tx = null;
		try {
			session = SingletonHiberUtil.getSession();
			tx = session.beginTransaction();
			Query<Prenotation> query = session.getNamedQuery("myVehiclePrenotationProcedure").setParameter("idutente",
					user.getIduser());
			prenList = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new PrenotationException(e);
		}
		return prenList;
	}

	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public List<Prenotation> prenSpecificVehicle(Vehicle vehicle) throws PrenotationException {
		List<Object[]> list;
		List<Prenotation> prenList = new ArrayList<Prenotation>();
		Session session = null;
		Transaction tx = null;
		try {
			session = SingletonHiberUtil.getSession();
			tx = session.beginTransaction();
			Query<Prenotation> query = session.getNamedQuery("prenSpecificVehicleProcedure").setParameter("specificID",
					vehicle.getIdvehicle());
			prenList = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new PrenotationException(e);
		}
		return prenList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prenotation> availablePrenotation(Date date) throws PrenotationException {

		List<Prenotation> prenList = new ArrayList<Prenotation>();
		Session session = null;
		Transaction tx = null;
		try {
			session = SingletonHiberUtil.getSession();
			tx = session.beginTransaction();
			Query<Prenotation> query = session.getNamedQuery("availablePrenotationProcedure").setParameter("paramDate",
					date);
			prenList = query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new PrenotationException(e);
		}
		return prenList;
	}
}
