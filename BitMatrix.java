import java.util.ArrayList;

public class BitMatrix implements BooleanMatrix {
	// The array that stores the logical Boolean value. Each array element represents 32 True/False values.
	private int[][] cells;

	// All bits are false (0)
	private static final int MASK_ALL_F = 0x00000000;

	// All bits are true (1)
	private static final int MASK_ALL_T = 0xFFFFFFFF;

	// High bit is false (0), all others are true (1)
	private static final int MASK_HIGH_F = 0x7FFFFFFF;

	// High bit is true (1), all others are false (0)
	private static final int MASK_HIGH_T = 0x80000000;

	// Low bit is true (1), all others are false (0)
	private static final int MASK_LOW_T = 0x00000001;

	// The number of columns in the Boolean Matrix. This will likely be different than the number of columns in the array representation.
	private int numCols;

	// The number of rows in the Boolean Matrix.
	private int numRows;

	public BitMatrix() {
		// Creates a 1 x 1 matrix with a single false value. This constructor calls the reset() method.
		reset();
	}

	public final void reset() {
		// Resets the Boolean matrix to a 1 x 1 matrix that contains a single false value.
		cells = new int[1][1];
		numRows = 1;
		numCols = 1;
		cells[0][0] = MASK_ALL_F;
	}

	public int getNumberRows() {
		// Returns the number of rows.
		return numRows;
	}

	public int getNumberCols() {
		// Returns the number of columns.
		return numCols;
	}

	public void set(int row, int pos) {
		// Sets the element at the specified position to true. Adds new rows and columns if needed, filled with false. This method should call the BooleanMatrix.put(int row, int col, boolean value) method.
		put(row, pos, true);
	}

	public void clear(int row, int pos) {
		// Sets the element at the specified position to false. Adds new rows and columns if needed, filled with false. This method should call the BooleanMatrix.put(int row, int col, boolean value) method.
		put(row, pos, false);
	}

	public void put(int row, int col, boolean value) {
		// Stores the element at the specified position to the specified value. Adds new rows and columns if needed, filled with false.
		if (row < 0 || col < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		// Expand matrix if needed
		while (row >= numRows) {
			doubleNumCellRows();
		}
		while (col >= numCols) {
			doubleNumCellCols();
		}
		
		// Calculate array indices and bit position
		int arrayRow = row;
		int arrayCol = col / 32;
		int bitPos = col % 32;
		
		// Create mask for the specific bit
		int mask = MASK_HIGH_T >>> bitPos;
		
		if (value) {
			cells[arrayRow][arrayCol] |= mask;  // Set bit to 1
		} else {
			cells[arrayRow][arrayCol] &= ~mask; // Set bit to 0
		}
	}

	private void doubleNumCellRows() {
		// Doubles the number of rows in the array representing the Boolean Matrix.
		int[][] newCells = new int[numRows + 1][];
		for (int i = 0; i < cells.length; i++) {
			newCells[i] = cells[i].clone();
		}
		for (int i = cells.length; i < newCells.length; i++) {
			newCells[i] = new int[(numCols + 31) / 32];
		}
		cells = newCells;
		numRows = cells.length;
	}

	private void doubleNumCellCols() {
		// Doubles the number of columns in the array representing the Boolean Matrix.
		int newColArraySize = ((numCols + 1) + 31) / 32;
		int[][] newCells = new int[cells.length][newColArraySize];
		for (int i = 0; i < cells.length; i++) {
			System.arraycopy(cells[i], 0, newCells[i], 0, cells[i].length);
		}
		cells = newCells;
		numCols++;
	}

	public void toggle(int row, int pos) {
		// Changes the element at the specified position. If the current value is true it is changed to false. If the current value is false it is changed to true.

		if (row < 0 || pos < 0 || row >= numRows || pos >= numCols) {
			throw new IndexOutOfBoundsException();
		}
		
		int arrayCol = pos / 32;
		int bitPos = pos % 32;
		
		// Use MASK_HIGH_F to create a mask by rotating it right
		int mask = (MASK_HIGH_F >>> (30 - bitPos)) & MASK_LOW_T;
		mask = mask << (31 - bitPos);
		
		cells[row][arrayCol] ^= mask;  // XOR operation to toggle the bit
	}

	public void setAll() {
		// Sets all elements to true. This method should call the BooleanMatrix.putAll(boolean value) method.
		putAll(true);
	}

	public void clearAll() {
		// Clears all elements to false. This method should call the BooleanMatrix.putAll(boolean value) method.
		putAll(false);
	}

	public void putAll(boolean value) {
		// Sets all elements to the specified value.
		int mask = value ? MASK_ALL_T : MASK_ALL_F;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = mask;
			}
		}
	}

	public boolean get(int row, int pos) {
		// Returns the element at the specified position.
		if (row < 0 || pos < 0 || row >= numRows || pos >= numCols) {
			throw new IndexOutOfBoundsException();
		}
		
		int arrayCol = pos / 32;
		int bitPos = pos % 32;
		int mask = MASK_HIGH_T >>> bitPos;
		
		return (cells[row][arrayCol] & mask) != 0;
	}

	public int[][] getTruePositions() {
		// Returns the indices of the elements whose value is true. The indices are returned in a P x 2 array of integers, where P is the number of elements with a value of true. Each row in the returned array contains the row and column index of an element with a value of true. The returned array is sorted in increasing order by row and column. This method should call the BooleanMatrix.getPositions(boolean value) method.
		return getPositions(true);
	}

	public int[][] getFalsePositions() {
		// Returns the indices of the elements whose value is false. The indices are returned in a P x 2 array of integers, where P is the number of elements with a value of false. Each row in the returned array contains the row and column index of an element with a value of false. The returned array is sorted in increasing order by row and column. This method should call the BooleanMatrix.getPositions(boolean value) method.
		return getPositions(false);
	}

	public int[][] getPositions(boolean value) {
		// Returns the indices of the elements whose value matches the parameter. The indices are returned in a P x 2 array of integers, where P is the number of elements whose value matches the parameter. Each row in the returned array contains the row and column index of an element whose value matches the parameter. The returned array is sorted in increasing order by row and column. This method should call the BooleanMatrix.getPositions(boolean value) method.
		ArrayList<int[]> positions = new ArrayList<>();
    
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (get(i, j) == value) {
					positions.add(new int[]{i, j});
				}
			}
		}
		
		int[][] result = new int[positions.size()][2];
		for (int i = 0; i < positions.size(); i++) {
			result[i] = positions.get(i);
		}
		
		return result;
	}
	public int getNumberTrueValues() {
		// Returns the number of elements whose value is true.
		return getNumberValues(true);
	}

	public int getNumberFalseValues() {
		// Returns the number of elements whose value is false.
		return getNumberValues(false);
	}

	public int getNumberValues(boolean value) {
		// Returns the number of elements with the specified value.
		int count = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (get(i, j) == value) {
					count++;
				}
			}
		}
		return count;
	}

	public String toString() {
		// Returns a string showing: 1) the number of rows, 2) the number of columns, 3) the number of true values, and 4) the number of false values.
		return String.format("Rows: %d, Columns: %d, True Values: %d, False Values: %d", numRows, numCols, getNumberTrueValues(), getNumberFalseValues());
	}
}