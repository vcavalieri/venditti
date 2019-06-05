package com.garage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

	private static final Log log = LogFactory.getLog(PrenotationController.class);

	@Autowired
	private ApplicationContext ctx;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/myRentedVehicles", method = RequestMethod.GET)
	public String myVehicles(HttpServletRequest req, Model model) {

		log.info("Executing /myRentedVehicles from com.garage.controller.PrenotationController");
		String message = null;
		log.info("Retrieving User's ID from HTTPSession and parsing...");
		int userid = Integer.parseInt((String) req.getSession().getAttribute("iduser"));
		log.info("done.");
		PrenotationServiceImpl prenOp = ctx.getBean(PrenotationServiceImpl.class);
		List<Prenotation> prenList = (List<Prenotation>) ctx.getBean("prenList");
		try {
			User user = ctx.getBean(User.class);
			user.setIduser(userid);
			log.warn("Trying to retrieve myPrenotations...");
			prenList = prenOp.myPrenotationService(user);
			log.warn("myPrenotations retrieved");
		} catch (PrenotationException e) {
			message = e.getMessage();
			log.error(e);
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
			@RequestParam(value = "rentstart", required = false) String rentstart, Model model,
			HttpServletRequest req) {

		String message = null;
		log.info("Executing /deletePrenotation from com.garage.controller.PrenotationController");
		PrenotationServiceImpl prenOp = ctx.getBean(PrenotationServiceImpl.class);
		try {
			Prenotation pren = ctx.getBean(Prenotation.class);
			if ((rentstart != null && rentstart != "") && (idPrenotation != null && idPrenotation != "")) {
				pren.setIdprenotation(Integer.parseInt(idPrenotation));
				pren.setRentstart(java.sql.Date.valueOf(rentstart));
				log.warn("Trying do delete a prenotation..");
				prenOp.deletePrenotationService(pren);
				log.warn("Prenotation with ID: " + pren.getIdprenotation() + " deleted.");
			}
		} catch (PrenotationException e) {
			message = e.getMessage();
			model.addAttribute("message", message);
			log.error(e);
		}
		return "search";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/insertPrenotation", method = RequestMethod.GET)
	public String insertPrenotation(@RequestParam(value = "idvehicle", required = false) String idVehicle,
			@RequestParam(value = "rentstart", required = false) String rentStart,
			@RequestParam(value = "rentend", required = false) String rentEnd, HttpServletRequest req, Model model) {

		String message = null;
		log.info("Executing /insertPrenotation from com.garage.controller.PrenotationController");
		PrenotationServiceImpl prenOp = ctx.getBean(PrenotationServiceImpl.class);
		List<Prenotation> prenList = (List<Prenotation>) ctx.getBean("prenList");
		Vehicle vehicle = ctx.getBean(Vehicle.class);
		try {
			log.info("Retrieving User's ID from HTTPSession and parsing...");
			int idUser = Integer.parseInt((String) req.getSession().getAttribute("iduser"));
			log.info("done.");
			if (idVehicle != null && idVehicle != "") {
				vehicle.setIdvehicle(Integer.parseInt(idVehicle));
				log.warn("Trying to retrieve a specific vehicle...");
				prenList = prenOp.prenSpecificVehicleService(vehicle);
				log.warn("Specific vehicle retrivied.");
			}
			log.warn("Trying to insert a new prenotation...");
			message = prenOp.insertPrenotationService(idUser, idVehicle, rentStart, rentEnd);
			log.warn("New prenotation inserted.");
		} catch (PrenotationException e) {
			message = e.getMessage();
			log.error(e);
		} finally {
			model.addAttribute("prenotations", prenList);
			model.addAttribute("message", message);
		}
		return "insertNewPrenotation";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/available", method = RequestMethod.GET)
	public String availablePrenotations(@RequestParam(value = "rentend", required = false) String rentend, Model model,
			HttpServletRequest req) {

		String message = null;
		log.info("Executing /available from com.garage.controller.PrenotationController");
		java.sql.Date date = null;
		List<Prenotation> prenList = (List<Prenotation>) ctx.getBean("prenList");
		PrenotationServiceImpl prenOp = ctx.getBean(PrenotationServiceImpl.class);
		if (rentend != null && rentend != "") {
			try {
				date = PrenotationService.parseDataToSql(rentend);
			} catch (PrenotationException e) {
				message = e.getMessage();
				model.addAttribute("message", message);
				log.error(e);
			}
			try {
				log.warn("Trying to retrieve available prenotations...");
				prenList = prenOp.availablePrenotationService(date);
				log.warn("Available prenotation retrieved.");
			} catch (PrenotationException e) {
				message = e.getMessage();
				log.error(e);
			} finally {
				model.addAttribute("prenotations", prenList);
				req.getSession().setAttribute("rentstartdate", date);
			}
		}
		return "availablePrenotations";
	}
}
