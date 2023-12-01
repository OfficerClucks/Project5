
package edu.ou.cs2334.project5.models;


/**
 * The Enum CellState that shows the state of the cell.
 * @author Ben Cruickshank
 * @version 1.0
 */
public enum CellState {

	/** The cell is empty. */
	EMPTY,
	
	/** The cell is filled. */
	FILLED,
	
	/** The cell is marked. */
	MARKED;
	
	/**
	 * To boolean returns true if the cell is filled.
	 * Otherwise it returns false
	 *
	 * @param state is the state of the cell
	 * @return true, if successful and the cell is filled
	 */
	public static boolean toBoolean(CellState state) {
		return state == FILLED;
	}
}
