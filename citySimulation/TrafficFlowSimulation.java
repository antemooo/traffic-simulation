package citySimulation;

import trafficSimulation.City;
import trafficSimulation.CrossRoad;
import trafficSimulation.Vehicle;
import trafficSimulation.VehicleRegister;

public class TrafficFlowSimulation {
	private City mCity;
	private VehicleRegister mRegister;

	public void setupCity(String fileName) {
		SimulationReader sr = new SimulationReader(this);
		sr.readCity(fileName);
	}

	public void addVehicles(String fileName) {
		SimulationReader sr = new SimulationReader(this);
		sr.readVehicles(fileName);
	}

	public void addVehicle(String start, String Side, Vehicle v) {
		this.mCity.addVehicle(start, Side, v);
		this.mRegister.addVehcileToRegister(v.getName(), v);
		System.out.println(v + " Start at " + start + ".");
	}

	public void initiateCity(String cityName, int horizontalSize, int verticalSize) {
		this.mCity = new City(cityName, horizontalSize, verticalSize);
		this.mRegister = new VehicleRegister();
		System.out.println(
				"Initiate city named " + cityName + " of size " + horizontalSize + " by " + verticalSize + ".");
	}

	public void addRoadWork(String from, String to) {
		this.mCity.addRoadWork(from, to);
		System.out.println("Roadwork added from " + from + " to " + to + ".");
	}

	public void setUpCrossroad(int column, int row, CrossRoad crossName) {
		mCity.initializeCrossRoad(row, column, crossName);
		System.out.println("Set up crossroad at position " + column + ", " + row + " named " + crossName + ".");
	}

	// Start the simulation
	public void start(int loops) {
		System.out.println("Simulation Starts");
		for (int i = 0; i < loops; i++) {
			if (loops == 0) {
				/*
				 * run complete simulation, this functionality is not working
				 * now because i don't have the real path so that the Vehicles
				 * will stack in one of the streets that leads to nowhere.
				 */

				// while (!mCity.isEmpty()) {
				// System.out.println("-------Loop" + i + "------");
				// mCity.iterate();
				// }

			} else {
				// run "loops" number of loops
				mCity.iterate();
				System.out.print("-------Loop" + i + "------");
				// System.out.println(mCity);
			}
		}
		System.out.println();
		System.out.println("Simulation Ends");
	}

	// Returns total travel time for all cars
	public float totalTravelTime() {
		return this.mRegister.totalTravelTime();
	}

	// Returns average travel time for all cars
	public float averageTravelTime() {
		return this.mRegister.averageTravelTime();
	}

	// Print the route for a given vehicle
	public int travelTimeForVehicle(String vehicleName) {
		return this.mRegister.travelTimeForVehicle(vehicleName);
	}

	// Print the route for a given vehicle
	public void printRouteForVehicle(String vehicleName) {
		System.out.println(this.mRegister.routeForVehicle(vehicleName));
	}

	// to print the City with all its crossRoads
	public void printSimulation() {
		System.out.println(mCity);
	}

	/* to make the Edges between the crossRoads ( to connect the CrossRoads) */
	public void connectCity() {
		this.mCity.connect();
	}

}
