package com.garage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.garage.dao.TransactionManager;
import com.garage.model.User;

@SuppressWarnings("unchecked")
@RestController
public class UserREST {

	@Autowired
	private ApplicationContext ctx;

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User registerRest(

			@PathVariable(name = "firstname", required = true) String firstname,
			@PathVariable(name = "lastname", required = true) String lastname,
			@PathVariable(name = "username", required = true) String username,
			@PathVariable(name = "password", required = true) String password) {

		TransactionManager<User> txUser = (TransactionManager<User>) ctx.getBean("txManUser");

		User user = ctx.getBean(User.class);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setUsername(username);
		user.setPassword(password);
		try {
			txUser.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@RequestMapping(value = "/allUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<User> allUsersRest() {

		TransactionManager<User> txUser = (TransactionManager<User>) ctx.getBean("txManUser");
		List<User> userList = (List<User>) ctx.getBean("userList");
		try {
			userList = txUser.search(ctx.getBean(User.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<User> deleteUserRest(@RequestParam(name = "id", required = false) String id) {
		
		TransactionManager<User> txUser = (TransactionManager<User>) ctx.getBean("txManUser");
		User user = ctx.getBean(User.class);
		user.setIduser(Integer.parseInt(id));
		List<User> userList = (List<User>) ctx.getBean("userList");
		try {
			txUser.delete(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User updateUserRest(

			@PathVariable(name = "firstname", required = false) String firstname,
			@PathVariable(name = "lastname", required = false) String lastname,
			@PathVariable(name = "username", required = false) String username,
			@PathVariable(name = "password", required = false) String password) {

		TransactionManager<User> txUser = (TransactionManager<User>) ctx.getBean("txManUser");
		User user = ctx.getBean(User.class);
		if (firstname != null) {
			user.setFirstname(firstname);
		}
		if (lastname != null) {
			user.setLastname(lastname);
		}
		if (username != null) {
			user.setUsername(username);
		}
		if (password != null) {
			user.setPassword(password);
		}
		try {
			txUser.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
