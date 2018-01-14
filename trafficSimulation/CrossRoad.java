package trafficSimulation;

/* i imported random just to generate a random integer number*/
import java.util.Random;

import trafficSimulation.TrafficLight.State;

/* Ahmed Kareem A Abdullah
 * This class describe the CrossRoad properties and their methods 
 */
//The letter M before the variables is to declare that it is a member variable (instead of using this.)
public class CrossRoad implements Comparable<CrossRoad> {
	// the name of the crossRoad that will be recorded by the traveling
	// vehicles.
	private String mName;
	// Four trafficLights
	private TrafficLight mTrafficNorth;
	private TrafficLight mTrafficWest;
	private TrafficLight mTrafficEast;
	private TrafficLight mTrafficSouth;
	// Four Streets
	private Street mStreetNorth;
	private Street mStreetWest;
	private Street mStreetEast;
	private Street mStreetSouth;

	/*
	 * the constructor of the class takes 9 parameters ( the name of the
	 * crossRoad, 4 trafficLights , 4 Streets) in case you pass direct traffic
	 * and streets
	 */

	public CrossRoad(String name, TrafficLight tNorth, TrafficLight tWest, TrafficLight tEast, TrafficLight tSouth,
			Street sNorth, Street sWest, Street sEast, Street sSouth) {
		// initializing the TrafficLights
		mTrafficNorth = tNorth;
		mTrafficWest = tWest;
		mTrafficEast = tEast;
		mTrafficSouth = tSouth;
		// initializing the streets
		mStreetNorth = sNorth;
		mStreetWest = sWest;
		mStreetEast = sEast;
		mStreetSouth = sSouth;

		mName = name; // setting the name of the crossRoad
	}

	/*
	 * another constructor to use it with the parser(takes all the sub
	 * parameters of the sub traffic and streets)
	 */
	public CrossRoad(String name, State tNorth, int tNorthCapacity, int tNorthFlow, State tWest, int tWestCapacity,
			int tWestFlow, State tEast, int tEastCapacity, int tEastFlow, State tSouth, int tSouthCapacity,
			int tSouthFlow, int sNorthCapacity, int sNorthTravelTime, int sEastCapacity, int sEastTravelTime,
			int sWestCapacity, int sWestTravelTime, int sSouthCapacity, int sSouthTravelTime) {
		// initializing the TrafficLights
		mTrafficNorth = new TrafficLight(tNorth, tNorthCapacity, tNorthFlow);
		mTrafficWest = new TrafficLight(tWest, tWestCapacity, tWestFlow);
		mTrafficEast = new TrafficLight(tEast, tEastCapacity, tEastFlow);
		mTrafficSouth = new TrafficLight(tSouth, tSouthCapacity, tSouthFlow);
		// initializing the streets
		mStreetNorth = new Street(sNorthCapacity, sNorthTravelTime);
		mStreetWest = new Street(sWestCapacity, sWestTravelTime);
		mStreetEast = new Street(sEastCapacity, sEastTravelTime);
		mStreetSouth = new Street(sSouthCapacity, sSouthTravelTime);

		mName = name; // setting the name of the crossRoad
	}

	// to get the name of the crossRoad
	public String getName() {
		return mName;
	}

	/*
	 * this method adds a vehicle to a specific trafficLight. in the same time
	 * adding the current crossRoad to the route of the vehicle
	 */
	public void addVehicle(TrafficLight traffic, Vehicle v) {
		if (!traffic.isFull()) {
			v.addLocationToRoute(this.getName());
			if (traffic.equals(mTrafficNorth))
				mTrafficNorth.addVehicle(v);
			if (traffic.equals(mTrafficWest))
				mTrafficWest.addVehicle(v);
			if (traffic.equals(mTrafficEast))
				mTrafficEast.addVehicle(v);
			if (traffic.equals(mTrafficSouth))
				mTrafficSouth.addVehicle(v);
		}
	}

	/*
	 * this method controls the exchange of the vehicles between the traffic and
	 * the street it will be used in the iterate method.
	 */
	public void control(TrafficLight traffic, Street straat) {
		/*
		 * if the Traffic is GREEN some cars of amount (flow) will be moved from
		 * the traffic to the given street
		 */
		/*
		 * if the Traffic is Yellow some cars of amount (half the flow) will be
		 * moved from the traffic to the given street
		 */

		/*
		 * here i'm getting the flow of the current traffic if it is yellow, the
		 * loop will be to the half of the amount otherwise if traffic is green,
		 * the loop will go for the full amount if the Vehicle reached the
		 * destination it will not be added to the next crossRoad(next street
		 * that leads to a next crossRoad), but it will just be removed from the
		 * traffic to nowhere
		 */
		int flow = traffic.getFlow();
		if (traffic.getState().equals(State.YELLOW))
			flow = flow / 2;

		if (traffic.getState().equals(State.GREEN) || traffic.getState().equals(State.YELLOW)) {
			while (flow != 0 && traffic.getRaminingAmount() != 0 && !straat.isFull()) {
				flow--;
				Vehicle v = traffic.removeVehicle();
				// to check that this crossRoad is not the destination 
				if (!v.getDestination().equalsIgnoreCase(this.getName()))
					straat.addCar(v);
			}
		}
		/*
		 * else the Traffic is RED Just the priority vehicles can pass the
		 * traffic if the Vehicle reached the destination it will not be added
		 * to the next crossRoad but just it will be removed from the traffic to
		 * nowhere
		 */
		else {
			while (traffic.getRaminingAmount() != 0 && traffic.getVehicle().isPriorityVehicle() && !straat.isFull()) {
				Vehicle v = traffic.removeVehicle();
				if (!v.getDestination().equalsIgnoreCase(this.getName()))
					straat.addCar(v);
			}
		}
	}

