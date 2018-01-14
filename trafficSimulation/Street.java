package trafficSimulation;

import dataStructure.CircularVector;

/* Ahmed Kareem A Abdullah
 * This class describe the street properties and their methods 
 */
public class Street {
	// the time required to reach the next Crossroad.
	private int mTravelingTime;
	// specifies the maximum amount of vehicles a street can hold.
	private int mCapacity;

	private CircularVector<CarAndTime> mVehicles;

	/*
	 * Data structure that can hold multiple vehicles and their associated
	 * remaining travel time in the street. and again because we want the street
	 * to have a specific amount of vehicles on the street i used CircularVector
	 * because it is efficient in the complexity and can have specific capacity.
	 * i'm building a pair called CarAndTime to store the both values in the
	 * CircularVector
	 */
	public class CarAndTime {
		private Vehicle car;
		private int time;

		// the constructor of the pair
		public CarAndTime(Vehicle v, int traveltime) {
			car = v;
			time = traveltime;
		}

		// getters and setters for the car and the time that stored in this pair
		public Vehicle getCar() {
			return car;
		}

		public int getTime() {
			return time;
		}

		public void setTime(int time) {
			this.time = time;
		}
	}

	// the constructor of the class
	public Street(int capacity, int travelingTime) {
		mCapacity = capacity;
		mTravelingTime = travelingTime;
		mVehicles = new CircularVector<CarAndTime>(mCapacity);
	}

	// addCar creates a new CarAndTime pair and stores it in the CircularVector
	public void addCar(Vehicle v) {
		if (this.mVehicles.size() < this.mCapacity) {
			CarAndTime carA = new CarAndTime(v, mTravelingTime);
			mVehicles.addFirst(carA);
		}
	}

	// iterate method increases each vehicle time on the street by one minute
	public void iterate() {
		for (int i = 0; i < this.getRemainingAmount(); i++) {
			// O(1) getter of the Circular Vector.
			CarAndTime temp = mVehicles.getIndex(i);
			// to increment the travel time of the car self
			temp.getCar().incrementTravelTime(1);
			// to decrement the travel time of the car in the pair(in the
			// street)
			temp.setTime(temp.getTime() - 1);
		}

	}

	// toString method of the class
	public String toString() {
		String s = "(A street: the periode to pass this street is " + mTravelingTime + " minutes: ";
		s += "The maximum amount of cars that can enter this street is " + mCapacity + ": ";
		s += "The current amount of cars on the street is " + mVehicles.size();
		s += ")";
		return s;
	}

	// remove a vehicle from the street
	public void removeVehicle() {
		mVehicles.removeFirst();
	}
	
	// to get the traveling time of the street 
	public int getTravelingTime() {
		return mTravelingTime;
	}

	// check the current amount of vehicles in the street
	public int getRemainingAmount() {
		return mVehicles.size();
	}

	// to get the first Car and Time instance in the street structure
	public CarAndTime getFirstCarAndTime() {
		if (this.mVehicles.size() > 0) {
			return mVehicles.getFirst();
		}
		return null;
	}

	// Boolean Check for the capacity of the street and if it is full or not
	public Boolean isFull() {
		if (this.mVehicles.size() == this.mCapacity)
			return true;
		else
			return false;
	}
	/* to be used later(in city checker to stop the simulation )
	 * to check if the street is Empty of Vehicles */
	public boolean isEmpty(){
		if(this.mVehicles.size() == 0)
			return true;
		else
			return false;
	}

}
