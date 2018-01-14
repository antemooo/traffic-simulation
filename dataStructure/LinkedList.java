package dataStructure;

public class LinkedList<T extends Comparable> {
	private class ListElement<K extends Comparable> {
		private K el1;
		private ListElement<K> el2;

		public ListElement(K el, ListElement<K> nextElement) {
			el1 = el;
			el2 = nextElement;
		}

		public ListElement(K el) {
			this(el, null);
		}

		public K first() {
			return el1;
		}

		public ListElement<K> rest() {
			return el2;
		}

		public void setFirst(K value) {
			el1 = value;
		}

		public void setRest(ListElement<K> value) {
			el2 = value;
		}

	}

	private ListElement<T> head;
	private int count; // I added this variable to use to keep track on the
						// added lists and other operations

	public LinkedList() {
		head = null;
		count = 0; // with the initialization of the LinkedList we define the
					// count as zero because there is not yet any element
	}

	public void addFirst(T o) {
		head = new ListElement<T>(o, head);
		count++; // i added this to increment the counter each time we add a new
					// list

	}

	public T getFirst() {
		return head.first();
	}

	public T get(int n) {
		ListElement<T> d = head;
		while (n > 0) {
			d = d.rest();
			n--;
		}
		return d.first();
	}

	public String toString() {
		String s = "(";
		ListElement<T> d = head;
		while (d != null) {
			s += d.first().toString();
			s += " ";
			d = d.rest();
		}
		s += ")";
		return s;
	}

	/* All the Methods from here and below are my implementation */

	// I implemented this method to make it easier to use the toString method (
	// to print it directly out/ Question1 )
	public void printList() {
		System.out.println(toString());
	}

	// Question 2
	// To find the size of the linked list (Question 2) the time complexity here
	// is O(n)

	// public int sizeTOn(){
	// int sizeList = 0;
	// ListElement<T> y = head;
	// while (y != null){
	// sizeList++;
	// y = y.rest();
	// }
	// return sizeList;
	// }

	// another implementation with time complexity O(1)
	public int sizeTO1() {
		return count;
	}
	// Question 3
	// set method to change an element of (n)th list by a given Comparable
	// (Question 3)
	// I added an if statement to check if the index is in the range or not

	public void set(int n, T o) {
		ListElement<T> x = head;
		if (n < sizeTO1()) {
			while (n > 0) {
				x = x.rest();
				n--;
			}
			x.setFirst(o);
		} else {
			System.out.println("this index is out of range");
		}
	}

	// Question 4
	// to get the element of the last element of the LinkedList (Question 4)
	public T getLast() {
		ListElement<T> j = head;
		while (j.rest() != null) {
			j = j.rest();
		}
		return j.first();
	}
	// To add an Comparable at the end of the LinkedList ( you have to create a
	// ListElement<T> at the end and to put the Comparable in it )
	// Question 5

	public void addLast(T o) {
		if (this.sizeTO1() == 0)
			this.addFirst(o);
		else {
			ListElement<T> i = head;
			while (i.rest() != null) {
				i = i.rest();
			}
			i.setRest(new ListElement<T>(o));
			count++;
		}
	}

	// To search for an element in the LinkedList ( search inside each
	// ListElement<T> ) (Question 6)
	public boolean contains(T o) {
		ListElement<T> c = head;
		int n = count;
		while (n > 0) {
			if (c.first().equals(o)) {
				return true;
			} else {
				c = c.rest();
				n--;
			}
		}
		return false;
	}
	// Question 7 Methods from Vector ( implement them on LinkedList)

	// 1-checking whether a ListElement<T> is empty or not
	public boolean isEmpty() {
		if (head == null) {
			return true;
		}
		return false;
	}

	// 2- Remove First
	public void removeFirst() {
		if (this.sizeTO1() != 0) {
			head = head.rest();
			count--;
		}
	}

	// 3-Remove Last
	public void removeLast() {
		if (this.sizeTO1() != 0 && this.sizeTO1() != 1) { // to check that it is
															// not empty and not
															// the only element
															// otherwise you
															// call removeFirst
			ListElement<T> r = head;
			int index = count;
			while (index - 2 != 0) { // index - 2 because the first
										// ListElement<T> we mentioned with the
										// head so one comparison less (one
										// minus) and we want before the last
										// ListElement<T> so we make another
										// minus
				r = r.rest();
				index--;
			}
			r.setRest(null);
			count--;
		} else if (this.sizeTO1() == 1) { // if it is one element call
											// removeFirst
			this.removeFirst();
		}
	}

