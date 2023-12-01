package edu.ou.cs2334.project5.presenters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ou.cs2334.project5.handlers.OpenHandler;
import edu.ou.cs2334.project5.interfaces.Openable;
import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.models.NonogramModel;
import edu.ou.cs2334.project5.views.NonogramView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * The Class NonogramPresenter.
 *
 * @author Ben Cruickshank
 * @version 1.0
 * Help from henery
 */
public class NonogramPresenter implements Openable {

	/** The view of GUI that golds the CellViews. */
	private NonogramView view;
	
	/** The model that holds the cell states. */
	private NonogramModel model;
	
	/** The cell length. */
	private int cellLength;
	
	
	/** The Constant DEFAULT_PUZZLE. */
	private final static String DEFAULT_PUZZLE = "puzzles/space-invader.txt";

	 
	/**
	 * Instantiates a new nonogram presenter.
	 *
	 * @param cellLength the cell length
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public NonogramPresenter(int cellLength) throws IOException {
		
		this.model = new NonogramModel(DEFAULT_PUZZLE);
		this.view = new NonogramView();
		this.cellLength = cellLength;
		
		initializePresenter();
		
	}
	
	
	/**
	 * Initialize presenter.
	 */
	private void initializePresenter() {
		initializeView();
		bindCellViews();
		synchronize();
		configureButtons();
	}
	
	
	/**
	 * Initialize view.
	 */
	public void initializeView() {
		
		view.initialize(model.getRowClues(), model.getColClues(), cellLength);
		
		if (getWindow() != null) {
			getWindow().sizeToScene();
		}
	}
	
	
	/**
	 * Bind cell views.
	 */
	public void bindCellViews() {
		
		int numCols = model.getNumCols();
		int numRows = model.getNumRows();		

		for (int rowIndex = 0; rowIndex < numRows; ++rowIndex) {
			
			for (int colIndex = 0; colIndex < numCols; ++colIndex) {
				
				final int row = rowIndex;
				final int col = colIndex;
				
				view.getCellView(rowIndex, colIndex).setOnMouseClicked(new EventHandler<MouseEvent>() {
					
					@Override
					public void handle(MouseEvent mouseEvent) {
				        if(mouseEvent.getButton() == MouseButton.PRIMARY) {
				        	handleLeftClick(row, col);
				        }
				        else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
				        	handleRightClick(row, col);
				        }
						
					}
					
				});
			}
			
		}
		
	}
	
	
	/**
	 * Handle left click.
	 *
	 * @param rowIndex the row index
	 * @param colIndex the col index
	 */
	public void handleLeftClick(int rowIndex, int colIndex) {
		if (model.getCellState(rowIndex, colIndex) == CellState.EMPTY || model.getCellState(rowIndex, colIndex) == CellState.MARKED) {
			updateCellState(rowIndex, colIndex, CellState.FILLED);
		}
		else if (model.getCellState(rowIndex, colIndex) == CellState.FILLED) {
			updateCellState(rowIndex, colIndex, CellState.EMPTY);
		}
	}
	
	
	/**
	 * Handle right click.
	 *
	 * @param rowIndex the row index
	 * @param colIndex the col index
	 */
	public void handleRightClick(int rowIndex, int colIndex) {
		if (model.getCellState(rowIndex, colIndex) == CellState.EMPTY || model.getCellState(rowIndex, colIndex) == CellState.FILLED) {
			updateCellState(rowIndex, colIndex, CellState.MARKED);
		}
		else if (model.getCellState(rowIndex, colIndex) == CellState.MARKED) {
			updateCellState(rowIndex, colIndex, CellState.EMPTY);
		}
	}
	
	
	/**
	 * Update cell state.
	 *
	 * @param rowIndex the row index
	 * @param colIndex the col index
	 * @param state the state
	 */
	public void updateCellState(int rowIndex, int colIndex, CellState state) {
		
		if (model.setCellState(rowIndex, colIndex, state)) {
			view.setCellState(rowIndex, colIndex, state);
			view.setRowClueState(rowIndex, model.isRowSolved(rowIndex));
			view.setColClueState(colIndex, model.isColSolved(colIndex));
		}
		
		if (model.isSolved()) {
			processVictory();
		}
	}
	
	
	/**
	 * Process victory.
	 */
	public void processVictory() {
		synchronize();
		removeCellViewMarks();
		view.showVictorAlert();
	}
	
	
	/**
	 * Removes the cell view marks.
	 */
	public void removeCellViewMarks() {
		
		int numCols = model.getNumCols();
		int numRows = model.getNumRows();
		
		for (int rowIndex = 0; rowIndex < numRows; ++rowIndex) {
			
			for (int colIndex = 0; colIndex < numCols; ++colIndex) {
				
				if (model.getCellState(rowIndex, colIndex) == CellState.MARKED) {
					view.setCellState(rowIndex, colIndex, CellState.EMPTY);
				}
			}
			
		}

					
	}
	
	
	/**
	 * Synchronize.
	 */
	public void synchronize(){
		
		int numCols = model.getNumCols();
		int numRows = model.getNumRows();
		
		for (int rowIndex = 0; rowIndex < numRows; ++rowIndex) {
			
			for (int colIndex = 0; colIndex < numCols; ++colIndex) {
				
				view.setCellState(rowIndex, colIndex, model.getCellState(rowIndex, colIndex));
				view.setRowClueState(rowIndex, model.isRowSolved(rowIndex));
				view.setColClueState(colIndex, model.isColSolved(colIndex));
				view.setPuzzleState(model.isSolved());
			}
			
		}
		

	}

	
	/**
	 * Configure buttons.
	 */
	public void configureButtons() {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.setInitialDirectory(new File("."));
		view.getLoadButton().setOnAction(new OpenHandler(getWindow(), fileChooser, this));
		
		view.getResetButton().setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				resetPuzzle();
			}
			
		});
		
		
	}
	
	
	/**
	 * Reset puzzle.
	 */
	public void resetPuzzle() {
		model.resetCells();
		synchronize();
	}
	
	
	/**
	 * Gets the pane.
	 *
	 * @return the pane
	 */
	public Pane getPane() {
		return view;
	}
	
	
		/**
		 * Gets the window.
		 *
		 * @return the window
		 */
		private Window getWindow() {
		try {
			return view.getScene().getWindow();
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * Open.
	 *
	 * @param file the file
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public void open(File file) throws FileNotFoundException, IOException {
		model = new NonogramModel(file);
		initializePresenter();
	}

}