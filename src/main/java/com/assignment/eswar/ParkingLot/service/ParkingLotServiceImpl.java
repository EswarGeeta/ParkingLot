package com.assignment.eswar.ParkingLot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.assignment.eswar.ParkingLot.model.Car;
import com.assignment.eswar.ParkingLot.model.Colour;
import com.assignment.eswar.ParkingLot.model.ParkingLot;

public class ParkingLotServiceImpl implements ParkingLotService {

	ParkingLot parkingLot = null;

	public void initParkingLot(int totalParkingSlots) {
		parkingLot = ParkingLot.getParkingLotInstance(totalParkingSlots);
	}

	public int parkCar(String carNumber, Colour colour){
		Car car = new Car(carNumber, colour);
		if(!parkingLot.isFull())	{
			int parkingSlotId = parkingLot.getFreeParkingSlots().pollFirst();
			parkingLot.getSlotToCarNumberMap().put(parkingSlotId, carNumber);
			parkingLot.getParkedCars().put(carNumber, car);
			car.setParkedAt(parkingSlotId);
			return parkingSlotId;
		}
		// Returning 0 means that parking slot is not available.
		return 0;
	}

	public String leave(int parkingSlotId){
		if(parkingSlotId < 1 || parkingLot.getTotalParkingSlots() < parkingSlotId)
			throw new IllegalArgumentException("Parking Slot id exceeded max number of parking slots available");
		String carNumber = parkingLot.getSlotToCarNumberMap().remove(parkingSlotId);
		if(carNumber == null)	{
			System.out.println("Parking Slot is already free");
			return null;
		}		
		parkingLot.getParkedCars().remove(carNumber);
		return carNumber;
	}

	public int getParkingSlotId(String carNumber) {
		Car car = parkingLot.getParkedCars().get(carNumber);
		if(car == null)	{
			System.out.println("Car is not parked in the parking lot");
			return 0;
		}
		else
			return car.getParkedAt();
	}

	public List<String> getCarsFromColour(String colour) {
		List<String> carNumbers = new ArrayList<String>();
		Map<String, Car> parkedCars = parkingLot.getParkedCars();
		for(Car car : parkedCars.values())	{
			if(car.getColour() == Colour.valueOf(colour))
				carNumbers.add(car.getCarNumber());
		}
		return carNumbers;
	}

	public List<Integer> getParkingSlotIdsForCarColour(String colour) {
		List<Integer> parkingSlotIds = new ArrayList<Integer>();
		Map<String, Car> parkedCars = parkingLot.getParkedCars();
		for(Car car : parkedCars.values())	{
			if(car.getColour() == Colour.valueOf(colour))
				parkingSlotIds.add(car.getParkedAt());
		}
		return parkingSlotIds;
	}
}
