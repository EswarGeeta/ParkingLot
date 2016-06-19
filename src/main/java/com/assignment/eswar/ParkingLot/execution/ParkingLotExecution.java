package com.assignment.eswar.ParkingLot.execution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ParkingLotExecution 
{
	private static Scanner sc = new Scanner(System.in);
	private static ParkingLotHandler handler = new ParkingLotHandler();
	public static void main( String[] args )
    {
		if(args.length == 0){

			String input = sc.nextLine();
			if(!handler.createParkingLot(input))
				return;
			while (true) {
				input = sc.nextLine();
				handleInput(input);
			}
		}
		else if(args.length == 1){
			List<String> inputs = null;
			try {
				inputs = Files.readAllLines(Paths.get(args[0]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!handler.createParkingLot(inputs.get(0)))
				return;
			for(int i = 1; i<inputs.size() ; i++)	{
				handleInput(inputs.get(i));				
			}
		}
    }

	private static void handleInput(String input) {
		if(input.contains("park"))
			handler.handleParking(input);
		else if(input.contains("leave"))
			handler.handleLeaving(input);
		else if(input.contains("status"))
			handler.showStatus();
		else if(input.contains("registration_numbers_for_cars_with_colour"))
			handler.showCarsWithColour(input);
		else if(input.contains("slot_numbers_for_cars_with_colour"))
			handler.showSlotsWithColour(input);
		else if(input.contains("slot_number_for_registration_number"))
			handler.showSlotForCarNumber(input);
	}
}