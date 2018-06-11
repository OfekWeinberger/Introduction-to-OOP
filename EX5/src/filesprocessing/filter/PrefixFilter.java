package filesprocessing.filter;

import java.io.File;

/**
 * This class implements the prefix filter.
 */
public class PrefixFilter implements Filter {
	/**
	 * The prefix value we search for.
	 */
	private final String value;

	/**
	 * The constructor for the prefix filter.
	 *
	 * @param value The prefix value we search for.
	 */
	public PrefixFilter(String value) {
		this.value = value;
	}

	/**
	 * This method is the filter for single file.
	 *
	 * @param file The file we want to check if can pass the filter.
	 * @return True if the filter made it through the filter.
	 */
	public boolean filter(File file) {
		return file.getName().startsWith(value);
	}

}