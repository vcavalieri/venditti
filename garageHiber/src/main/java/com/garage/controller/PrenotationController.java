package com.garage.controller;

import java.util.List; 

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.garage.exception.PrenotationException;
import com.garage.model.Prenotation;
import com.garage.model.User;
import com.garage.model.Vehicle;
import com.garage.service.PrenotationService;
import com.garage.service.impl.PrenotationServiceImpl;

@Controller
public class PrenotationController {
	
	@Autowired
	private ApplicationContext ctx;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/myRentedVehicles", method = RequestMethod.GET)
	public String myVehicles(HttpServletRequest req, Model model) {

		String message = null;
		int userid = Integer.parseInt((String) req.getSession().getAttribute("iduser"));
		PrenotationServiceImpl prenOp = ctx.getBean(PrenotationServiceImpl.class);
		List<Prenotation> prenList = (List<Prenotation>) ctx.getBean("prenList");
		try {
			User user = ctx.getBean(User.class);
			user.setIduser(userid);
			prenList = prenOp.myPrenotationService(user);
		} catch (PrenotationException e) {
			message = e.getMessage();
			e.printStackTrace();
		} finally {
			if (!prenList.isEmpty()) {
				model.addAttribute("prenotations", prenList);
			} else {
				message = "You have no Rented Vehicles!";
			}
			model.addAttribute("message", message);
		}
		return "myPrenotations";
	}
 
	@RequestMapping(value = "/deletePrenotation", method = RequestMethod.GET)
	public String deletePrenotation(@RequestParam(value = "idprenotation", required = false) String idPrenotation,
			@RequestParam(value = "rentstart", required = false) String rentstart,
			Model model, HttpServletRequest req) {

		String message = null;
		PrenotationServiceImpl prenOp = ctx.getBean(PrenotationServiceImpl.class);
		try {
			Prenotation pren = ctx.getBean(Prenotation.class);
			if((rentstart != null && rentstart != "")  && (idPrenotation != null && idPrenotation != "")) {
				pren.setIdprenotation(Integer.parseInt(idPrenotation));
				pren.setRentstart(java.sql.Date.valueOf(rentstart));
				prenOp.deletePrenotationService(pren);				
			}    
		} catch (PrenotationException e) {
			message = e.getMessage();
			e.printStackTrace();
			model.addAttribute("message", message); 
		}
		return "search";
	}
    
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/insertPrenotation", method = RequestMethod.GET)
	public String insertPrenotation(@RequestParam(value = "idvehicle", required = false) String idVehicle,
			@RequestParam(value = "rentstart", required = false) String rentStart,
			@RequestParam(value = "rentend", required = false) String rentEnd, HttpServletRequest req, Model model) {

		String message = null;
		PrenotationServiceImpl prenOp = ctx.getBean(PrenotationServiceImpl.class);
		List<Prenotation> prenList = (List<Prenotation>) ctx.getBean("prenList");
		Vehicle vehicle = ctx.getBean(Vehicle.class);
		try {
			int idUser = Integer.parseInt((String) req.getSession().getAttribute("iduser"));
			if(idVehicle != null && idVehicle != "") {
				vehicle.setIdvehicle(Integer.parseInt(idVehicle));
				prenList = prenOp.prenSpecificVehicleService(vehicle);
			}
			message = prenOp.insertPrenotationService(idUser, idVehicle, rentStart, rentEnd);
		} catch (PrenotationException e) {
			message = e.getMessage();
			e.printStackTrace();
		} finally {
			model.addAttribute("prenotations",prenList);
			model.addAttribute("message", message);
		}
		return "insertNewPrenotation";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/available", method = RequestMethod.GET)
	public String availablePrenotations(@RequestParam(value = "rentend", required = false) String rentend,
			Model model, HttpServletRequest req) {
		
		String message = null;
		java.sql.Date date = null;
		List<Prenotation> prenList = (List<Prenotation>) ctx.getBean("prenList");
		PrenotationServiceImpl prenOp = ctx.getBean(PrenotationServiceImpl.class);
		if(rentend != null && rentend != "") {
			try {
				date = PrenotationService.parseDataToSql(rentend);
			} catch (PrenotationException e) {
				e.printStackTrace();
				message = e.getMessage();
				model.addAttribute("message",message);
			}
			try {
				prenList = prenOp.availablePrenotationService(date);
			}catch(PrenotationException e) {
				e.printStackTrace();
				message = e.getMessage();
			}finally {
				model.addAttribute("prenotations",prenList);
				req.getSession().setAttribute("rentstartdate", date);
			}
		}
		return "availablePrenotations";  
	}
}
