package edu.ou.cs2334.project5.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The Class NonogramModel.
 * @author Ben Cruickshank
 * @version 1.0
 */
public class NonogramModel {

	/** The Constant DELIMITER. */
	private static final String DELIMITER = " ";
	
	/** The Constant IDX_NUM_ROWS. */
	private static final int IDX_NUM_ROWS = 0;
	
	/** The Constant IDX_NUM_COLS. */
	private static final int IDX_NUM_COLS = 1;

	/** The row clues. */
	private int[][] rowClues;
	
	/** The col clues. */
	private int[][] colClues;
	
	/** The cell states. */
	private CellState[][] cellStates;

	/**
	 * Instantiates a new nonogram model.
	 *
	 * @param rowClues the row clues
	 * @param colClues the col clues
	 */
	public NonogramModel(int[][] rowClues, int[][] colClues) {

		this.rowClues = deepCopy(rowClues);
		this.colClues = deepCopy(colClues);

		cellStates = initCellStates(getNumRows(), getNumCols());
	}

	/**
	 * Instantiates a new nonogram model.
	 *
	 * @param file this is the file that is used as the puzzle
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public NonogramModel(File file) throws IOException {
		// Number of rows and columns
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String header = reader.readLine();
		String[] fields = header.split(DELIMITER);
		int numRows = Integer.parseInt(fields[IDX_NUM_ROWS]);
		int numCols = Integer.parseInt(fields[IDX_NUM_COLS]);

		cellStates = initCellStates(numRows, numCols);

		this.rowClues = readClueLines(reader, numRows);

		this.colClues = readClueLines(reader, numCols);
		// Close reader
		reader.close();
	}

	/**
	 * Instantiates a new nonogram model and reads in a new file.
	 *
	 * @param filename the filename
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public NonogramModel(String filename) throws IOException {
		this(new File(filename));
	}

	/* Helper methods */

	/**
	 * Sets the cell states.
	 *
	 * @param numRows the number of rows
	 * @param numCols the number of cols
	 * @return the cell state[][]
	 */
	// This is implemented for you
	private static CellState[][] initCellStates(int numRows, int numCols) {

		CellState[][] cellStates = new CellState[numRows][numCols];

		for (int rowIdx = 0; rowIdx < numRows; ++rowIdx) {
			for (int colIdx = 0; colIdx < numCols; ++colIdx) {
				cellStates[rowIdx][colIdx] = CellState.EMPTY;
			}
		}

		return cellStates;
	}

	/**
	 * Deep copy of the original double array.
	 *
	 * @param array the array
	 * @return a double array
	 */
	private static int[][] deepCopy(int[][] array) {
		int[][] holder = new int[array.length][];

		for (int i = 0; i < array.length; i++) {
			holder[i] = Arrays.copyOf(array[i], array[i].length);
		}

		return holder;
	}

	/**
	 * Read in the clue lines.
	 *
	 * @param reader that is used to read the clues
	 * @param numLines the number of lines
	 * @return the int[][] the double array of the clues
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static int[][] readClueLines(BufferedReader reader, int numLines) throws IOException {
		
		int[][] clueLines = new int[numLines][];

		for (int lineNum = 0; lineNum < numLines; ++lineNum) {
			
			String line = reader.readLine();

			String[] tokens = line.split(DELIMITER);

			int[] clues = new int[tokens.length];
			
			for (int idx = 0; idx < tokens.length; ++idx) {
				clues[idx] = Integer.parseInt(tokens[idx]);
			}

			// Store the processed clues in the resulting 2D array
			clueLines[lineNum] = clues;
		}

		// Return the result
		return clueLines;
	}

	/**
	 * Gets the number of  cols.
	 *
	 * @return the number of cols
	 */
	public int getNumCols() {
		int num = colClues.length;
		return num;
	}

	/**
	 * Gets the number of  rows.
	 *
	 * @return the number of rows
	 */
	public int getNumRows() {
		int num = rowClues.length;
		return num;
	}

	/**
	 * Gets the row of the clues.
	 *
	 * @return the row of the clues
	 */
	public int[][] getRowClues() {
		return deepCopy(rowClues);
	}

	/**
	 * Gets the col of the clues.
	 *
	 * @return the col of the clues
	 */
	public int[][] getColClues() {
		return deepCopy(colClues);
	}

