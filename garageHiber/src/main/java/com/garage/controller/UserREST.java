package com.garage.controller;

import java.util.ArrayList;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.garage.dao.TransactionManager;
import com.garage.model.User;
import com.google.gson.Gson;  

@SuppressWarnings("unchecked")
@RestController
public class UserREST {

	@Autowired
	private ApplicationContext ctx;

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> registerRest(

			@RequestParam(name = "firstname", required = true) String firstname,
			@RequestParam(name = "lastname", required = true) String lastname,
			@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password) {

		TransactionManager<User> txUser = (TransactionManager<User>) ctx.getBean("txManUser");
		Gson gson = new Gson();
		User user = ctx.getBean(User.class);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setUsername(username);
		user.setPassword(password);
		try {
			txUser.insert(user);
			user = txUser.initializeAndUnproxy(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(gson.toJson(user), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/allUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> allUsersRest() {

		TransactionManager<User> txUser = (TransactionManager<User>) ctx.getBean("txManUser");
		List<User> userList = (List<User>) ctx.getBean("userList");
		List<Object> gsonList = new ArrayList<Object>();
		Gson gson = new Gson();
		try {
			userList = txUser.search(ctx.getBean(User.class));
			for (User user : userList) {
				gsonList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(gson.toJson(gsonList), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> deleteUserRest(@RequestParam(name = "id", required = false) String id) {

		TransactionManager<User> txUser = (TransactionManager<User>) ctx.getBean("txManUser");
		Gson gson = new Gson();
		User user = ctx.getBean(User.class);
		User copy = ctx.getBean(User.class);
		try {
			user = txUser.searchByID(User.class, Integer.parseInt(id));
			user = txUser.initializeAndUnproxy(user);
			copy.setIduser(user.getIduser());
			copy.setFirstname(user.getFirstname());
			copy.setLastname(user.getLastname());
			copy.setUsername(user.getUsername());
			copy.setPassword(user.getPassword());
			txUser.delete(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(gson.toJson(copy), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> updateUserRest(

			@RequestParam(name = "iduser", required = true) String id,
			@RequestParam(name = "firstname", required = false) String firstname,
			@RequestParam(name = "lastname", required = false) String lastname,
			@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password) {

		TransactionManager<User> txUser = (TransactionManager<User>) ctx.getBean("txManUser");
		User old = ctx.getBean(User.class);
		User user = null;
		Gson gson = new Gson();
		try {
			old = txUser.searchByID(User.class, Integer.parseInt(id));
			old = txUser.initializeAndUnproxy(old);
			if (firstname != null) {
				old.setFirstname(firstname);
			}
			if (lastname != null) {
				old.setLastname(lastname);
			}
			if (username != null) {
				old.setUsername(username);
			}
			if (password != null) {
				old.setPassword(password);
			}
			txUser.update(old);
			user = new User(firstname, lastname, username, password);
			user.setIduser(old.getIduser());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(gson.toJson(user), HttpStatus.CREATED);
	}
}
