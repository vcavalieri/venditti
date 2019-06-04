package com.garage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.garage.dao.impl.UserDAOImpl;
import com.garage.exception.UserException;
import com.garage.model.User;
import com.garage.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private ApplicationContext ctx;
	
	@Override
	public String[] loginService(User user) throws UserException {

		String[] loginData;
		UserDAOImpl userOp = ctx.getBean(UserDAOImpl.class);
		loginData = userOp.loginUser(user);
		if (loginData[0] == "true") {
			loginData[0] = "Login Succesfully Done!";
		} else {
			loginData[0] = "Login ERROR!";
		}
		return loginData;
	}

	@Override
	public String registerService(User user) throws UserException {

		String message = null;
		UserDAOImpl userOp = ctx.getBean(UserDAOImpl.class);
		boolean result = userOp.registerUser(user);
		if (result) {
			message = "User Succesfully Registered!";
		} else {
			message = "Registration ERROR!";
		}
		return message;
	}
}
