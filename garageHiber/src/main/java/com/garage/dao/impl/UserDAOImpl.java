package com.garage.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.garage.dao.UserDAO;
import com.garage.exception.UserException;
import com.garage.model.SingletonHiberUtil;
import com.garage.model.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean registerUser(User user) throws UserException {

		boolean status = false;

		Session session = null;
		
		Transaction tx = null;

		try {

			session = SingletonHiberUtil.getSession();
		
			System.out.println(session.isOpen());
			
			tx = session.beginTransaction();
			
			tx.begin();
			
			session.save(user);

			status = true;

			tx.commit();
			
		} catch (Exception e) {
			
			if(tx!=null) {
				
				tx.rollback();
				
			}
			
			e.printStackTrace();

			throw new UserException(e);

		}

		return status;

	}

	@Override
	public String[] loginUser(User user) throws UserException {

		Boolean status = false;
		
//		List<Object[]> list;
		
		String[] loginData = { null, null };

		User userQ = new User();

		Session session = null;

		try {

			session = SingletonHiberUtil.getSession();
			
//			CriteriaBuilder builder = session.getCriteriaBuilder();
//			CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
//			Root<User> rootUser = criteriaQuery.from(User.class);
//			
//			criteriaQuery.multiselect(rootUser).where(builder.equal(rootUser.get("username"), user.getUsername()));
//
//			Query<Object[]> query = session.createQuery(criteriaQuery);
//			
//			list = query.getResultList();
//			
//			for (Object obj : list) {
//				
//				userQ = (User) obj;
//				
//			}
			
			userQ = session.load(User.class, 1);

			if (userQ.getIduser() == 1) {

				status = true;
  
			}

			loginData[0] = status.toString();
			loginData[1] = Integer.toString(userQ.getIduser());

		} catch (Exception e) {
			
			e.printStackTrace();
   
			throw new UserException(e);

		}
 
		return loginData;

	}

}
