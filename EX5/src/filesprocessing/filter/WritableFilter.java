package filesprocessing.filter;

import java.io.File;

/**
 * This class implements the writable filter.
 */
public class WritableFilter implements Filter {

	/**
	 * Holds if we want to filter writable or not writable.
	 */
	private final boolean isWritable;

	/**
	 * The constructor for the writable filter.
	 *
	 * @param isWritable Holds if we want to filter writable or not writable.
	 */
	public WritableFilter(boolean isWritable) {
		this.isWritable = isWritable;
	}

	/**
	 * This method is the filter for single file.
	 *
	 * @param file The file we want to check if can pass the filter.
	 * @return True if the filter made it through the filter.
	 */
	public boolean filter(File file) {
		if (this.isWritable) {
			return file.canWrite();
		} else {
			return !file.canWrite();
		}
	}

}