package edu.ou.cs2334.project5.handlers;

import javafx.stage.FileChooser;
import javafx.stage.Window;



/**
 * 
 * The Class AbstractBaseHandler.
 * @author Ben Cruickshank
 * @version 1.0
 */
public abstract class AbstractBaseHandler {

	/** The window. */
	protected Window window;

	/** The file chooser. */
	protected FileChooser fileChooser;

	/**
	 * Instantiates a new abstract base handler.
	 *
	 * @param window the window
	 * @param fileChooser the file chooser to import a file
	 */
	protected AbstractBaseHandler(Window window, FileChooser fileChooser) {

		this.window = window;
		this.fileChooser = fileChooser;

	}
}
