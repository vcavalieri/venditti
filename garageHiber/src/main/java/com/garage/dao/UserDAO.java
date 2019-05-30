package com.garage.dao;

import com.garage.exception.UserException;
import com.garage.model.User;

public interface UserDAO {

	public boolean registerUser(User user) throws UserException;
	
	public String[] loginUser(User user) throws UserException;
	
}
