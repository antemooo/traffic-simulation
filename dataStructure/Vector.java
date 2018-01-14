package dataStructure;

public class Vector<T extends Comparable> {
	protected T data[];
	protected int mCapacity;
	protected int count;

	public Vector(int capacity) // constructor to make a new vector of specific
								// capacity, and to set the number of existing
								// element to zero because that it is new
	{
		mCapacity = capacity;
		data = (T[]) new Comparable[mCapacity];
		count = 0;
	}
	// this method is just for test
	// public void addElement(int n1 , int n2){ //to full the vector with
	// elements ( I created it )
	// for (int j = n1; j <= n2; j++){
	// data[count] = j;
	// count++;
	// }
	// }

	public T get(int index) {
		return data[index]; // to get an element at specific index
	}

	public T getLast() { // to get the last element of the vector at the moment.
		return data[count - 1];
	}

	public T getFirst() { // to get the first element of the vector
		return data[0];
	}

	public void addFirst(T o) {
		// we increment the count once to move to the next empty place before
		// the loop is began
		if (mCapacity == count) {
			this.extendVector();
		}
		for (int i = count - 1; i >= 0; i--) {
			data[i + 1] = data[i];
		}
		data[0] = o;
		count++;
	}

	// to add an element at the end ,(if you assume that there is an empty place
	public void addLast(T o) {
		if (mCapacity == count) {
			this.extendVector();
		}
		data[count] = o;
		count++;
	}

	public void set(int index, T obj) // Overwriting an element in a vector at
										// specific index
	{
		data[index] = obj;
	}

	public int size() // Getting the size of a vector
	{
		return count;
	}

	public boolean isEmpty() // checking whether a vector is empty
	{
		return size() == 0;
	}

	public boolean contains(T obj) { // Checking whether the vector contains a
										// given element

		for (int i = 0; i < count; i++) {
			if (data[i].equals(obj))
				return true;
		}
		return false;
	}

	public void print() { // this method to print out all the elements of the
							// vector
		System.out.println("Here's a list of all elements in our vector ");
		System.out.print("[");
		for (int ele = 0; ele < count; ele++) {
			System.out.print(data[ele] + " ");
		}
		System.out.print("]");
		System.out.println();
	}

	public void removeLast() {
		if (count != 0) {
			data[count - 1] = null;
			count--;
		}

	}

	public void removeFirst() {
		if (count != 0) {
			for (int i = 0; i < count - 1; i++) {
				data[i] = data[i + 1];
			}
			data[count - 1] = null;
			count--;
		}
	}

	public void reverse() {

		for (int i = 0; i < count / 2; i++) { // we divide the vector to the
												// half ( to make the mirror
												// because after the half it
												// will re reverse the element
												// that we have already sort in
												// the reverse function
			T x = data[i];
			data[i] = data[count - 1 - i];
			data[count - i - 1] = x;
		}
	}

	public Vector vDuplicates() { // we create a method type vector to return a
		// vector
		Vector doubled = new Vector(mCapacity * 2);
		for (int i = 0; i < count; i++) {
			doubled.set(i * 2, data[i]); // to add the odd elements
			doubled.set((i * 2) + 1, data[i]); // to add the even elements
		}
		doubled.count = count * 2;
		return doubled;
	}

	public Vector interLeave(Vector v8) {
		Vector interLeaveVar = new Vector(count * 2);
		for (int i = 0; i < count; i++) {
			interLeaveVar.set(i * 2, data[i]); // to add the element of the
												// first Vector as the odd
												// elements
			interLeaveVar.set((i * 2) + 1, v8.data[i]); // to add the element of
														// the second vector as
														// the even elements
		}
		interLeaveVar.count = count * 2;
		return interLeaveVar;
	}

	private void extendVector() { // we create a vector to extend the main
									// vector ( you have to complete the
									// question 9 by adding this function to the
									// function of add last )

		mCapacity = 2 * mCapacity;
		T data2[] = (T[]) new Object[mCapacity];
		for (int i = 0; i < count; i++) {
			data2[i] = data[i];
		}
		data = data2;
	}

	// I made this function just to check the Capacity after extending a Vector
	public int capa() {
		return mCapacity;
	}

	// Just to initail a compare to method
	public int compareTo(Object c) {
		return 0;
	}

	/*
	 * public boolean binarySearch ( T key){ long start = 0; long end = count
	 * -1; while ( start <= end) { long middle = ( start + end + 1) / 2; if (key
	 * < data[middle]){ end = middle -1; }else if( key > data[middle]){ start =
	 * middle + 1; }else{ return true ;} } return false ; }
	 */
	public T find(T obj) { // Checking whether the vector contains a given
							// element

		for (int i = 0; i < count; i++) {
			if (data[i].compareTo(obj) == 0)
				return data[i];
		}
		return null;
	}

	public String toString() {
		String s = "";
		s += "[";
		for (int ele = 0; ele < count; ele++) {
			s += data[ele] + " ";
		}
		s += "]";
		return s;
	}
}
