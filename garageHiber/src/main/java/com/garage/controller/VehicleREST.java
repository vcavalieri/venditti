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
import com.garage.model.Vehicleinfo; 
import com.garage.utils.JsonSerializers;
import com.garage.model.Vehicle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SuppressWarnings("unchecked")
@RestController
public class VehicleREST {

	@Autowired
	private ApplicationContext ctx;

	@RequestMapping(value = "/insertVehicle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> insertVehicleRest(

			@RequestParam(name = "licenseplate", required = true) String licensePlate,
			@RequestParam(name = "brand", required = true) String brand,
			@RequestParam(name = "idtype", required = true) String idType) {

		TransactionManager<Vehicle> txVehicle = (TransactionManager<Vehicle>) ctx.getBean("txManVehicle");
		Vehicle vehicle = ctx.getBean(Vehicle.class);
		Vehicleinfo info = ctx.getBean(Vehicleinfo.class);
		Gson gson = new Gson();
		vehicle.setLicenseplate(licensePlate);
		vehicle.setBrand(brand);
		info.setVehicletype(Integer.parseInt(idType));
		vehicle.setVehicleinfo(info);
		try {
			txVehicle.insert(vehicle);
			vehicle = txVehicle.initializeAndUnproxy(vehicle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(gson.toJson(vehicle), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/deleteVehicle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> deleteVehicleRest(
			@RequestParam(name = "id", required = true) String id) {

		TransactionManager<Vehicle> txVehicle = (TransactionManager<Vehicle>) ctx.getBean("txManVehicle");
		Vehicle vehicle = ctx.getBean(Vehicle.class);
		Vehicle copy = ctx.getBean(Vehicle.class);
		Gson gson = new Gson();
		try {
			vehicle = txVehicle.searchByID(Vehicle.class, Integer.parseInt(id));
			copy.setIdvehicle(vehicle.getIdvehicle());
			copy.setLicenseplate(vehicle.getLicenseplate());
			copy.setBrand(vehicle.getBrand());
			copy.setVehicleinfo(vehicle.getVehicleinfo());
			vehicle = txVehicle.initializeAndUnproxy(vehicle);
			txVehicle.delete(vehicle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(gson.toJson(copy), HttpStatus.CREATED);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/allVehicles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> allVehiclesRest(){
		
		TransactionManager<Vehicle> txVehicle = (TransactionManager<Vehicle>) ctx.getBean("txManVehicle");
		List<Vehicle> vehicleList = (List<Vehicle>) ctx.getBean("vehicleList");
		List<Object> gsonList = new ArrayList<Object>();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.registerTypeAdapter(Vehicle.class, new JsonSerializers(){}).create();
		try {
			vehicleList = txVehicle.search(ctx.getBean(Vehicle.class));
			for (Vehicle vehicle : vehicleList) {
				vehicle = txVehicle.initializeAndUnproxy(vehicle);
				gsonList.add(vehicle);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(gson.toJson(gsonList), HttpStatus.CREATED);
	}

	public @ResponseBody ResponseEntity<Object> updateVehicleRest(

			@RequestParam(name = "idvehicle", required = true) String id,
			@RequestParam(name = "licenseplate", required = false) String licenseplate,
			@RequestParam(name = "brand", required = false) String brand) {

		TransactionManager<Vehicle> txVehicle = (TransactionManager<Vehicle>) ctx.getBean("txManVehicle");
		Vehicle old = ctx.getBean(Vehicle.class);
//		Vehicle vehicle = null;
//		Gson gson = new Gson();
		try {
			old = txVehicle.searchByID(Vehicle.class, Integer.parseInt(id));
			old = txVehicle.initializeAndUnproxy(old);
			if (licenseplate != null) {
				old.setLicenseplate(licenseplate);
			}
			if (brand != null) {
				old.setBrand(brand);
			}
			txVehicle.update(old);
//			vehicle = new Vehicle(vehicleinfo, licenseplate, brand)
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
}