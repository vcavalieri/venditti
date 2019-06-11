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
import com.garage.exception.VehicleException;
import com.garage.exception.VehicleinfoException;
import com.garage.model.SearchFilter;
import com.garage.model.Vehicle;
import com.garage.model.Vehicleinfo; 
import com.garage.service.impl.VehicleServiceImpl;
import com.garage.service.impl.VehicleinfoServiceImpl; 
import com.garage.utils.Utility;

public class VehicleController {

	private static final Log log = LogFactory.getLog(VehicleController.class);

	@Autowired
	private ApplicationContext ctx;

	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value = "/showVehicle", method = RequestMethod.GET)
	public String showVehicleController(@RequestParam(value = "idvehicle", required = false) String id,
			@RequestParam(value = "licenseplate", required = false) String licensePlate,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "rentend", required = false) String rentEnd, Model model, HttpServletRequest req) {

		String message = null;
		log.info("Executing /showVehicle from com.garage.controller.VehicleController");
		List<Vehicle> list = (List<Vehicle>) ctx.getBean("vehicleList");
		VehicleServiceImpl vehicleOp = ctx.getBean(VehicleServiceImpl.class);
		try {
			SearchFilter filter = ctx.getBean(SearchFilter.class);
			if (id != null && id != "") {
				filter.setIdVehicle(Integer.parseInt(id));
			} else {
				filter.setIdVehicle(0);
			}
			filter.setLicensePlate(licensePlate);
			filter.setBrand(brand);
			list = vehicleOp.searchVehicleService(filter);
			if (!list.isEmpty()) {
				model.addAttribute("vehicles", list);
			} else {
				message = "There are no Results!";
			}
		} catch (VehicleException e) {
			message = e.getMessage();
			log.error(e);
		}
		return "showVehicle";
	}

	@RequestMapping(value = "/deleteVehicle", method = RequestMethod.GET)
	public String deleteVehicleController(@RequestParam(value = "idvehicle", required = false) String id,
			@RequestParam(value = "licenseplate", required = false) String licensePlate, Model model) {

		String message = null;
		log.info("Executing /deleteVehicle from com.garage.controller.VehicleController");
		Vehicle vehicle = ctx.getBean(Vehicle.class);
		VehicleServiceImpl vehicleOp = ctx.getBean(VehicleServiceImpl.class);
		if (id != null && id != "") {
			if (licensePlate != null && licensePlate != "") {
				vehicle.setIdvehicle(Integer.parseInt(id));
				vehicle.setLicenseplate(licensePlate);
				try {
					message = vehicleOp.deleteVehicleService(vehicle);
				} catch (VehicleException e) {
					message = e.getMessage();
					log.error(e);
				} finally {
					model.addAttribute("message", message);
				}
			}
		}
		return "search";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/insertVehicle", method = RequestMethod.GET)
	public String insertVehicleController(@RequestParam(value = "licenseplate", required = false) String licensePlate,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "type", required = false) String type, Model model) {

		String message = null;
		log.info("Executing /insertVehicle from com.garage.controller.VehicleController");
		VehicleServiceImpl vehicleOp = ctx.getBean(VehicleServiceImpl.class);
		VehicleinfoServiceImpl typeOp = ctx.getBean(VehicleinfoServiceImpl.class);
		List<Vehicleinfo> typesList = (List<Vehicleinfo>) ctx.getBean("vehicleinfoList");
		try {
			typesList = typeOp.allTypesService();
			if (!typesList.isEmpty()) {
				model.addAttribute("list", typesList);
			}
			if (licensePlate != null && licensePlate != "") {
				if (brand != null && brand != "") {
					if (type != null && type != "") {
						Vehicle vehicle = ctx.getBean(Vehicle.class);
						Vehicleinfo info = ctx.getBean(Vehicleinfo.class);
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
			log.error(e);
		} catch (VehicleinfoException e) {
			message = e.getMessage();
			log.error(e);
		} finally {
			model.addAttribute("message", message);
		}
		return "insertNewVehicle";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/available", method = RequestMethod.GET)
	public String availableVehiclesController(@RequestParam(value = "rentstart", required = false) String rentStart,
			@RequestParam(value = "rentend", required = false) String rentEnd, Model model, HttpServletRequest req) {

		String message = null;
		log.info("Executing /available from com.garage.controller.VehicleController");
		java.sql.Date rentStartDate = null;
		java.sql.Date rentEndDate = null;
		List<Vehicle> vehicleList = (List<Vehicle>) ctx.getBean("vehicleList");
		VehicleServiceImpl vehicleOp = ctx.getBean(VehicleServiceImpl.class);
		if (rentEnd != null && rentEnd != "") {
			try {
				rentStartDate = ctx.getBean(Utility.class).parseDataToSql(rentStart);
				rentEndDate = ctx.getBean(Utility.class).parseDataToSql(rentEnd);
			} catch (PrenotationException e) {
				message = e.getMessage();
				model.addAttribute("message", message);
				log.error(e);
			}
			try {
				vehicleList = vehicleOp.availableVehicleService(rentStartDate, rentEndDate);
			} catch (VehicleException e) {
				message = e.getMessage();
				log.error(e);
			} finally {
				model.addAttribute("vehicles", vehicleList);
				req.getSession().setAttribute("rentstartdate", rentStartDate);
				req.getSession().setAttribute("rentenddate", rentEndDate);
			}
		}
		return "availableVehicles";
	}
}
