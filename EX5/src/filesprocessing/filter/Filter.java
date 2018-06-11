package filesprocessing.filter;

import java.io.File;
import java.util.ArrayList;

/**
 * The interface every filter should implement.
 */
public interface Filter {

	/**
	 * This method is the filter for single file.
	 *
	 * @param file The file we want to check if can pass the filter.
	 * @return True if the filter made it through the filter.
	 */
	boolean filter(File file);

	/**
	 * This method does the actual filtering of the files, default implementation should be okay for every
	 * type of filter.
	 *
	 * @param files The list of files we want to filter.
	 */
	default void filter(ArrayList<File> files) {
		files.removeIf(file -> !filter(file));
	}

}