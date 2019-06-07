package com.garage.dao;

import com.garage.exception.UserException;
import com.garage.model.User;

public interface UserDAO {

	public boolean registerUserDAO(User user) throws UserException;

	public String[] loginUserDAO(User user) throws UserException;

}
