package com.garage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirector(HttpServletRequest req) {
		return "index";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		return "error";
	}
}
