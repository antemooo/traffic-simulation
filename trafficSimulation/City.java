package trafficSimulation;

import dataStructure.GraphList;
import dataStructure.MatrixGraph;

/* Ahmed Kareem A Abdullah
 * This class describe the City properties and their methods 
 */
public class City {
	/*
	 * the City has a horizontal and vertical streets which will generate a
	 * matrix of crossRoads. the city has a name as well as String
	 */
	private String mName; // City name
	private int mHorizontalStreets; // number of rows in the matrix
	private int mVerticalStreets; // number of column
	/*
	 * matrix to store all the CrossRoads, it will be used to help to decide the
	 * next step of the vehicle(next traffic from the current street )
	 */
	private MatrixGraph mCrossRoads;
	/*
	 * city data structure as graph to store all the crossRoad and the
	 * connection between them it will be used to adapt the Shortest Path
	 * Algorithm
	 */
	private GraphList<CrossRoad> mCity;

	/*
	 * Simple Constructor to generate the name , matrix of crossRoads, and city
	 * Graph.
	 */
	public City(String name, int horizontal, int vertical) {
		mName = name;
		mHorizontalStreets = horizontal;
		mVerticalStreets = vertical;
		mCrossRoads = new MatrixGraph(mHorizontalStreets, mVerticalStreets);
		mCity = new GraphList<CrossRoad>();

	}

	// method to add crossRoad to the matrix and to the graph
	public void initializeCrossRoad(int i, int j, CrossRoad cr) {
		mCrossRoads.addEdge(i, j, cr);
		mCity.addNode(cr);
	}

	public void connect() {
		/*
		 * this method will initialize the connection between the crossRoad.
		 * (the edges of the graph). after that implement the Shortest Path
		 * Algorithm.(the algorithm is not implemented yet).
		 */
		for (int i = 0; i < mHorizontalStreets; i++)
			for (int j = 0; j < mVerticalStreets; j++) {
				CrossRoad currentCR = (CrossRoad) mCrossRoads.getEdge(i, j);
				if (i - 1 >= 0)
					mCity.addEdge(currentCR, (CrossRoad) mCrossRoads.getEdge(i - 1, j),
							currentCR.getStreetNorth().getTravelingTime());
				if (i + 1 < this.mHorizontalStreets)
					mCity.addEdge(currentCR, (CrossRoad) mCrossRoads.getEdge(i + 1, j),
							currentCR.getStreetSouth().getTravelingTime());
				if (j - 1 >= 0)
					mCity.addEdge(currentCR, (CrossRoad) mCrossRoads.getEdge(i, j - 1),
							currentCR.getStreetWest().getTravelingTime());
				if (j + 1 < this.mVerticalStreets)
					mCity.addEdge(currentCR, (CrossRoad) mCrossRoads.getEdge(i, j + 1),
							currentCR.getStreetEast().getTravelingTime());

			}
	}

	// method to add a Vehicle to a specific CrossRoad and a given trafficLight
	public void addVehicle(String cRoad, TrafficLight side, Vehicle v) {
		for (int i = 0; i < mHorizontalStreets; i++)
			for (int j = 0; j < mVerticalStreets; j++) {
				CrossRoad temp = (CrossRoad) mCrossRoads.getEdge(i, j);
				if (temp.getName().equalsIgnoreCase(cRoad)) {
					temp.addVehicle(side, v);
					break;
				}
			}
	}

	/*
	 * the same method to add vehicle but it takes a String Side instead of
	 * Traffic, to use it with parser.
	 */
	public void addVehicle(String cRoad, String side, Vehicle v) {
		for (int i = 0; i < mHorizontalStreets; i++)
			for (int j = 0; j < mVerticalStreets; j++) {
				CrossRoad temp = (CrossRoad) mCrossRoads.getEdge(i, j);
				if (temp.getName().equalsIgnoreCase(cRoad)) {
					temp.addVehicle(side, v);
					break;
				}
			}
	}

	/*
	 * iterate method that triggers the iterate of each crossRoad in the City
	 * and move the Vehicle from each streets to the next CrossRoad. ( it moves
	 * Vehicles from street to a trafficLight of the next cross).
	 */
	public void iterate() {
		for (int i = 0; i < mHorizontalStreets; i++)
			for (int j = 0; j < mVerticalStreets; j++) {
				CrossRoad currentCR = (CrossRoad) mCrossRoads.getEdge(i, j);
				currentCR.iterate();
				// it takes the (i,j) index, the current CrossRoad, a specific
				// street
				this.control(i, j, currentCR, currentCR.getStreetNorth());
				this.control(i, j, currentCR, currentCR.getStreetSouth());
				this.control(i, j, currentCR, currentCR.getStreetWest());
				this.control(i, j, currentCR, currentCR.getStreetEast());
			}

	}

