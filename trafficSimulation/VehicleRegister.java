package trafficSimulation;

import dataStructure.Dictionary;

/* Ahmed Kareem A Abdullah
 * This class describe the VehicleRegister properties and their methods 
 */
public class VehicleRegister {
	// data structure to store all the vehicles of the city
	// Feed Back change it to dictionary
	private Dictionary<String, Vehicle> mVehicles;

	/*
	 * the constructor will create a Dictionary type String for the Key and Vehicles as value
	 *  the reason behind using Dictionary is that we do need to store 
	 *  each Vehicle in the city and its name to be called later by its name 
	 *  for some calculations after the simulation
	 */
	public VehicleRegister() {
		mVehicles = new Dictionary<String, Vehicle>();
	}

	// method to add a Vehicle to the register
	public void addVehcileToRegister(String name, Vehicle v) {
		mVehicles.add(name, v);

	}

	// method to get a vehicle from the register
	public Vehicle getVehcileFromRegister(String name) {
		return mVehicles.find(name);
	}

	// toString of the class
	public String toString() {
		return mVehicles.toString();
	}

	// to count all the traveling time of all the vehicles in the simulation
	public float totalTravelTime() {
		int sum = 0;
		if (this.mVehicles.size() > 0)
			for (int i = 0; i < mVehicles.size(); i++) {
				Vehicle temp = mVehicles.getIndex(i);
				sum += temp.getTravelTime();
			}
		return sum;
	}

	// to calculate the average traveling time of the vehicles
	public float averageTravelTime() {
		float total = this.totalTravelTime();
		if(total!=0)
			return total / mVehicles.size();
		else
			return 0;
	}

	// to get a specific Vehicle traveling time
	public int travelTimeForVehicle(String vehicleName) {
		Vehicle temp = this.getVehcileFromRegister(vehicleName);
		return temp.getTravelTime();
	}

	// to print the route of the Vehicle from the start to destination
	public String routeForVehicle(String vehicleName) {
		Vehicle temp = this.getVehcileFromRegister(vehicleName);
		return temp.printRoute();
	}

}
