package com.assignment.eswar.ParkingLot.model;

public class ParkingSlot {
	private int slotId;
	private String carNumber;
	
	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public boolean isOccupied()	{
		return (carNumber != null);
	}
	
	//service methods
	public void parkCar(String carNumber)	{
		setCarNumber(carNumber);
	}
	
	public String leaveCar()	{
		String temp = carNumber;
		carNumber = null;
		return temp;
	}
}
