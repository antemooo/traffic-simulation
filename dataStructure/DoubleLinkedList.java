package dataStructure;

public class DoubleLinkedList<T> {
	private class DoubleLinkedListElement<K> {
		private K data;
		private DoubleLinkedListElement<K> nextElement;
		private DoubleLinkedListElement<K> previousElement;

		public DoubleLinkedListElement(K v, DoubleLinkedListElement<K> next, DoubleLinkedListElement<K> previous) {
			data = v;
			nextElement = next;
			previousElement = previous;
			if (nextElement != null)
				nextElement.previousElement = this;
			if (previousElement != null)
				previousElement.nextElement = this;
		}

		public DoubleLinkedListElement(K v) {
			this(v, null, null);
		}

		public DoubleLinkedListElement<K> previous() {
			return previousElement;
		}

		public K value() {
			return data;
		}

		public DoubleLinkedListElement<K> next() {
			return nextElement;
		}

		public void setNext(DoubleLinkedListElement<K> value) {
			nextElement = value;
		}

		public void setPrevious(DoubleLinkedListElement<K> value) {
			previousElement = value;
		}
	}

	private int count;
	private DoubleLinkedListElement<T> head;
	private DoubleLinkedListElement<T> tail;

	public DoubleLinkedList() {
		head = null;
		tail = null;
		count = 0;
	}

	public T getFirst() {
		return head.value();
	}

	public T getLast() {
		return tail.value();
	}

	public int size() {
		return count;
	}

	public void addFirst(T value) {
		head = new DoubleLinkedListElement<T>(value, head, null);
		if (tail == null)
			tail = head;
		count++;
	}

	public void addLast(T value) {
		tail = new DoubleLinkedListElement<T>(value, null, tail);
		if (head == null)
			head = tail;
		count++;
	}

	public void removeFirst() {
		head = head.next();
		if (head == null) {
			tail = null;
		} else {
			head.setPrevious(null);
		}
		count--;
	}

	public void removeLast() {
		tail = tail.previous();
		if (tail == null) {
			head = null;
		} else {
			tail.setNext(null);
		}
		count--;
	}

	public void print() {
		DoubleLinkedListElement<T> d = head;
		System.out.print("(");
		while (d != null) {
			System.out.print(d.value() + " ");
			d = d.next();
		}
		System.out.println(")");
	}

	public void printReverse() {
		DoubleLinkedListElement<T> d = tail;
		System.out.print("(");
		while (d != null) {
			System.out.print(d.value() + " ");
			d = d.previous();
		}
		System.out.print(")");
	}
	public void reverse(){
		DoubleLinkedListElement<T> temp = head;
		DoubleLinkedListElement<T> next = null;
		DoubleLinkedListElement<T> prev = null;
		while(temp != null ){
			next = temp.next();
			prev = temp.previous();
			temp.setPrevious(next);
			temp.setNext(prev);
			prev = temp;          // here we make a copy of the temp before changing it then we will return this copy when temp became == null 
			temp = temp.previous();
		}
		tail = head;   // here just to swap the head with the tail ( necessary for PrintReverse )
		head = prev;   // the return value that we copied 
	}
	public T getIndex(int n){
		if (count != 0 && n < count){
			DoubleLinkedListElement<T> temp = head;
			while (n != 0){
				temp = temp.next();
				n--;
			}
			return temp.value();
		}
		else if (n == count && count == 1) return this.getFirst();
		else if (n == count && count != 1) return this.getLast();
		else return null;
	}
}