	// public void reverse(){
	// ListElement<T> rev = head; // make an initial List element take the head
	// as pointer
	// int n = count; // Make a copy of the LinkedList counter to use it for the
	// loop.
	//
	// for (int i = 0; i < n; i++){ // the first loop takes every element and
	// add it at first
	// this.addFirst(rev.first());
	// rev = rev.rest();
	//
	// }
	// for (int j = 0; j < n; j++){ // the second loop delete the same amount of
	// element from the last
	// this.removeLast(); // otherwise i will have something like (4 3 2 1 1 2 3
	// 4 )
	// }
	// }
	// Another implementation by using vector !!!
	/*
	 * public void reverseLinkedList(){ ListElement<T> x = head; Vector temp =
	 * new Vector(count); int n = count; for (int i = 0; i < n; i++){
	 * temp.addLast(x.first()); x = x.rest(); this.removeFirst(); } for (int i
	 * =0; i < n ; i++){ this.addFirst(temp.get(i)); } }
	 */
	// Another implementation of reverse
	public void reverseL() {
		ListElement<T> current = head;
		ListElement<T> previous = null;
		ListElement<T> next = null;
		while (current != null) {
			next = current.rest();
			current.setRest(previous);
			previous = current;
			current = next;
		}
		head = previous;
	}

	// Question ( you have to solve a small problem here )
	public void fropple() {
		ListElement<T> x = head;
		ListElement<T> y = x.rest();
		T o;
		for (int i = 0; i < count / 2; i = i + 1) {
			if (y.rest() != null) {
				o = x.first();
				x.setFirst(y.first());
				y.setFirst(o);
				x.setRest(y);
				x = y.rest();
				y = x.rest();
			}
		}
		if (count % 2 == 0) {
			o = x.first();
			x.setFirst(y.first());
			y.setFirst(o);
		}
	}

	// Another implementation
	public void fropple2() {
		ListElement<T> temp = head;
		T o;
		while (temp != null && temp.rest() != null) {
			o = temp.first();
			temp.setFirst(temp.rest().first());
			temp.rest().setFirst(o);
			temp = temp.rest().rest();

		}
	}

	// Question 9 Append
	public void append(LinkedList L2) {
		ListElement<T> peace = L2.head;
		int counter = L2.count;

		for (int i = 0; i < counter; i++) {
			this.addLast(peace.first());
			peace = peace.rest();
		}
	}

	// Question 9 another solution Append
	public void appendOn(LinkedList L2) {
		ListElement<T> secondListHead = L2.head;
		ListElement<T> firstList = head;
		while (firstList.rest() != null) {
			firstList = firstList.rest();
		}
		firstList.setRest(secondListHead);
		count = count + L2.count;
	}

	// this method is used to add elements depending on their priority ( used
	// for PriorityQueue)
	public void addSorted(T o) {

		// an empty list , add element in front
		if (head == null)
			head = new ListElement<T>(o, null);
		else if (head.first().compareTo(o) > 0) {
			// we have to add the element in front
			head = new ListElement<T>(o, head);
		} else {
			// we have to find the first element which is bigger
			ListElement<T> d = head;
			while ((d.rest() != null) && (d.rest().first().compareTo(o) < 0)) {
				d = d.rest();
			}
			ListElement<T> next = d.rest();
			d.setRest(new ListElement<T>(o, next));
		}
		count++;
	}

	// this method is used to remove an element with a specific index ( used for
	// PriorityQueue)
	public void removeIndex(int n) {
		ListElement<T> d = head;
		ListElement<T> prev = null;
		if (n == 0) {
			this.removeFirst();
		} else if (n != 0 && count > n) {
			while (n > 0) {
				prev = d;
				d = d.rest();
				n--;
			}
			prev.setRest(d.rest());
		} else {
			System.out.println("This index is out of range");
		}
	}

	public int compareTo(Object o) {

		return 0;
	}

	public T find(T o) {
		ListElement<T> c = head;
		int n = this.sizeTO1();
		while (n > 0) {
			if (c.first().compareTo(o) == 0) {
				return c.first();
			} else {
				c = c.rest();
				n--;
			}
		}
		return null;
	}
}