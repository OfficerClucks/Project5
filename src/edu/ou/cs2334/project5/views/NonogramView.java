package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.views.clues.LeftCluesView;
import edu.ou.cs2334.project5.views.clues.TopCluesView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


/**
 * The Class NonogramView.
 * @author Ben Cruickshank
 * @version 1.0
 * help from henery 
 */
public class NonogramView extends BorderPane {

	/** The style class. */
	private static String STYLE_CLASS = "nonogram-view";

	/** The solved style class. */
	private static String SOLVED_STYLE_CLASS = "nonogram-view-solved";

	/** The left clues view. */
	private LeftCluesView leftCluesView;

	/** The top clues view. */
	private TopCluesView topCluesView;

	/** The cell grid view. */
	private CellGridView cellGridView;

	/** The button H box. */
	private HBox buttonHBox;

	/** The load btn. */
	private Button loadBtn;

	/** The reset btn. */
	private Button resetBtn;

	/**
	 * Instantiates a new nonogram view.
	 */
	public NonogramView() {
		this.getStyleClass().add(STYLE_CLASS);
	}

	/**
	 * Initialize.
	 *
	 * @param rowClues the row clues
	 * @param colClues the col clues
	 * @param cellLength the cell length
	 */
	public void initialize(int[][] rowClues, int[][] colClues, int cellLength) {
		this.leftCluesView = new LeftCluesView(rowClues, cellLength, findMaxLength(rowClues));
		this.topCluesView = new TopCluesView(colClues, cellLength, findMaxLength(colClues));
		this.cellGridView = new CellGridView(rowClues.length, colClues.length, cellLength);

		setLeft(leftCluesView);

		setTop(topCluesView);
		setAlignment(topCluesView, Pos.TOP_RIGHT);

		setCenter(cellGridView);

		initButtonHBox();

		setBottom(buttonHBox);

	}

	/**
	 * Find max length.
	 *
	 * @param array the array
	 * @return the int
	 */
	public int findMaxLength(int[][] array) {
		int maxLength = array[0].length;
		for (int i = 0; i < array.length; i++) {
			if (maxLength < array[i].length) {
				maxLength = array[i].length;
			}
		}
		
		return maxLength;
	}

	/**
	 * Inits the button H box.
	 */
	public void initButtonHBox() {
		this.buttonHBox = new HBox();
		buttonHBox.setAlignment(Pos.CENTER);
		loadBtn = new Button("Load");
		resetBtn = new Button("Reset");
		
		buttonHBox.getChildren().addAll(loadBtn, resetBtn);
	}

	/**
	 * Gets the cell view.
	 *
	 * @param rowIndex the row index
	 * @param colIndex the col index
	 * @return the cell view
	 */
	public CellView getCellView(int rowIndex, int colIndex) {
		return cellGridView.getCellView(rowIndex, colIndex);

	}

	/**
	 * Sets the cell state.
	 *
	 * @param rowIndex the row index
	 * @param colIndex the col index
	 * @param state the state
	 */
	public void setCellState(int rowIndex, int colIndex, CellState state) {
		cellGridView.setCellState(rowIndex, colIndex, state);

	}

	/**
	 * Sets the row clue state.
	 *
	 * @param rowIndex the row index
	 * @param solved the solved
	 */
	public void setRowClueState(int rowIndex, boolean solved) {
		leftCluesView.setState(rowIndex, solved);

	}

	/**
	 * Sets the col clue state.
	 *
	 * @param colIndex the col index
	 * @param solved the solved
	 */
	public void setColClueState(int colIndex, boolean solved) {
		topCluesView.setState(colIndex, solved);

	}

	/**
	 * Gets the load button.
	 *
	 * @return the load button
	 */
	public Button getLoadButton() {
		return loadBtn;
	}

	/**
	 * Gets the reset button.
	 *
	 * @return the reset button
	 */
	public Button getResetButton() {
		return resetBtn;
	}

	/**
	 * Show victor alert.
	 */
	public void showVictorAlert() {
		Alert win = new Alert(AlertType.INFORMATION);
		win.setHeaderText("Congratulations!");
		win.setTitle("Puzzle Solved");
		win.setContentText("You Win!");
		win.show();
	}

	/**
	 * Sets the puzzle state.
	 *
	 * @param solved the new puzzle state
	 */
	public void setPuzzleState(boolean solved) {
		if (solved == true) {
			getStyleClass().add(SOLVED_STYLE_CLASS);
		}
		else {
			getStyleClass().removeAll(SOLVED_STYLE_CLASS);
		}
	}

}
