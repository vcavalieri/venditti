package com.garage.service;

import com.garage.exception.UserException;
import com.garage.model.User;

public interface UserService {
 
	public String[] loginUserService(User user) throws UserException;
 
	public String registerUserService(User user) throws UserException;

}
