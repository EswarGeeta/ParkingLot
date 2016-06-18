package com.assignment.eswar.ParkingLot.model;

public class Car {
	private String carNumber;
	private Colour colour;

	public Car(String carNumber, Colour colour) {
		super();
		this.carNumber = carNumber;
		this.colour = colour;
	}

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
	public Colour getColour() {
		return colour;
	}
	public void setColour(Colour colour) {
		this.colour = colour;
	}
}
