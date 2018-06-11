package filesprocessing.filter;

import java.io.File;

/**
 * This class implements the all filter.
 */
public class AllFilter implements Filter {

	/**
	 * This method is the filter for single file.
	 *
	 * @param file The file we want to check if can pass the filter.
	 * @return True if the filter made it through the filter.
	 */
	public boolean filter(File file) {
		return true;
	}

}
