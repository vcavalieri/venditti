package com.garage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.garage.exception.PrenotationException;
import com.garage.model.Prenotation;
import com.garage.model.User;
import com.garage.model.Vehicle;
import com.garage.service.impl.PrenotationServiceImpl;

@Controller
public class PrenotationController {

	@RequestMapping(value = "/myRentedVehicles", method = RequestMethod.GET)
	public String myVehicles(HttpServletRequest req, Model model) {

		String message = null;

		int userid = Integer.parseInt((String) req.getSession().getAttribute("iduser"));

		PrenotationServiceImpl prenOp = new PrenotationServiceImpl();

		List<Prenotation> prenList = new ArrayList<Prenotation>();
	
		
		try {
			
			User user = new User();
			user.setIduser(userid);
			
			prenList = prenOp.myPrenotationService(user);

		} catch (PrenotationException e) {

			message = e.getMessage();
			e.printStackTrace();

		} finally {

			if (!prenList.isEmpty()) {

				model.addAttribute("vehicles", prenList);

			} else {

				message = "You have no Rented Vehicles!";

			}

			model.addAttribute("message", message);

		}

		return "showVehicles";

	}

	@RequestMapping(value = "/deletePrenotation", method = RequestMethod.GET)
	public String deletePrenotation(@RequestParam(value = "idprenotation", required = false) String idPrenotation,
			Model model, HttpServletRequest req) {

		String message = null;

		PrenotationServiceImpl prenOp = new PrenotationServiceImpl();

		try {
			
			Prenotation pren = new Prenotation();
			pren.setIdprenotation(Integer.parseInt(idPrenotation));

			prenOp.deletePrenotationService(pren);

		} catch (PrenotationException e) {

			message = e.getMessage();
			e.printStackTrace();

			model.addAttribute("message", message);

		}

		return "search";

	}

	@RequestMapping(value = "/insertPrenotation", method = RequestMethod.GET)
	public String insertPrenotation(@RequestParam(value = "idvehicle", required = false) String idVehicle,
			@RequestParam(value = "rentstart", required = false) String rentStart,
			@RequestParam(value = "rentend", required = false) String rentEnd, HttpServletRequest req, Model model) {

		String message = null;

		PrenotationServiceImpl prenOp = new PrenotationServiceImpl();
		
		List<Prenotation> prenList = new ArrayList<Prenotation>();
		
		Vehicle vehicle = new Vehicle();
		
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

			model.addAttribute("vehicles",prenList);
			
			model.addAttribute("message", message);

		}

		return "insertNewPrenotation";

	}

}