	/*
	 * This method will forward the call to all contained traffic lights and
	 * thus will take care of time in the crossroad. in each iteration there is
	 * an amount of cars passing the crossroad on green. the half amount is
	 * passing the crossroad on yellow. All priority vehicles waiting in the
	 * crossroad are served first regardless the state of the traffic
	 * lights.(even on red)
	 */
	public void iterate() {
		/*
		 * the iterate in the current situation takes a random street because i
		 * don't have the actual path of the vehicle so that i can't decide
		 * which street to move for. I made 4 arrays each one with one specific
		 * street less to avoid making U turn. However it is not the correct
		 * functionality
		 * 
		 */

		Street[] streetsN = { mStreetSouth, mStreetEast, mStreetWest };
		Street[] streetsS = { mStreetEast, mStreetWest, mStreetNorth };
		Street[] streetsW = { mStreetSouth, mStreetEast, mStreetNorth };
		Street[] streetsE = { mStreetSouth, mStreetWest, mStreetNorth };
		Random rand = new Random();
		int i = rand.nextInt(3);

		// the operations on the NORTH trafficLight
		this.control(mTrafficNorth, streetsN[i]);
		// the operations on the WEST trafficLight
		this.control(mTrafficWest, streetsW[i]);
		// the operations on the EAST trafficLight
		this.control(mTrafficEast, streetsE[i]);
		// the operations on the SOUTH trafficLight
		this.control(mTrafficSouth, streetsS[i]);

		// // the implementation of part two ( the Vehicles go just horizontal
		// or
		// // vertical)
		// // the operations on the NORTH trafficLight
		// this.control(mTrafficNorth, mStreetSouth);
		// // the operations on the WEST trafficLight
		// this.control(mTrafficWest, mStreetEast);
		// // the operations on the EAST trafficLight
		// this.control(mTrafficEast, mStreetWest);
		// // the operations on the SOUTH trafficLight
		// this.control(mTrafficSouth, mStreetNorth);

		mTrafficNorth.iterate();
		mTrafficWest.iterate();
		mTrafficEast.iterate();
		mTrafficSouth.iterate();

		mStreetNorth.iterate();
		mStreetWest.iterate();
		mStreetEast.iterate();
		mStreetSouth.iterate();
	}

	public String toString() {
		String s = mName + " Crossroad:{\n";
		s += "The North: " + mTrafficNorth.toString() + "\n";
		s += "The West: " + mTrafficWest.toString() + "\n";
		s += "The East: " + mTrafficEast.toString() + "\n";
		s += "The South: " + mTrafficSouth.toString() + "\n";
		s += "}";

		return s;
	}
	// getters for the traffics and the streets

	public Street getStreetNorth() {
		return mStreetNorth;
	}

	public Street getStreetWest() {
		return mStreetWest;
	}

	public Street getStreetEast() {
		return mStreetEast;
	}

	public Street getStreetSouth() {
		return mStreetSouth;
	}

	public TrafficLight getTrafficNorth() {
		return mTrafficNorth;
	}

	public TrafficLight getTrafficWest() {
		return mTrafficWest;
	}

	public TrafficLight getTrafficEast() {
		return mTrafficEast;
	}

	public TrafficLight getTrafficSouth() {
		return mTrafficSouth;
	}

	/*
	 * to crossRoads are equal if they have the same name this is not used
	 * anywhere ( but i have to extends Comparable because my Matrix
	 * implementation is not generic) so that i had to make crossRoads accepts
	 * comparable to store it there
	 */
	@Override
	public int compareTo(CrossRoad o) {
		return this.getName().compareTo(o.getName());
	}

	/*
	 * helper method to addVehicle just to if the given side is a String not a
	 * Traffic instance, to use it with the parser. this method will be used
	 * inside the city, thus it helps to adapt with the parser
	 */
	public void addVehicle(String side, Vehicle v) {

		if (side.equalsIgnoreCase("TrafficNorth"))
			this.addVehicle(mTrafficNorth, v);
		if (side.equalsIgnoreCase("TrafficWest"))
			this.addVehicle(mTrafficWest, v);
		if (side.equalsIgnoreCase("TrafficEast"))
			this.addVehicle(mTrafficEast, v);
		if (side.equalsIgnoreCase("TrafficSouth"))
			this.addVehicle(mTrafficSouth, v);

	}

	/*
	 * to be used later(in city checker to stop the simulation if all the
	 * Vehicles reached the destination) to check if the crossRoad is Empty of
	 * Vehicles
	 */
	public boolean isEmpty() {
		if (mTrafficNorth.isEmpty() && mTrafficEast.isEmpty() && mTrafficWest.isEmpty() && mTrafficSouth.isEmpty()
				&& mStreetNorth.isEmpty() && mStreetEast.isEmpty() && mStreetWest.isEmpty() && mStreetSouth.isEmpty())
			return true;
		else
			return false;
	}
}
