package com.assignment.eswar.ParkingLot.model;

public class Car {
	private String carNumber;
	private Colours colour;

	private int parkedAt;
	
	public int getParkedAt() {
		return parkedAt;
	}
	public void setParkedAt(int parkedAt) {
		this.parkedAt = parkedAt;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public Colours getColour() {
		return colour;
	}
	public void setColour(Colours colour) {
		this.colour = colour;
	}
}
