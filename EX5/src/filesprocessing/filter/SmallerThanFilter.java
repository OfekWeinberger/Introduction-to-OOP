package filesprocessing.filter;

import java.io.File;

/**
 * This class implements the smaller than filter.
 */
public class SmallerThanFilter implements Filter {

	/**
	 * This is conversation ratio from KB to B.
	 */
	private static final int BYTES_TO_KBYTES = 1024;
	/**
	 * This is the maximum size of file we allow in this filter.
	 */
	private final double maxSize;

	/**
	 * The constructor for the smaller than filter.
	 *
	 * @param maxSize This is the maximum size of file we allow in this filter.
	 */
	public SmallerThanFilter(double maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * This method is the filter for single file.
	 *
	 * @param file The file we want to check if can pass the filter.
	 * @return True if the filter made it through the filter.
	 */
	public boolean filter(File file) {
		return file.length() / BYTES_TO_KBYTES < this.maxSize;
	}

}
