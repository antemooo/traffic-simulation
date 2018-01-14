package citySimulation;

public class Main {

	public static void main(String[] args) {

		TrafficFlowSimulation simulation = new TrafficFlowSimulation();
		simulation.setupCity("files/city2.txt");

		// to connect the CrossRoads
		simulation.connectCity();

		simulation.addVehicles("files/vehicles2.txt");

		// to print the whole City
		simulation.printSimulation();

		// simulation Start
		simulation.start(1000);

		System.out.println(simulation.totalTravelTime());
		System.out.println(simulation.averageTravelTime());

		// tp print all the routes and all the vehicles
		 for(int i= 1 ; i < 157;i++){
		 System.out.println("Travel Time of V"+i+" is:"+simulation.travelTimeForVehicle("V"+i));
		 simulation.printRouteForVehicle("V"+i);
		 }

	}

}
