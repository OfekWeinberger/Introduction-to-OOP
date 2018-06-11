package filesprocessing.filter;

import java.io.File;

/**
 * This class implements the between filter.
 */
public class BetweenFilter implements Filter {

	/**
	 * A conversation ratio between KB to B.
	 */
	private static final int BYTES_TO_KBYTES = 1024;
	/**
	 * The minimum size of file.
	 */
	private final double minSize;
	/**
	 * The maximum size of file.
	 */
	private final double maxSize;

	/**
	 * Constructor for the between filter.
	 *
	 * @param minSize The minimum size of file.
	 * @param maxSize The maximum size of file.
	 */
	public BetweenFilter(double minSize, double maxSize) {
		this.minSize = minSize;
		this.maxSize = maxSize;
	}

	/**
	 * This method is the filter for single file.
	 *
	 * @param file The file we want to check if can pass the filter.
	 * @return True if the filter made it through the filter.
	 */
	public boolean filter(File file) {
		return file.length() / BYTES_TO_KBYTES >= this.minSize &&
				file.length() / BYTES_TO_KBYTES <= this.maxSize;
	}

}