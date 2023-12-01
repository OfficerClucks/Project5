package edu.ou.cs2334.project5;

import edu.ou.cs2334.project5.presenters.NonogramPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main class that launches the GUI.
 * 
 * @author Ben Cruickshank
 * @version 1.0
 */
public class Main extends Application {

	/** The Constant IDX_CELL_SIZE. */
	private final static int IDX_CELL_SIZE = 0;

	/** The Constant DEFAULT_CELL_SIZE. */
	private final static int DEFAULT_CELL_SIZE = 30;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Start.
	 *
	 * @param primaryState the primary state
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage primaryState) throws Exception {

		NonogramPresenter presenter;
		if (getParameters().getUnnamed().isEmpty()) {
			presenter = new NonogramPresenter(DEFAULT_CELL_SIZE);
		}

		else {
			presenter = new NonogramPresenter(Integer.parseInt(getParameters().getUnnamed().get(IDX_CELL_SIZE)));
		}

		Scene scene = new Scene(presenter.getPane());
		scene.getStylesheets().add("/style.css");

		primaryState.setScene(scene);
		primaryState.setTitle("Nonogram Maker");
		primaryState.setResizable(false);
		primaryState.show();

	}

}