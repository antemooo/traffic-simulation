package dataStructure;

public class MatrixGraph {
	private Matrix data;

	public MatrixGraph(int row, int column) {
		data = new Matrix(row, column);
	}

	public void addEdge(int from, int to, Comparable w) {
		data.set(from, to, w);
	}

	public Comparable getEdge(int from, int to) {
		return  data.get(from, to);
	}

	public String toString() {
		return data.toString();
	}
	public Comparable getBySearch(Comparable w){
		return data.getBySearch(w);
	}
	public int getRowSize(){
		return data.getRows();
	}
	public int getColumnSize(){
		return data.getColumns();
	}
}
