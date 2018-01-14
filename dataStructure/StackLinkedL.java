package dataStructure;

public class StackLinkedL<T extends Comparable> {
	private LinkedList<T> data;
	
	public StackLinkedL(){
		data = new LinkedList<T>();
		
	}
	public void push ( T o){
		data.addFirst(o);
	}
	public T pop(){
		T x = data.getFirst();
		data.removeFirst();
		return x;
	}
	public T top(){
		return data.getFirst();
	}
	public int size (){
		return data.sizeTO1();
	}
	public boolean empty (){
		return data.isEmpty();
	}
	public void toStringPrint(){
		data.printList();
	}
	//Just to initail a compare to method
	public int compareTo(Object c){
		return 0;
		}
		
	
}