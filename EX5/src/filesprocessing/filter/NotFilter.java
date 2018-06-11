package filesprocessing.filter;

import java.io.File;

/**
 * This class implements the not filter.
 */
public class NotFilter implements Filter {

	/**
	 * The filter we want to make not for - this class inverts the output of this filter.
	 */
	private Filter filter;

	/**
	 * The constructor for the not filter.
	 *
	 * @param filter: The filter we want to make not for - this class inverts the output of this filter.
	 */
	public NotFilter(Filter filter) {
		this.filter = filter;
	}

	/**
	 * This method is the filter for single file.
	 *
	 * @param file The file we want to check if can pass the filter.
	 * @return True if the filter made it through the filter.
	 */
	public boolean filter(File file) {
		return !this.filter.filter(file);
	}

}