	// helper method to move the Vehicles from a street to the next traffic in
	// the next crossRoad
	private void control(int i, int j, CrossRoad crossR, Street street) {

		if (street.equals(crossR.getStreetNorth()) && !street.isFull() && i - 1 >= 0)
			this.move(street, (CrossRoad) mCrossRoads.getEdge(i - 1, j),
					((CrossRoad) mCrossRoads.getEdge(i - 1, j)).getTrafficSouth());
		if (street.equals(crossR.getStreetSouth()) && !street.isFull() && i + 1 < this.mHorizontalStreets)
			this.move(street, (CrossRoad) mCrossRoads.getEdge(i + 1, j),
					((CrossRoad) mCrossRoads.getEdge(i + 1, j)).getTrafficNorth());
		if (street.equals(crossR.getStreetWest()) && !street.isFull() && j - 1 >= 0)
			this.move(street, (CrossRoad) mCrossRoads.getEdge(i, j - 1),
					((CrossRoad) mCrossRoads.getEdge(i, j - 1)).getTrafficEast());
		if (street.equals(crossR.getStreetEast()) && !street.isFull() && j + 1 < this.mVerticalStreets)
			this.move(street, (CrossRoad) mCrossRoads.getEdge(i, j + 1),
					((CrossRoad) mCrossRoads.getEdge(i, j + 1)).getTrafficWest());
	}

	/*
	 * helper method that removes a car from the street and add it to the
	 * TrafficLight of the next CrossRoad
	 */
	private void move(Street street, CrossRoad crossRoad, TrafficLight traffic) {
		// check if there still vehicle in the street,
		// and the first vehicle reached the end of the street
		if (street.getRemainingAmount() != 0 && street.getFirstCarAndTime().getTime() == 0) {
			for (int i = 0; i < street.getRemainingAmount(); i++) {
				Vehicle v = street.getFirstCarAndTime().getCar();
				/*
				 * Check if the next crossRoad's traffic is not full WE
				 * ADD,otherwise we break so the car stays at the current
				 * street.
				 */
				if (!traffic.isFull()) {
					crossRoad.addVehicle(traffic, v);
					street.removeVehicle();
					/*
					 * Check if there is no more vehicles in the street, OR the
					 * first one time is not zero so it is not at the end of the
					 * street yet Break to save time
					 */
					if (street.getRemainingAmount() == 0 || street.getFirstCarAndTime().getTime() != 0)
						break;
				} else
					break;
			}
		}
	}

	// to get the City Name
	public String getName() {
		return mName;
	}

	// ToString to print the city
	public String toString() {
		return mCrossRoads.toString();
	}

	/*
	 * Boolean checks all the crossRoads , if they are empty then the city is
	 * empty so that the simulation can stop
	 */
	public boolean isEmpty() {
		boolean check = true;
		for (int i = 0; i < mHorizontalStreets; i++)
			for (int j = 0; j < mVerticalStreets; j++) {
				CrossRoad currentCR = (CrossRoad) mCrossRoads.getEdge(i, j);
				if (currentCR.isEmpty())
					continue;
				else{
					check = false;
					break;
				}
			}
		return check;
	}

	/*
	 * helper method to be used to get a CrossRoad instance from a String name
	 * of crossRoad
	 */
	private CrossRoad getCrossRoad(String cr) {
		for (int i = 0; i < mHorizontalStreets; i++)
			for (int j = 0; j < mVerticalStreets; j++) {
				CrossRoad currentCR = (CrossRoad) mCrossRoads.getEdge(i, j);
				if (currentCR.getName().equalsIgnoreCase(cr))
					return currentCR;
			}
		return null;

	}

	/*
	 * to add a Road Work between two CrossRoads (the joint street between them)
	 * it takes a String from the parser and gets the corresponding CrossRoad
	 * for this name from the matrix, after that it pass them to the next add
	 * Road work method
	 */
	public void addRoadWork(String cr1, String cr2) {
		this.addRoadWork(this.getCrossRoad(cr1), this.getCrossRoad(cr2));

	}
	
	/*
	 * this method will set the edge between two CrossRoads to infinity
	 * (the maximum integer declaration).
	 * so that this edge will not be calculated in the Shortest path
	 */
	public void addRoadWork(CrossRoad cr1, CrossRoad cr2) {
		this.mCity.setEdge(cr1, cr2, Integer.MAX_VALUE);
	}
}
