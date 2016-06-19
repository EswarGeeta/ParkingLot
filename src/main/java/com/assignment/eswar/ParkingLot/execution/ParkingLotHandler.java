package com.assignment.eswar.ParkingLot.execution;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.assignment.eswar.ParkingLot.service.ParkingLotService;
import com.assignment.eswar.ParkingLot.service.ParkingLotServiceImpl;

public class ParkingLotHandler 
{
	private static ParkingLotService service = new ParkingLotServiceImpl();
	private static String pattern; 
	private static Pattern regex;
	private static Matcher m;

	public void showSlotForCarNumber(String input) {
		pattern = "slot_number_for_registration_number\\s+(\\S+)";
		regex = Pattern.compile(pattern);
		m = regex.matcher(input);
		if (m.find()) {
			int parkingSlotId = service.getParkingSlotId(m.group(1));
			if(parkingSlotId == 0)
				System.out.println("Not found\n");
			else
				System.out.println(parkingSlotId + "\n");
		}		
	}

	public void showSlotsWithColour(String input) {
		pattern = "slot_numbers_for_cars_with_colour\\s+(\\w+)";
		regex = Pattern.compile(pattern);
		m = regex.matcher(input);
		if (m.find())	{		
			List<Integer> slotIds = service.getParkingSlotIdsForCarColour(m.group(1));
			System.out.print(slotIds.get(0));
			for(int i = 1 ; i< slotIds.size() ; i++)
				System.out.print(", " + slotIds.get(i));
			System.out.println("\n");
		}
	}

	public void showCarsWithColour(String input) {
		pattern = "registration_numbers_for_cars_with_colour\\s(\\S+)";
		regex = Pattern.compile(pattern);
		m = regex.matcher(input);
		if (m.find())	{
			List<String> carNumbers = service.getCarsFromColour(m.group(1));
			System.out.print(carNumbers.get(0));
			for(int i = 1 ; i< carNumbers.size() ; i++)
				System.out.print(", " + carNumbers.get(i));
			System.out.println("\n");
		}
	}

	public void showStatus() {
		service.showParkingLotStatus();	
	}

	public void handleParking(String input) {
		pattern = "park (\\S+) (\\S+)";
		regex = Pattern.compile(pattern);
		m = regex.matcher(input);
		if (m.find()) {
			int parkingSlotId = service.parkCar(m.group(1), m.group(2));
			if(parkingSlotId == 0)
				System.out.println("Sorry, parking lot is full\n");
			else
				System.out.println("Allocated slot number: " + parkingSlotId + "\n");
		}
		else
			System.out.println("Invalid input\n");
	}
	
	public void handleLeaving(String input) {
		pattern = "leave\\s+(\\d+)";
		regex = Pattern.compile(pattern);
		m = regex.matcher(input);
		if (m.find()) {
			int parkingSlotId = Integer.parseInt(m.group(1));
			if(service.leave(parkingSlotId)!= null)
				System.out.println("Slot number " + parkingSlotId + " is free"  + "\n");
			else
				System.out.println("Parking Slot is already free \n");
		}
	}

	public boolean createParkingLot(String input) {
		pattern = "create_parking_lot (\\d+)";
		regex = Pattern.compile(pattern);
		m = regex.matcher(input);
		if (m.find()) {
			int parkingLotSize = Integer.parseInt(m.group(1));
			service.initParkingLot(parkingLotSize);
			System.out.println("Created a parking lot with " + parkingLotSize + " slots" + "\n");
			return true;
		} else {
			System.out.println("Enter the parking lot size. Example: create_parking_lot 10\n");
			return false;
		}
	}
}