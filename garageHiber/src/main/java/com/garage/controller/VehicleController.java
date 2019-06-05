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

import com.garage.exception.VehicleException;
import com.garage.exception.VehicleinfoException;
import com.garage.model.SearchFilter;
import com.garage.model.Vehicle;
import com.garage.model.Vehicleinfo;
import com.garage.service.impl.VehicleServiceImpl;
import com.garage.service.impl.VehicleinfoServiceImpl;

@Controller
public class VehicleController {

	private static final Log log = LogFactory.getLog(VehicleController.class);

	@Autowired
	private ApplicationContext ctx;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/showVehicle", method = RequestMethod.GET)
	public String showVehicle(@RequestParam(value = "idvehicle", required = false) String id,
			@RequestParam(value = "licenseplate", required = false) String licensePlate,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "rentend", required = false) String rentEnd, Model model, HttpServletRequest req) {

		@SuppressWarnings("unused")
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
			log.warn("Trying to search vehicles...");
			list = vehicleOp.searchVehicleService(filter);
			if (!list.isEmpty()) {
				log.warn("Vehicles found.");
				model.addAttribute("vehicles", list);
			} else {
				log.warn("Vehicles not found.");
				message = "There are no Results!";
			}
		} catch (VehicleException e) {
			message = e.getMessage();
			log.error(e);
		} finally {
			if (rentEnd != null && rentEnd != "") {
				req.getSession().setAttribute("rentcheck", true);
				req.getSession().setAttribute("rentstart", rentEnd);
			}
		}
		return "showVehicle";
	}

	@RequestMapping(value = "/deleteVehicle", method = RequestMethod.GET)
	public String deleteVehicle(@RequestParam(value = "idvehicle", required = false) String id,
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
					log.warn("Trying to delete a vehicle...");
					message = vehicleOp.deleteVehicleService(vehicle);
					log.warn("Vehicle ID: " + vehicle.getIdvehicle() + " deleted.");
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
	public String insertVehicle(@RequestParam(value = "licenseplate", required = false) String licensePlate,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "type", required = false) String type, Model model) {

		String message = null;
		log.info("Executing /insertVehicle from com.garage.controller.VehicleController");
		VehicleServiceImpl vehicleOp = ctx.getBean(VehicleServiceImpl.class);
		VehicleinfoServiceImpl typeOp = ctx.getBean(VehicleinfoServiceImpl.class);
		List<Vehicleinfo> typesList = (List<Vehicleinfo>) ctx.getBean("vehicleinfoList");
		try {
			log.warn("Trying to retrieve all Vehicleinfo's records...");
			typesList = typeOp.allInfoService();
			if (!typesList.isEmpty()) {
				log.warn("Records retrieved.");
				model.addAttribute("list", typesList);
			} else {
				log.info("There are no records.");
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
						log.warn("Trying to insert a new vehicle...");
						message = vehicleOp.insertVehicleService(vehicle);
						log.warn("Vehicle inserted.");
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
}
