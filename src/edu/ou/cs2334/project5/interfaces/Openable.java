package edu.ou.cs2334.project5.interfaces;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Interface Openable.
 *
 * @author Ben Cruickshank
 * 
 *         creates a method used for making a interface for opening files.
 */
public interface Openable {
	
	/**
	 * makes it so u can open the file.
	 *
	 * @param file the file
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void open(File file) throws FileNotFoundException, IOException;
}