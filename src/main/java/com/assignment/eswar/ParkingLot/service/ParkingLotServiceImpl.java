package com.assignment.eswar.ParkingLot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.assignment.eswar.ParkingLot.model.Car;
import com.assignment.eswar.ParkingLot.model.Colour;
import com.assignment.eswar.ParkingLot.model.ParkingLot;

public class ParkingLotServiceImpl implements ParkingLotService {

	ParkingLot parkingLot = null;

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	public void initParkingLot(int totalParkingSlots) {
		parkingLot = ParkingLot.getParkingLotInstance(totalParkingSlots);
	}

	public int parkCar(String carNumber, String colour){
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
			System.out.println("Invalid parking slot number");
		String carNumber = parkingLot.getSlotToCarNumberMap().remove(parkingSlotId);
		if(carNumber == null)	{
			return null;
		}
		parkingLot.getParkedCars().remove(carNumber);
		parkingLot.getFreeParkingSlots().add(parkingSlotId);
		return carNumber;
	}

	public int getParkingSlotId(String carNumber) {
		Car car = parkingLot.getParkedCars().get(carNumber);
		if(car == null)
			return 0;
		else
			return car.getParkedAt();
	}

	public List<String> getCarsFromColour(String colour) {
		List<String> carNumbers = new ArrayList<String>();
		Map<String, Car> parkedCars = parkingLot.getParkedCars();
		for(Car car : parkedCars.values())	{
			if(car.getColour().equals(colour))
				carNumbers.add(car.getCarNumber());
		}
		return carNumbers;
	}

	public List<Integer> getParkingSlotIdsForCarColour(String colour) {
		List<Integer> parkingSlotIds = new ArrayList<Integer>();
		Map<String, Car> parkedCars = parkingLot.getParkedCars();
		for(Car car : parkedCars.values())	{
			if(car.getColour().equals(colour))
				parkingSlotIds.add(car.getParkedAt());
		}
		return parkingSlotIds;
	}
	
	public void showParkingLotStatus()	{
		Map<String, Car> parkedCars = parkingLot.getParkedCars();
		System.out.println("Slot No.\tRegistration No\tColour");
		for(Car car : parkedCars.values())
			System.out.println(car.getParkedAt() + "\t" + car.getCarNumber() + "\t" + car.getColour());
		System.out.println();
	}
}
