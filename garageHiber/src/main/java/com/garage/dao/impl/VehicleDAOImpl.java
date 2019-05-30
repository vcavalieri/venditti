package com.garage.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.garage.dao.TransactionManager;
import com.garage.dao.VehicleDAO;
import com.garage.exception.VehicleException;
import com.garage.model.Prenotation;
import com.garage.model.SearchFilter;
import com.garage.model.SingletonHiberUtil;
import com.garage.model.Vehicle;
import com.garage.model.Vehicleinfo;

public class VehicleDAOImpl implements VehicleDAO {

	@SuppressWarnings("unused")  
	@Override
	public List<Vehicle> searchVehicle(SearchFilter filter) throws VehicleException {
	//TODO IMPLEMENTA SEARCHFILTER TRAMITE TXMAN
		List<Object[]> list;
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		Session session = null;
		Transaction tx = null;
		try {
			session = SingletonHiberUtil.getSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
			Root<Vehicle> vehiRoot = criteriaQuery.from(Vehicle.class);
			Root<Vehicleinfo> infoRoot = criteriaQuery.from(Vehicleinfo.class);
			Join<Vehicle, Vehicleinfo> joinVehicleInfo = vehiRoot.join("vehicleinfo", JoinType.INNER);
			List<Predicate> predicates = new ArrayList<Predicate>();
			if (filter.getRentEnd() != null) {
				Root<Prenotation> prenRoot = criteriaQuery.from(Prenotation.class);
				criteriaQuery.multiselect(vehiRoot, infoRoot);
				Join<Vehicle, Prenotation> joinVP = prenRoot.join("vehicle", JoinType.LEFT);
				criteriaQuery
						.where(builder.equal(vehiRoot.get("idvehicle"), prenRoot.get("vehicle")),
								builder.or(
										vehiRoot.get("idvehicle")
												.in(criteriaQuery.multiselect(prenRoot.get("vehicle"))
														.from(Prenotation.class))
												.not(),
										builder.and(builder.lessThan((prenRoot.get("rentend")), filter.getRentEnd()),
												builder.lessThan(prenRoot.get("rentstart"), filter.getRentEnd()))));
			}
			if (filter.getIdVehicle() != 0) {
				Predicate pAnd = builder.conjunction();
				pAnd = builder.and(builder.equal(vehiRoot.get("idvehicle"), filter.getIdVehicle()));
				predicates.add(pAnd);
			}
			if (filter.getLicensePlate() != null && filter.getLicensePlate() != "") {
				Predicate pAnd = builder.conjunction();
				pAnd = builder.equal(vehiRoot.get("licenseplate"), filter.getLicensePlate());
				predicates.add(pAnd);
			}
			if (filter.getBrand() != null && filter.getBrand() != "") {
				Predicate pAnd = builder.conjunction();
				pAnd = builder.and(builder.equal(vehiRoot.get("brand"), filter.getBrand()));
				predicates.add(pAnd);
			}
			if (filter.getDescription() != null && filter.getDescription() != "") {
				Predicate pAnd = builder.conjunction();
				pAnd = builder.and(builder.equal(infoRoot.get("description"), filter.getDescription()));
				predicates.add(pAnd);
			}
			Predicate finalPredicate = builder.and();
			if (predicates != null) {
				for (Predicate predicate : predicates) {
					finalPredicate = builder.and(finalPredicate, predicate);
				}
			}
			if (filter.getRentEnd() == null) {
				criteriaQuery.multiselect(vehiRoot, infoRoot);
				criteriaQuery.where(finalPredicate).groupBy(vehiRoot.get("idvehicle"));
			}
			criteriaQuery.groupBy(vehiRoot.get("idvehicle"));
			Query<Object[]> query = session.createQuery(criteriaQuery);
			list = query.list();
			for (Iterator<Object[]> itr = list.iterator(); itr.hasNext();) {
				Object[] o = itr.next();
				Vehicle vehicle = new Vehicle();
				vehicle = (Vehicle) o[0];
				vehicleList.add(vehicle);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			throw new VehicleException(e);
		}
		return vehicleList;
	}

	@Override
	public boolean deleteVehicle(Vehicle vehicle) throws VehicleException {

		boolean status = false;
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		try {
			TransactionManager<Vehicle> txMan = new TransactionManager<Vehicle>();
			vehicleList = txMan.search(vehicle);
			for (Vehicle vehicles : vehicleList) {
				vehicle = null; 
				vehicle = vehicles;
			}
			status = txMan.delete(vehicle);
		} catch (Exception e) {
			e.printStackTrace();
			throw new VehicleException(e);
		} 
		return status;
	}

	@Override
	public boolean insertVehicle(Vehicle vehicle) throws VehicleException {

		boolean status = false;
		try {
			TransactionManager<Vehicle> txMan = new TransactionManager<Vehicle>();
			status = txMan.insert(vehicle);
		} catch (Exception e) {
			e.printStackTrace();
			throw new VehicleException(e);
		}
		return status;
	}
}
