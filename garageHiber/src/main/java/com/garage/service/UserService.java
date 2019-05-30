package com.garage.service;

import com.garage.exception.UserException;
import com.garage.model.User;

public interface UserService {

	public String[] loginService(User user) throws UserException;
	
	public String registerService(User user) throws UserException;
	
}
