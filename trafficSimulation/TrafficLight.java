package trafficSimulation;

import dataStructure.CircularVector;

/* Ahmed Kareem A Abdullah
 * This class describe the TrafficLight properties and their methods 
 */
// The letter M before the variables is to declare that it is a member variable (instead of using this.)
public class TrafficLight {

	public enum State {
		// enumeration To describe state of the traffic with a specific duration
		RED(3), YELLOW(1), GREEN(2);
		private int duration;

		// State constructor to set the duration that specified above
		private State(int duration) {
			this.duration = duration;
		}

		/*
		 * (setter and getter) to set and get the duration of the Enumerator
		 */
		public void setDuration(int duration) {
			this.duration = duration;
		}

		public int getDuration() {
			return this.duration;
		}
	};

	// integer counter used to control State Machine
	private int mCurrentTime;
	/*
	 * Local variable type state which it is an enumerator to describe the
	 * trafficLight state.
	 */
	private State mState;
	private CircularVector<Vehicle> mVehicles; /*
												 * this is a data structure
												 * where vehicles are waiting
												 * before they can go. Priority
												 * vehicles can pass all other
												 * vehicles i used
												 * CircularVector because the
												 * addFirst, addLast,
												 * removeFirst and removeLast
												 * are O(1) the second reason it
												 * might be inefficient to use
												 * any type of vectors because
												 * of the capacity limitations,
												 * however, we have a hint that
												 * the traffic can accept a
												 * given amount of vehicles that
												 * wait in front of it so that
												 * we can lay on that amount to
												 * define the vector
												 */

	private int mFlow;
	/*
	 * specifies how many vehicles can pass the traffic light on a color per
	 * minute. ( the amount on Green per minute). For Yellow state the flow is
	 * reduced by a half.
	 */

	private int mCapacity; /*
							 * specifies the maximum amount of vehicles that can
							 * wait for the traffic light.
							 */

	// the constructor of the class TrafficLight
	public TrafficLight(State s, int capacity, int flow) {
		// state definition for the traffic late
		mState = s;
		/*
		 * CurrentTime is the counter that controls the traffic iteration(it
		 * gets its value from the enum self)
		 */
		mCurrentTime = mState.getDuration();

		// the amount of the vehicles that can pass the traffic per minute
		mFlow = flow;
		// the traffic Capacity
		mCapacity = capacity;
		// the traffic data structure
		mVehicles = new CircularVector<Vehicle>(mCapacity);

	}

	// it gives back the current traffic state
	public State getState() {
		return mState;

	}

	// iterate is the method that controls the traffic light states changing
	public void iterate() {
		mCurrentTime--;
		switch (mState) {
		case RED:
			// System.out.println(mState);
			if (mCurrentTime == 0) {
				mState = State.GREEN;
				mCurrentTime = mState.getDuration();
			}
			break;
		case GREEN:
			// System.out.println(mState);
			if (mCurrentTime == 0) {
				mState = State.YELLOW;
				mCurrentTime = mState.getDuration();
			}
			break;
		case YELLOW:
			// System.out.println(mState);
			if (mCurrentTime == 0) {
				mState = State.RED;
				mCurrentTime = mState.getDuration();
			}
			break;
		}
	}

	// this method is to set a new duration to the state ( it calls the setter
	// of the enum)
	public void setTimeForState(State s, int time) {
		s.setDuration(time);
	}

	/*
	 * this method is using CircularVector implementation ( if it is a priority
	 * vehicle it will be added at the beginning if not then it will be at the
	 * end)
	 */
	public void addVehicle(Vehicle v) {
		if (this.mVehicles.size() <= this.mCapacity)
			if (v.isPriorityVehicle()) {
				mVehicles.addFirst(v);
			} else {
				mVehicles.addLast(v);
			}
	}

	/*
	 * Because my date structure is circularVector and i'm adding the priority
	 * vehicle at the beginning and the others at the end ,, when we want to
	 * pass the vehicles first we will start with priority vehicles (it will
	 * pass the traffic first) so that i used removeFirst the removed Vehicle
	 * will be returned (like popping a vehicle from the structure)
	 */
	public Vehicle removeVehicle() {
		Vehicle temp = mVehicles.getFirst();
		mVehicles.removeFirst();
		return temp;
	}

	public String toString() {
		String s = "(A traffic light on state " + mState.toString() + " : ";
		s += "The maximum capacity of the traffic is " + mCapacity + " : ";
		s += "The current filled amount is " + this.mVehicles.size() + " : ";
		s += "The flow on Green is " + mFlow;
		s += ")";
		return s;
	}

	// I create this method to be used in the class CrossRoad in the iterate
	// method ( to get the flow)
	public int getFlow() {
		return mFlow;
	}

	// this method returns the current amount of vehicles on the Traffic
	public int getRaminingAmount() {
		return this.mVehicles.size();
	}

	// to get the first Vehicle on the traffic
	public Vehicle getVehicle() {
		return mVehicles.getFirst();
	}

	// Boolean check for the capacity of the traffic if it is full or not
	public Boolean isFull() {
		if (this.mVehicles.size() == this.mCapacity)
			return true;
		else
			return false;
	}
	
	/* to be used later(in city checker to stop the simulation )
	 * to check if the traffic is Empty of Vehicles */
	public boolean isEmpty(){
		if(this.mVehicles.size() == 0)
			return true;
		else
			return false;
	}
}
