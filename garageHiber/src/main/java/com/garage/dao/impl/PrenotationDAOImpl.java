package com.garage.dao.impl;

import java.sql.Date;
import java.util.List;
 
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.garage.dao.PrenotationDAO;
import com.garage.dao.TransactionManager;
import com.garage.exception.PrenotationException;
import com.garage.model.Prenotation;
import com.garage.model.User;
import com.garage.model.Vehicle;
import com.garage.utils.SingletonHiberUtil;

public class PrenotationDAOImpl implements PrenotationDAO {

	@Autowired
	private ApplicationContext ctx;

	@SuppressWarnings("unchecked")
	@Override
	public boolean deletePrenotationDAO(Prenotation pren) throws PrenotationException {

		boolean status = false;
		try {
			TransactionManager<Prenotation> txMan = (TransactionManager<Prenotation>) ctx.getBean("txManPren");
			List<Prenotation> prenList = txMan.search(pren);
			for (Prenotation prens : prenList) {
				pren = null;
				pren = prens;
			} 
			status = txMan.delete(pren);
		} catch (Exception e) {
			throw new PrenotationException(e);
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean insertPrenotationDAO(User user, Vehicle vehicle, Date rentStart, Date rentEnd)
			throws PrenotationException {

		boolean status = false;
		Prenotation pren = ctx.getBean(Prenotation.class);
		pren.setUser(user);
		pren.setVehicle(vehicle);
		pren.setRentstart(rentStart);
		pren.setRentend(rentEnd);
		try {
			TransactionManager<Prenotation> txMan = (TransactionManager<Prenotation>) ctx.getBean("txManPren"); 
			status = txMan.insert(pren);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PrenotationException(e);
		}
		return status;
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public List<Prenotation> myVehiclePrenotationsDAO(User user) throws PrenotationException {

		List<Prenotation> prenList = (List<Prenotation>) ctx.getBean("prenList");
		Session session = null;
		Transaction tx = null;
		try {
			session = ctx.getBean(SingletonHiberUtil.class).getSession();
			tx = session.beginTransaction(); 
			Query<Prenotation> query = session.getNamedQuery("myVehiclePrenotationProcedure").setParameter("idutente",
					user.getIduser());
			prenList = query.list(); 
			tx.commit(); 
		} catch (Exception e) {
			if (tx != null)
			tx.rollback();
			throw new PrenotationException(e);
		}
		return prenList;
	}
}
