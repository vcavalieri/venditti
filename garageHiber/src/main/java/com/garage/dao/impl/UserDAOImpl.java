package com.garage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.garage.dao.TransactionManager;
import com.garage.dao.UserDAO;
import com.garage.exception.UserException;
import com.garage.model.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean registerUser(User user) throws UserException {

		boolean status = false;
		try {
			TransactionManager<User> txMan = new TransactionManager<User>();
			status = txMan.insert(user);  
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e);
		}
		return status;
	}

	@Override
	public String[] loginUser(User user) throws UserException {

		Boolean status = false;
		String[] loginData = { null, null };
		User userQ = new User();
		List<User> userList = new ArrayList<User>();
		try {
			TransactionManager<User> txMan = new TransactionManager<User>();
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
