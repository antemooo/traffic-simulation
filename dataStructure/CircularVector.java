package dataStructure;

public class CircularVector<T extends Object> {
	private T data[];
	private int first;
	private int count;
	private int mCapacity;

	@SuppressWarnings("unchecked")
	public CircularVector(int capacity) {
		count = 0;
		first = 0;
		mCapacity = capacity;
		data = (T[]) new Object[mCapacity];
	}

	public int size() {
		return count;
	}

	public void addFirst(T element) {
		if (this.size() < this.capacity()) {
			first = (first + mCapacity - 1) % mCapacity;
			data[first] = element;
			count++;
		}

	}

	public void addLast(T element) {
		// here we just add the element at the next index after the index of the
		// existing elements ( at the end)..( AnTeMoOo)
		if (this.size() < this.capacity()) {
			int last = (first + count) % mCapacity;
			data[last] = element;
			count++;
		}

	}

	public T getFirst() {
		return data[first];
	}

	/*
	 * we put minus 1 ( -1 ) here because when we added an element at the last
	 * the count had been increased as well so that we have to return the right
	 * Index otherwise we will get the first element instead of the last (
	 * AnTeMoOo )
	 */
	public T getLast() {
		int last = (first + count - 1) % mCapacity;
		return data[last];
	}

	public void removeFirst() {
		if (count > 0) {
			first = (first + 1) % mCapacity;
			count--;
		}
	}

	public void removeLast() {
		if (this.size() > 0) {
			count--;
		}

	}

	private int capacity() {
		return mCapacity;
	}

	public void print() {
		System.out.print("[");
		for (int i = 0; i < count; i++) {
			int index = (first + i) % capacity();
			System.out.print(data[index] + " ");
		}
		System.out.println("]");
	}

	public boolean isEmpty() {
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	public T getIndex(int i) {
		if (this.size() > 0) {
			int index = (first + i) % capacity();
			return data[index];
		}
		return null;

	}

	public void setIndex(int i, T o) {
		int index = (first + i) % capacity();
		data[index] = o;

	}
}