	/**
	 * Gets the row clue.
	 *
	 * @param rowIdx the row index
	 * @return a copy of the row clue
	 */
	public int[] getRowClue(int rowIdx) {
		return Arrays.copyOf(rowClues[rowIdx], rowClues[rowIdx].length);

	}

	/**
	 * Gets the col of the clue.
	 *
	 * @param colIdx the col index
	 * @return a copy of the col clue
	 */
	public int[] getColClue(int colIdx) {
		return Arrays.copyOf(colClues[colIdx], colClues[colIdx].length);

	}

	/**
	 * Gets the cell state.
	 *
	 * @param rowIdx the row index
	 * @param colIdx the col index
	 * @return the cell state if its filled or empty
	 */
	public CellState getCellState(int rowIdx, int colIdx) {

		return cellStates[rowIdx][colIdx];

	}

	/**
	 * Gets the cell state as boolean.
	 *
	 * @param rowIdx the row index
	 * @param colIdx the col index
	 * @return the cell state as boolean
	 */
	public boolean getCellStateAsBoolean(int rowIdx, int colIdx) {
		return CellState.toBoolean(getCellState(rowIdx, colIdx));
	}

	/**
	 * Project.
	 *
	 * @param cells the cells as a boolean showing filled or not filled
	 * @return the list of states of cells
	 */
	public static List<Integer> project(boolean[] cells) {
		List<Integer> holder = new ArrayList<Integer>();
		Integer counter = 0;
		Integer indexCounter = 0;
		holder.add(indexCounter);

		for (int i = 0; i < cells.length; i++) {

			if (cells[i] == true && i == 0) {
				holder.set(indexCounter, ++counter);
			} else if (cells[i] == true) {
				if (cells[i - 1] == false && holder.get(0) > 0) {
					counter = 1;
					holder.add(counter);
					indexCounter++;
				}

				else {
					holder.set(indexCounter, ++counter);
				}

			}

		}

		return holder;

	}

	/**
	 * Project cell states  of a row.
	 *
	 * @param rowIdx the row index
	 * @return the int[]
	 */
	public int[] projectCellStatesRow(int rowIdx) {
		
		boolean[] temp = new boolean[getNumCols()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = getCellStateAsBoolean(rowIdx, i);
		}
		return project(temp).stream().mapToInt(Integer::intValue).toArray();
	}

	/**
	 * Project cell states of the col.
	 *
	 * @param colIdx the col index of the project
	 * @return the int[] of cell states as an array
	 */
	public int[] projectCellStatesCol(int colIdx) {
		
		boolean[] temp = new boolean[getNumRows()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = getCellStateAsBoolean(i, colIdx);
		}

		return project(temp).stream().mapToInt(Integer::intValue).toArray();
	}

	/**
	 * Checks if is row solved.
	 *
	 * @param rowIdx the row index
	 * @return true, if is row solved
	 */
	public boolean isRowSolved(int rowIdx) {

		return Arrays.equals(getRowClue(rowIdx), projectCellStatesRow(rowIdx));

	}

	/**
	 * Checks if is col solved.
	 *
	 * @param colIdx the col index
	 * @return true, if is col solved
	 */
	public boolean isColSolved(int colIdx) {
		return Arrays.equals(getColClue(colIdx), projectCellStatesCol(colIdx));

	}

	/**
	 * Checks if is solved.
	 *
	 * @return true, if the whole thing is solved
	 */
	public boolean isSolved() {
		int counter = 0;
		for (int i = 0; i < getNumRows(); i++) {
			if (isRowSolved(i) == true) {
				counter++;
			}
		}

		for (int i = 0; i < getNumCols(); i++) {
			if (isColSolved(i) == true) {
				counter++;
			}
		}

		return counter == getNumRows() + getNumCols();
	}

	/**
	 * Sets the cell state.
	 *
	 * @param rowIndex the row index
	 * @param colIndex the col index
	 * @param state the state of a cell
	 * @return true, if successful
	 */
	public boolean setCellState(int rowIndex, int colIndex, CellState state) {
		if (cellStates[rowIndex][colIndex] == state || state == null || isSolved()) {
			return false;
		}

		cellStates[rowIndex][colIndex] = state;
		return true;

	}

	/**
	 * Reset cells the cells in the project.
	 */
	public void resetCells() {
		for (int rowIndex = 0; rowIndex < getNumRows(); ++rowIndex) {
			for (int colIndex = 0; colIndex < getNumCols(); ++colIndex) {
				cellStates[rowIndex][colIndex] = CellState.EMPTY;
			}
		}
	}
}
