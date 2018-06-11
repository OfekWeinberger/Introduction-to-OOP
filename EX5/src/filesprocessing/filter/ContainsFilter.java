package filesprocessing.filter;

import java.io.File;

/**
 * This class implements the contains filter.
 */
public class ContainsFilter implements Filter {

	/**
	 * The value we want to check if the file contains.
	 */
	private final String value;

	/**
	 * The constructor of the contains filter.
	 *
	 * @param value The value we want to check if the file contains.
	 */
	public ContainsFilter(String value) {
		this.value = value;
	}

	/**
	 * This method is the filter for single file.
	 *
	 * @param file The file we want to check if can pass the filter.
	 * @return True if the filter made it through the filter.
	 */
	public boolean filter(File file) {
		return file.getName().contains(value);
	}

}