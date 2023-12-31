package edu.ou.cs2334.project5.views.clues;
import javafx.geometry.Orientation;

// TODO: Auto-generated Javadoc
/**
 * The Class VerticalClueView.
 */
public class VerticalClueView extends AbstractOrientedClueView {

	/**
	 * The style class for this view.
	 */
	private static final String STYLE_CLASS = "vertical-clue-view";

	/**
	 * Constructs a VerticalClueView using the given parameter values.
	 *
	 * @param colClue an array of column clues
	 * @param cellLength the length of a cell
	 * @param height the height
	 */
	public VerticalClueView(int[] colClue, int cellLength, int height) {
		super(Orientation.VERTICAL, STYLE_CLASS, colClue, cellLength, height);
		setMaxHeight(height * cellLength);
	}

}
