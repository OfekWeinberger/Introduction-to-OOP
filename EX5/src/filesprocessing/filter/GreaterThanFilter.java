package filesprocessing.filter;

import java.io.File;

/**
 * This class implements the greater than filter.
 */
public class GreaterThanFilter implements Filter {

	/**
	 * This is a conversation ratio between KB to B.
	 */
	private static final int BYTES_TO_KBYTES = 1024;
	/**
	 * The minimum size.
	 */
	private final double minSize;

	/**
	 * The constructor for the greater than filter.
	 *
	 * @param minSize The minimum size.
	 */
	public GreaterThanFilter(double minSize) {
		this.minSize = minSize;
	}

	/**
	 * This method is the filter for single file.
	 *
	 * @param file The file we want to check if can pass the filter.
	 * @return True if the filter made it through the filter.
	 */
	public boolean filter(File file) {
		return file.length() / BYTES_TO_KBYTES > this.minSize;
	}

}
