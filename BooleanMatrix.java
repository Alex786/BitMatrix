/**
 * An m x n matrix of boolean values. When adding a value at position (r,c),
 * if r >= m or c >= n, then new rows and/or columns are automatically
 * added, filled with the default value of false, and then the new value
 * is stored.
 * 
 * @author Dr. Lillis
 */
public interface BooleanMatrix {

    /**
     * Returns the number of rows.
     * 
     * @return the number of rows
     */
    int getNumberRows();

    /**
     * Returns the number of columns.
     * 
     * @return the number of columns
     */
    int getNumberCols();

    /**
     * Sets the element at the specified position to true. Adds new rows and
     * columns if needed, filled with false. This method should call the
     * {@link #put(int row, int col, boolean value)} method.
     * 
     * @param row row index of the element to be changed
     * @param col column index of the element to be changed
     * @throws IndexOutOfBoundsException if either index is less than zero (row &lt;
     *                                   0 || col &lt; 0)
     */
    void set(int row, int col) throws IndexOutOfBoundsException;

    /**
     * Sets the element at the specified position to false. Adds new rows and
     * columns if needed, filled with false. This method should call the
     * {@link #put(int row, int col, boolean value)} method.
     * 
     * @param row row index of the element to be changed
     * @param col column index of the element to be changed
     * @throws IndexOutOfBoundsException if either index is less than zero (row &lt;
     *                                   0 || col &lt; 0)
     */
    void clear(int row, int col) throws IndexOutOfBoundsException;

    /**
     * Stores the element at the specified position to the specified value.
     * Adds new rows and columns if needed, filled with false.
     * 
     * @param row   row index of the element to be changed
     * @param col   column index of the element to be changed
     * @param value value to be stored at the specified position
     * @throws IndexOutOfBoundsException if either index is less than zero (row &lt;
     *                                   0 || col &lt; 0)
     */
    void put(int row, int col, boolean value) throws IndexOutOfBoundsException;

    /**
     * Changes the element at the specified position. If the current value is
     * true it is changed to false. If the current value is false it is
     * changed to true.
     * 
     * @param row row index of the element to be changed
     * @param col column index of the element to be changed
     * @throws IndexOutOfBoundsException if either index is out of range (row &lt; 0
     *                                   || col &lt; 0 || row &gt;= getNumberRows()
     *                                   || col &gt;= getNumberCols())
     */
    void toggle(int row, int col) throws IndexOutOfBoundsException;

    /**
     * Sets all elements to true. This method should call the
     * {@link #putAll(boolean value)} method.
     * 
     */
    void setAll();

    /**
     * Clears all elements to false. This method should call the
     * {@link #putAll(boolean value)} method.
     */
    void clearAll();

    /**
     * Sets all elements to the specified value.
     * 
     * @param value value to which all elements will be set
     */
    void putAll(boolean value);

    /**
     * Resets the Boolean matrix to a 1 x 1 matrix that contains a single
     * false value.
     */
    void reset();

    /**
     * Returns the element at the specified position.
     * 
     * @param row row index of the element to return
     * @param col column index of the element to return
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if either index is out of range (row &lt; 0
     *                                   || col &lt; 0 || row &gt;= getNumberRows()
     *                                   || col &gt;= getNumberCols())
     */
    boolean get(int row, int col) throws IndexOutOfBoundsException;

    /**
     * Returns the indices of the elements whose value is true. The indices are
     * returned in a P x 2 array of integers, where P is the number of elements
     * with a value of true. Each row in the returned array contains the row and
     * column index of an element with a value of true. The returned array is
     * sorted in increasing order by row and column. This method should call the
     * {@link #getPositions(boolean value)} method.
     * 
     * @return the indices of all elements whose value is true
     */
    int[][] getTruePositions();

    /**
     * Returns the indices of the elements whose value is false. The indices are
     * returned in a P x 2 array of integers, where P is the number of elements
     * with a value of false. Each row in the returned array contains the row and
     * column index of an element with a value of false. The returned array is
     * sorted in increasing order by row and column. This method should call the
     * {@link #getPositions(boolean value)} method.
     * 
     * @return the indices of all elements whose value is false
     */
    int[][] getFalsePositions();

    /**
     * Returns the indices of the elements whose value matches the parameter. The
     * indices are returned in a P x 2 array of integers, where P is the number of
     * elements whose value matches the parameter. Each row in the returned array
     * contains the row and column index of an element whose value matches the
     * parameter. The returned array is sorted in increasing order by row and
     * column. This method should call the {@link #getPositions(boolean value)}
     * method.
     * 
     * @return the indices of all elements whose value matches the parameter
     * @param value the value used to determine which pairs of indices to return
     */
    int[][] getPositions(boolean value);

    /**
     * Returns the number of elements whose value is true.
     * 
     * @return the number of elements whose value is true
     */
    int getNumberTrueValues();

    /**
     * Returns the number of elements whose value is false.
     * 
     * @return the number of elements whose value is false
     */
    int getNumberFalseValues();

    /**
     * Returns the number of elements with the specified value.
     * 
     * @return the number of elements with the specified value
     * @param value the value whose count is returned
     */
    int getNumberValues(boolean value);

    /**
     * Returns a string showing: 1) the number of rows, 2) the number of columns,
     * 3) the number of true values, and 4) the number of false values.
     * 
     * @return a string showing: 1) the number of rows, 2) the number of columns,
     *         3) the number of true values, and 4) the number of false values.
     */
    @Override
    String toString();

}
