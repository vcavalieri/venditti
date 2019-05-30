package com.garage.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

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
import com.garage.model.Vehicleinfo;

public class PrenotationDAOImpl implements PrenotationDAO {

	@Override
	public boolean deletePrenotation(Prenotation pren) throws PrenotationException {

		boolean status = false;
		try {
			TransactionManager<Prenotation> txMan = new TransactionManager<Prenotation>();
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

	@SuppressWarnings("unused")
	@Override
	public List<Prenotation> myVehiclePrenotations(User user) throws PrenotationException {
	//TODO IMPLEMENTA MYVEHICLEPREN CON TXMAN
		List<Object[]> list;
		List<Prenotation> prenList = new ArrayList<Prenotation>();
		Session session = null;
		Transaction tx = null;
		try {
			session = SingletonHiberUtil.getSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
			Root<Vehicle> vehiRoot = criteriaQuery.from(Vehicle.class);
			Root<Vehicleinfo> infoRoot = criteriaQuery.from(Vehicleinfo.class);
			Root<User> userRoot = criteriaQuery.from(User.class);
			Root<Prenotation> prenRoot = criteriaQuery.from(Prenotation.class);
			Join<Vehicle, Vehicleinfo> joinVehicleInfo = vehiRoot.join("vehicleinfo", JoinType.INNER);
			Join<Prenotation, User> joinPrenUser = prenRoot.join("user", JoinType.INNER);
			Join<Prenotation, Vehicle> joinPrenVehicle = prenRoot.join("vehicle", JoinType.INNER);
			criteriaQuery.multiselect(prenRoot, vehiRoot, infoRoot, userRoot)
					.where(builder.equal(vehiRoot.get("vehicleinfo"), infoRoot.get("vehicletype")),
							builder.equal(userRoot.get("iduser"), prenRoot.get("user")))
					.groupBy(prenRoot.get("idprenotation"));
			Query<Object[]> query = session.createQuery(criteriaQuery);
			list = query.list();
			for (Iterator<Object[]> itr = list.iterator(); itr.hasNext();) {
				Object[] o = itr.next();
				Prenotation pren = new Prenotation();
				pren = (Prenotation) o[0];
				prenList.add(pren);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new PrenotationException(e);
		}
		return prenList;
	}

	@SuppressWarnings("unused")
	@Override
	public List<Prenotation> prenSpecificVehicle(Vehicle vehicle) throws PrenotationException {
	//TODO IMPLEMENTA PRENSPECIFICVEHICLE CON TXMAN
		List<Object[]> list;
		List<Prenotation> prenList = new ArrayList<Prenotation>();
		Session session = null;
		Transaction tx = null;
		try {
			session = SingletonHiberUtil.getSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
			Root<Vehicle> vehiRoot = criteriaQuery.from(Vehicle.class);
			Root<Vehicleinfo> infoRoot = criteriaQuery.from(Vehicleinfo.class);
			Root<Prenotation> prenRoot = criteriaQuery.from(Prenotation.class);
			Join<Vehicle, Vehicleinfo> joinVehicleInfo = vehiRoot.join("vehicleinfo", JoinType.INNER);
			Join<Prenotation, Vehicle> joinPrenVehicle = prenRoot.join("vehicle", JoinType.INNER);
			criteriaQuery.multiselect(prenRoot, vehiRoot, infoRoot)
					.having(builder.equal(vehiRoot.get("vehicleinfo"), infoRoot.get("vehicletype")),
							builder.equal(vehiRoot.get("idvehicle"), vehicle.getIdvehicle()),
							builder.equal(prenRoot.get("rentend"), builder.max(prenRoot.get("rentend"))))
					.groupBy(prenRoot.get("idprenotation"));
			Query<Object[]> query = session.createQuery(criteriaQuery);
			list = query.list();
			for (Iterator<Object[]> itr = list.iterator(); itr.hasNext();) {
				Object[] o = itr.next();
				Prenotation pren = new Prenotation();
				pren = (Prenotation) o[0];
				prenList.add(pren);
			}
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
