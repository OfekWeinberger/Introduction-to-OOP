package filesprocessing.filter;

import java.io.File;

/**
 * This class implements the suffix filter.
 */
public class SuffixFilter implements Filter {

	/**
	 * The suffix value we search for.
	 */
	private final String value;

	/**
	 * The constructor for the suffix filter.
	 *
	 * @param value The suffix value we search for.
	 */
	public SuffixFilter(String value) {
		this.value = value;
	}

	/**
	 * This method is the filter for single file.
	 *
	 * @param file The file we want to check if can pass the filter.
	 * @return True if the filter made it through the filter.
	 */
	public boolean filter(File file) {
		return file.getName().endsWith(value);
	}

}