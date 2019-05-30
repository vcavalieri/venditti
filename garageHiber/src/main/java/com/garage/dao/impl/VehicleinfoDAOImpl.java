package com.garage.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.garage.dao.VehicleinfoDAO;
import com.garage.exception.VehicleinfoException;
import com.garage.model.SingletonHiberUtil;
import com.garage.model.Vehicleinfo;

public class VehicleinfoDAOImpl implements VehicleinfoDAO {

	@Override
	public List<Vehicleinfo> allTypes() throws VehicleinfoException {

		List<Object[]> list;

		List<Vehicleinfo> infoList = new ArrayList<Vehicleinfo>();

		Session session = null;

		try {

			session = SingletonHiberUtil.getSession();

			System.out.println(session.isOpen());

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

			Root<Vehicleinfo> infoRoot = criteriaQuery.from(Vehicleinfo.class);

			criteriaQuery.multiselect(infoRoot);

			Query<Object[]> query = session.createQuery(criteriaQuery);

			list = query.list();

			for (Iterator<Object[]> itr = list.iterator(); itr.hasNext();) {

				Object o = itr.next();

				Vehicleinfo info = new Vehicleinfo();

				info = (Vehicleinfo) o;

				infoList.add(info);

			}

		} catch (Exception e) {

			e.printStackTrace();

			throw new VehicleinfoException(e);

		}

		return infoList;

	}

}
