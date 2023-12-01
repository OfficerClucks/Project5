package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import javafx.scene.layout.GridPane;


/**
 * The Class CellGridView.
 */
public class CellGridView extends GridPane {

	/** The Constant STYLE_CLASS. */
	private final static String STYLE_CLASS = "cell-grid-view";

	/** The cell views. */
	private CellView[][] cellViews;

	/**
	 * Instantiates a new cell grid view.
	 *
	 * @param numRows the number of rows
	 * @param numCols the number of cols
	 * @param cellLength the cell length
	 */
	public CellGridView(int numRows, int numCols, int cellLength) {
		initCells(numRows, numCols, cellLength);
		getStyleClass().add(STYLE_CLASS);
	}
	
	/**
	 * Inits the cells.
	 *
	 * @param numRows the number of  rows
	 * @param numCols the number of cols
	 * @param cellLength the length cells 
	 */
	public void initCells(int numRows, int numCols, int cellLength) {
		getChildren().clear();
		this.cellViews = new CellView[numRows][numCols];

		for (int rowIndex = 0; rowIndex < numRows; ++rowIndex) {
			for (int colIndex = 0; colIndex < numCols; ++colIndex) {
				cellViews[rowIndex][colIndex] = new CellView(cellLength);
			}
			
			addRow(rowIndex, cellViews[rowIndex]);
		}
	}
	
	/**
	 * Gets the cell view.
	 *
	 * @param rowIndex the row index
	 * @param colIndex the col index
	 * @return the cell view
	 */
	public CellView getCellView(int rowIndex, int colIndex) {
		return cellViews[rowIndex][colIndex];

	}
	
	/**
	 * Sets the cell state.
	 *
	 * @param rowIndex the row index
	 * @param colIndex the col index
	 * @param state the cell state
	 */
	public void setCellState(int rowIndex, int colIndex, CellState state) {
		cellViews[rowIndex][colIndex].setState(state);
	}
	
}
