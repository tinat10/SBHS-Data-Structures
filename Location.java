public class Location {

	private int row, col;

	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getR() {
		return row;
	}

	public int getC() {
		return col;
	}

	public void incR(int x) {
		row+=x;
	}

	public void incC(int x) {
		col+=x;
	}

	public void set (int newR, int newC) {
		row = newR;
		col = newC;
	}

	public boolean equals (Location other) {
		if (row == other.getR() && col == other.getC())
			return true;
		return false;
	}

	public boolean equals (int r, int c) {
		if (row == r && col == c)
			return true;
		return false;
	}

	public String toString() {
		return "[" + row + ", " + col + "]";
	}

}

/*

getR() - returns the row
getC() - returns the col
incR(int x) - changes r by x
incC(int x) - changes c by x
set(int newR, int newC) - sets r and c to new values
equals(Location other) - returns true if same row and same col

Optional:
equals(int r, int c)- returns true if row and column match object attributes
toString() - prints formatted row and col as an ordered pair [used for debugging]


*/