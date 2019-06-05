package com.garage.model;

public class SearchFilter {

	private int idVehicle;
	private String licensePlate;
	private String brand;

	public SearchFilter() {

	}

	public SearchFilter(int idVehicle, String licensePlate, String brand) {
		this.idVehicle = idVehicle;
		this.licensePlate = licensePlate;
		this.brand = brand;
	}

	public int getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(int idVehicle) {
		this.idVehicle = idVehicle;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}