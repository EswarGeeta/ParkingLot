package com.assignment.eswar.ParkingLot.model;

import java.util.HashMap;
import java.util.TreeSet;

public class ParkingLot {
	private final int totalParkingSlots;
	private TreeSet<Integer> freeParkingSlots;
	private HashMap<Integer, String> slotToCarNumberMap;
	private HashMap<String, Car> parkedCars;

	protected ParkingLot(int totalParkingSlots) {
		super();
		this.totalParkingSlots = totalParkingSlots;
		freeParkingSlots = new TreeSet<Integer>();
		for(int i = 1 ; i <= totalParkingSlots; i++)
			freeParkingSlots.add(i);
		slotToCarNumberMap = new HashMap<Integer, String>();
		parkedCars = new HashMap<String, Car>();
	}

	private static ParkingLot instance = null;

	public static ParkingLot getParkingLotInstance(int totalParkingSlots) {
			if (instance == null) {
				synchronized (ParkingLot.class) {
					if (instance == null) {
						instance = new ParkingLot(totalParkingSlots);
					}
				}
			}
			return instance;
		}

	public boolean isFull()	{
		return (freeParkingSlots.size() == 0);
	}
	
	public int getTotalParkingSlots() {
		return totalParkingSlots;
	}

	public TreeSet<Integer> getFreeParkingSlots() {
		return freeParkingSlots;
	}
	public void setFreeParkingSlots(TreeSet<Integer> freeParkingSlots) {
		this.freeParkingSlots = freeParkingSlots;
	}
	public HashMap<Integer, String> getSlotToCarNumberMap() {
		return slotToCarNumberMap;
	}
	public void setSlotToCarNumberMap(HashMap<Integer, String> slotToCarNumberMap) {
		this.slotToCarNumberMap = slotToCarNumberMap;
	}
	public HashMap<String, Car> getParkedCars() {
		return parkedCars;
	}
	public void setParkedCars(HashMap<String, Car> parkedCars) {
		this.parkedCars = parkedCars;
	}
}