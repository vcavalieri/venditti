package com.garage.dao.impl;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.garage.dao.TransactionManager;
import com.garage.dao.UserDAO;
import com.garage.exception.UserException;
import com.garage.model.User;

public class UserDAOImpl implements UserDAO { 

	
	@Autowired
	private ApplicationContext ctx;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean registerUser(User user) throws UserException {

		boolean status = false;
		try {
			TransactionManager<User> txMan = (TransactionManager<User>) ctx.getBean("txManUser");
			status = txMan.insert(user);  
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String[] loginUser(User user) throws UserException {

		Boolean status = false;
		String[] loginData = { null, null };
		User userQ = ctx.getBean(User.class);
		List<User> userList = (List<User>) ctx.getBean("userList");
		try {
			TransactionManager<User> txMan = (TransactionManager<User>) ctx.getBean("txManUser");
			userList = txMan.search(user);
			for (User users : userList) {
				userQ = users;
			}
			if (userQ.getPassword().equals(user.getPassword()) && userQ.getUsername().equals(user.getUsername())) {
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
