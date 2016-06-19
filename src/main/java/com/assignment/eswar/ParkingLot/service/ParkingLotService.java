package com.assignment.eswar.ParkingLot.service;

import java.util.List;

public interface ParkingLotService {
	public void initParkingLot(int totalParkingSlots);
	public int parkCar(String carNumber, String colour);
	public String leave(int parkingSlotId);
	public int getParkingSlotId(String carNumber);
	public List<String> getCarsFromColour(String colour);
	public List<Integer> getParkingSlotIdsForCarColour(String colour);
	public void showParkingLotStatus();
}
