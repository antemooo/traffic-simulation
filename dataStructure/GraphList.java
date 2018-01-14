package dataStructure;

public class GraphList<T extends Comparable> {

	private class Node<K extends Comparable> implements Comparable {
		private K info;
		private LinkedList<Edge<K>> edges;
		private Boolean visited;

		public Node(K label) {
			info = label;
			visited = false;
			edges = new LinkedList<Edge<K>>(); // edit the constructor
		}

		public void addEdge(Edge<K> e) {
			edges.addLast(e);
		}

		public void setEdge(Edge<K> e, int w){
			Edge<K> temp = edges.find(e);
			if(temp!=null)
				temp.setWeight(w);
		}
		public int compareTo(Object o) {
			// two nodes are equal if they have the same label
			Node<K> n = (Node<K>) o;
			return n.info.compareTo(info);
		}

		public K getLabel() {
			return info;
		}

		public String toString() {
			String s = "(" + info.toString() + edges.toString() + ")";
			return s;
		}

		public Boolean getVisited() {
			return visited;
		}

		public void setVisited(Boolean visited) {
			this.visited = visited;
		}

	}

	private class Edge<E extends Comparable> implements Comparable {
		private Node<E> toNode;
		private int mWeight;

		public Edge(Node<E> to, int weight) {
			toNode = to;
			mWeight = weight;
		}

		public int compareTo(Object o) {
			// two edges are equal if they point
			// to the same node.
			// this assumes that the edges are
			// starting from the same node !!!
			Edge<E> n = (Edge<E>) o;
			return n.toNode.compareTo(toNode);
		}

		public Node<E> getToNode() {
			return toNode;
		}

		public void setToNode(Node<E> toNode) {
			this.toNode = toNode;
		}

		public int getWeight() {
			return mWeight;
		}

		public void setWeight(int weight) {
			this.mWeight = weight;
		}

		public String toString() {
			return toNode.getLabel().toString() + " Wight:" + mWeight;
		}
	}

	private LinkedList<Node<T>> nodes;

	public GraphList() {

		nodes = new LinkedList<Node<T>>();
	}

	public void addNode(T label) {
		nodes.addFirst(new Node<T>(label));
	}

	private Node<T> findNode(T nodeLabel) {
		Node<T> n = new Node<T>(nodeLabel);
		return nodes.find(n);
	}

	public void addEdge(T nodeLabel1, T nodeLabel2, int weight) {
		Node<T> n1 = findNode(nodeLabel1);
		Node<T> n2 = findNode(nodeLabel2);
		n1.addEdge(new Edge<T>(n2, weight));
	}
	public void setEdge(T nodeLabel1, T nodeLabel2, int weight) {
		Node<T> n1 = findNode(nodeLabel1);
		Node<T> n2 = findNode(nodeLabel2);
		n1.setEdge(new Edge<T>(n2,0), weight);
	}

	// Question 7: it will not work when there is a cycle (it keeps looping) ,
	// just works with acyclic graph
	public Boolean findPathBooleanOld(T nodeLabel1, T nodeLabel2) {
		Node<T> startState = findNode(nodeLabel1);
		Node<T> endState = findNode(nodeLabel2);
		StackLinkedL<Node<T>> toDoList = new StackLinkedL<Node<T>>();
		toDoList.push(startState);
		while (!toDoList.empty()) {
			Node<T> current = toDoList.pop();
			if (current.compareTo(endState) == 0)
				return true;
			else {

				for (int i = 0; i < current.edges.sizeTO1(); i++) {
					Edge<T> e = current.edges.get(i);
					toDoList.push(e.toNode);
				}
			}
		}
		return false;
	}

	// Question 8
	// optimizing the previous method to make it work when there is a loop in
	// the graph (Cyclic Graph)
	public Boolean findPathBooleanVisited(T nodeLabel1, T nodeLabel2) {
		Node<T> startState = findNode(nodeLabel1);
		Node<T> endState = findNode(nodeLabel2);
		StackLinkedL<Node<T>> toDoList = new StackLinkedL<Node<T>>();
		LinkedList<Node<T>> visited = new LinkedList<Node<T>>();
		toDoList.push(startState);
		while (!toDoList.empty()) {
			Node<T> current = toDoList.pop();
			visited.addFirst(current);
			if (current.compareTo(endState) == 0)
				return true;
			else {
				for (int i = 0; i < current.edges.sizeTO1(); i++) {
					Edge<T> e = current.edges.get(i);
					if (!visited.contains(e.toNode)) {
						toDoList.push(e.toNode);
					}
				}
			}
		}
		return false;
	}

	// Question 9:optimizing the previous method by making a boolean flag called
	// visited in the Node class
	// instead of adding in the list and looping over the list each time
	public Boolean findPath(T nodeLabel1, T nodeLabel2) {
		this.restVisted();
		Node<T> startState = findNode(nodeLabel1);
		Node<T> endState = findNode(nodeLabel2);
		StackLinkedL<Node<T>> toDoList = new StackLinkedL<Node<T>>();
		toDoList.push(startState);
		while (!toDoList.empty()) {
			Node<T> current = toDoList.pop();
			current.setVisited(true);
			if (current.compareTo(endState) == 0)
				return true;
			else {

				for (int i = 0; i < current.edges.sizeTO1(); i++) {
					Edge<T> e = current.edges.get(i);
					if (!e.toNode.getVisited())
						toDoList.push(e.toNode);
				}
			}
		}
		return false;
	}
	// private void DFS(Node<T> current) {
	// current.setVisited(true);
	// for (int i = 0; i < current.edges.size(); i++) {
	// Edge<T> e = current.edges.get(i);
	// Node<T> next = e.getToNode();
	// if (!next.getVisited())
	// DFS(next);
	// }
	// System.out.println(current.info);
	// }
	//
	// public void DFS() {
	// for (int i = 0; i < nodes.size(); i++) {
	// Node<T> current = nodes.get(i);
	// current.setVisited(false);
	// }
	// for (int i = 0; i < nodes.size(); i++) {
	// Node<T> current = nodes.get(i);
	// if (!current.getVisited())
	// DFS(current);
	// }
	// }

	// to make the topological sort
	private LinkedList<T> DFS(Node<T> current, LinkedList<T> result) {
		current.setVisited(true);
		for (int i = 0; i < current.edges.sizeTO1(); i++) {
			Edge<T> e = current.edges.get(i);
			Node<T> next = e.getToNode();
			if (!next.getVisited())
				DFS(next, result);
		}
		result.addFirst(current.info);
		return result;
	}
	public LinkedList<T> DFS() {
		this.restVisted();
		LinkedList<T> result = new LinkedList<T>();
		for (int i = 0; i < nodes.sizeTO1(); i++) {
			Node<T> current = nodes.get(i);
			if (!current.getVisited())
				DFS(current, result);
		}
		return result;
	}

	public void restVisted() {
		for (int i = 0; i < nodes.sizeTO1(); i++) {
			Node<T> current = nodes.get(i);
			current.setVisited(false);
		}
	}

	public String toString() {
		return nodes.toString();
	}

	public void print() {
		System.out.println(this.toString());
	}

}