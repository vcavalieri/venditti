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
import com.garage.exception.VehicleException;
import com.garage.exception.VehicleinfoException;
import com.garage.model.SearchFilter;
import com.garage.model.Vehicle;
import com.garage.model.Vehicleinfo;
import com.garage.service.impl.PrenotationServiceImpl;
import com.garage.service.impl.VehicleServiceImpl;
import com.garage.service.impl.VehicleinfoServiceImpl;

@Controller
public class VehicleController {

	@RequestMapping(value = "/showVehicle", method = RequestMethod.GET)
	public String showVehicle(@RequestParam(value = "idvehicle", required = false) String id,
			@RequestParam(value = "licenseplate", required = false) String licensePlate,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "rentend", required = false) String rentEnd, Model model, HttpServletRequest req) {

		@SuppressWarnings("unused")
		String message = null;
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		VehicleServiceImpl vehicleOp = new VehicleServiceImpl();
		PrenotationServiceImpl prenOp = new PrenotationServiceImpl();
		try {
			SearchFilter filter = new SearchFilter();
			if (id != null && id != "") {
				filter.setIdVehicle(Integer.parseInt(id));
			} else {
				filter.setIdVehicle(0);
			}
			filter.setLicensePlate(licensePlate);
			filter.setBrand(brand);
			filter.setDescription(description);
			if (rentEnd != null && rentEnd != "") {
				filter.setRentEnd(prenOp.parseDataToSql(rentEnd));
			}
			vehicleList = vehicleOp.searchVehicleService(filter);
			if (!vehicleList.isEmpty()) {
				model.addAttribute("vehicles", vehicleList);
			} else {
				message = "There are no Results!";
			}
		} catch (VehicleException e) {
			message = e.getMessage();
			e.printStackTrace();
		} catch (PrenotationException e) {
			message = e.getMessage();
			e.printStackTrace();
		} finally {
			if (rentEnd != null && rentEnd != "") {
				req.getSession().setAttribute("rentcheck", true);
				req.getSession().setAttribute("rentstart", rentEnd);
			}
		}
		return "showVehicles";
	}

	@RequestMapping(value = "/deleteVehicle", method = RequestMethod.GET)
	public String deleteVehicle(@RequestParam(value = "idvehicle", required = false) String id,
			@RequestParam(value = "licenseplate", required = false) String licensePlate, Model model) {

		String message = null;
		Vehicle vehicle = new Vehicle();
		VehicleServiceImpl vehicleOp = new VehicleServiceImpl();
		if (licensePlate != null) {
			vehicle.setLicenseplate(licensePlate);
			try {
				message = vehicleOp.deleteVehicleService(vehicle);
			} catch (VehicleException e) {
				message = e.getMessage();
				e.printStackTrace();
			} finally {
				model.addAttribute("message", message);
			}
		}
		return "search"; 
	}

	@RequestMapping(value = "/insertVehicle", method = RequestMethod.GET)
	public String insertVehicle(@RequestParam(value = "licenseplate", required = false) String licensePlate,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "type", required = false) String type, Model model) {

		String message = null;
		VehicleServiceImpl vehicleOp = new VehicleServiceImpl();
		VehicleinfoServiceImpl typeOp = new VehicleinfoServiceImpl();
		List<Vehicleinfo> typesList = new ArrayList<Vehicleinfo>();
		try {
			typesList = typeOp.allInfoService();
			if (!typesList.isEmpty()) {
				model.addAttribute("list", typesList);
			}
			if (licensePlate != null && licensePlate != "") {
				if (brand != null && brand != "") {
					if (type != null && type != "") {
						Vehicle vehicle = new Vehicle();
						Vehicleinfo info = new Vehicleinfo();
						vehicle.setLicenseplate(licensePlate);
						vehicle.setBrand(brand);
						String[] splitted = type.split("\\+");
						info.setVehicletype(Integer.parseInt(splitted[1]));
						info.setDescription(splitted[0]);
						vehicle.setVehicleinfo(info);
						message = vehicleOp.insertVehicleService(vehicle);
					}
				}
			}
		} catch (VehicleException e) {
			message = e.getMessage();
			e.printStackTrace();
		} catch (VehicleinfoException e) {
			message = e.getMessage();
			e.printStackTrace();
		} finally {
			model.addAttribute("message", message);
		}
		return "insertNewVehicle";
	}
}
