package com.garage.model;

public class SearchFilter {

	private int idVehicle;
	private String licensePlate;
	private String brand;
	private String description;
	private java.sql.Date rentEnd;

	public SearchFilter() {

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void setRentEnd(java.sql.Date rentEnd) {
		this.rentEnd = rentEnd;
	}

	public java.sql.Date getRentEnd() {
		return rentEnd;
	}
}