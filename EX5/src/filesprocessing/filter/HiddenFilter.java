package filesprocessing.filter;

import java.io.File;

/**
 * This class implements the hidden filter.
 */
public class HiddenFilter implements Filter {

	/**
	 * Holds if we want to filter hidden or not hidden.
	 */
	private final boolean isHidden;

	/**
	 * The constructor for the hidden filter.
	 *
	 * @param isHidden Holds if we want to filter hidden or not hidden.
	 */
	public HiddenFilter(boolean isHidden) {
		this.isHidden = isHidden;
	}

	/**
	 * This method is the filter for single file.
	 *
	 * @param file The file we want to check if can pass the filter.
	 * @return True if the filter made it through the filter.
	 */
	public boolean filter(File file) {
		if (this.isHidden) {
			return file.isHidden();
		} else {
			return !file.isHidden();
		}
	}

}