package dataStructure;

public class Dictionary<T, C> {
	private class DictionaryPair<K, V> implements Comparable {
		private K key;
		private V value;

		public DictionaryPair(K someKey, V someValue) {
			key = someKey;
			value = someValue;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public void setKey(K newKey) {
			key = newKey;
		}

		public void setValue(V newValue) {
			value = newValue;
		}
		public String toString(){
			String s = "(Key: " + key.toString() + ", Value: "+value.toString() + ")";
			return s;
		}
		public int compareTo(Object o){
			DictionaryPair<K, V> temp = (DictionaryPair<K, V>)o;
			return ((Comparable) this.key).compareTo(temp.getKey());
		}
		
	}

	private LinkedList<DictionaryPair<T, C>> data;

	public Dictionary() {
		data = new LinkedList<DictionaryPair<T, C>>();
	}

	public void add(T key, C value) {
		int check = findPosition(key);
		if (check != -1){
			DictionaryPair<T, C> temp = data.get(check);
			temp.setValue(value);
			data.set(check, temp);
		}else{
			DictionaryPair<T, C> d = new DictionaryPair<T, C>(key, value);
			data.addFirst(d);
		}
	}

	private int findPosition(T key) {
		for (int i = 0; i < data.sizeTO1(); i++) {
			DictionaryPair<T, C> temp = data.get(i);
			if (temp.getKey().equals(key)) {
				return i;
			}
		}
		return -1;
	}

	public C find(T key) {
		int check = findPosition(key);
		if (check != -1) {
			DictionaryPair<T, C> temp = data.get(check);
			return temp.getValue();
		} else {
			return null;
		}
	}
	public void print(){
		data.printList();
	}
	public int size(){
		return data.sizeTO1();
	}
	
	public C getIndex(int i){
		return data.get(i).getValue();
	}
	public String toString(){
		return data.toString();
	}

}
