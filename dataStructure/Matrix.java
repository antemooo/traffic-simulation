package dataStructure;

public class Matrix {

	private int mRows;
	private int mColumns;
	private Comparable[][] mMatrix;

	public Matrix(int rows, int columns) {
		this.mRows = rows;
		this.mColumns = columns;
		this.mMatrix = new Comparable[mRows][mColumns];
		for (int i = 0; i < mRows; i++) {
			for (int j = 0; j < mColumns; j++)
				mMatrix[i][j] = 0;
		}
	}

	public void set(int i, int j, Comparable weight) {
		mMatrix[i][j] = weight;
	}

	public Comparable get(int i, int j) {
		return mMatrix[i][j];
	}

	public String toString() {
		String s = "{ \n";
		for (int i = 0; i < mRows; i++) {
			for (int j = 0; j < mColumns; j++) {
				s += mMatrix[i][j].toString();
				s += " ";
			}
			s += "\n";
		}
		s += "}";
		return s;
	}

	public Comparable getBySearch(Comparable w) {
		for (int i = 0; i < mRows; i++) {
			for (int j = 0; j < mColumns; j++) {
				if (mMatrix[i][j].compareTo(w) == 0) {
					return mMatrix[i][j];
				}
			}
		}
		return null;
	}

	public int getRows() {
		return mRows;
	}

	public int getColumns() {
		return mColumns;
	}

}