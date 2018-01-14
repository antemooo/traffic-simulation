package trafficSimulation;

import dataStructure.LinkedList;

/* Ahmed Kareem A Abdullah
 * This class describe the Vehicle properties and their methods 
 */
// The letter M before the variables is to declare that it is a member variable (to be used later instead of using this.)
public class Vehicle {
	// to give a name for the vehicle
	private String mName;
	/*
	 * Boolean that indicates whether the vehicle is a priority vehicle (true)
	 * or not (false) .
	 */
	private boolean mPriority;
	/*
	 * string representation of the vehicle’s destination.
	 */
	private String mDestination;

	/*
	 * the route followed from initial starting point till the current state or
	 * final destination.
	 */
	private LinkedList<String> mRoute;
	/*
	 * the total travel time in minutes since starting at its initial position.
	 */
	private int mTravelTime;
	/*
	 * I used LinkedList as a data structure for the route (addFirst,
	 * removeFirst) == O(1)..
	 */

	/*
	 * The First constructor takes two arguments (the destination and the
	 * priority of the vehicle )
	 */
	public Vehicle(String name, String destination, boolean priority) {
		mName = name;
		mDestination = destination;
		mPriority = priority;
		/*
		 * here is just an establishment for the (LinkedList Data Structure Type
		 * string) of the route
		 */
		mRoute = new LinkedList<String>();
		// the travel time at the start point is zero
		mTravelTime = 0;
		/*
		 * this line could be omitted, i'm defining an initial point called
		 * Start.
		 */
		this.addLocationToRoute("start");
	}

	/*
	 * the second constructor takes just the name and destination as its argument and set
	 * the priority to false ( as a normal vehicle)
	 */
	public Vehicle(String name, String destination) {
		this(name, destination, false);
	}

	// to get the name of the vehicle as given when creating a vehicle instance
	public String getName() {
		return mName;
	}

	// this method is to get the current destination of the vehicle
	public String getDestination() {
		return mDestination;
	}

	// this method is to set a new destination to the vehicle
	public void setDestination(String destination) {
		mDestination = destination;
	}

	// this method check whether the vehicle is priority vehicle or not
	public boolean isPriorityVehicle() {
		return mPriority;
	}

	/*
	 * this method is to add a location to the route because my route data type
	 * is LinkedList, I added the new location at the beginning to keep the
	 * track on the sequence first and for the minimum time complexity O(1) for
	 * the addFirst in the LinkedList implementation.
	 */
	public void addLocationToRoute(String route) {
		mRoute.addFirst(route);
	}

	// this method is used to increment the Travel time of the vehicle
	public void incrementTravelTime(int num) {
		mTravelTime = mTravelTime + num;
	}
	
	/*
	 * this method is used to rest the travel time (when the car reached the
	 * destination or if the car will start again from the initial start point)
	 */

	public void resetTravelTime() {
		mTravelTime = 0;
	}
	// this method is used to print the route that the car has been following.
	/*
	 * (if and else) here just if we would omit the line of initializing the
	 * start point then it will print this message if the route is empty ( new
	 * route ) the code can be just one line ( mRoute.printList(); )
	 */

	public String printRoute() {
		if (!mRoute.isEmpty()) {
			return mRoute.toString();
		} else {
			System.out.println("The vehicle's route is not declared yet");
			return null;
		}
	}

	/*
	 * toString to print the car details and whether it is a priority
	 * car or not
	 */
	public String toString() {
		String s = "A ";
		if (this.isPriorityVehicle()) {
			s += "priority ";
		}
		s += "vehicle with destination " + mDestination;
		return s;
	}

	// this method is used to print the TravelTime of the vehicle
	public void printTime() {
		System.out.println(mTravelTime);
	}

	// to use it in other classes to get the travel time of a vehicle
	public int getTravelTime() {
		return mTravelTime;
	}
}
