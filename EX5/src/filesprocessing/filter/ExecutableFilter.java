package filesprocessing.filter;

import java.io.File;

/**
 * This class implements the executable filter.
 */
public class ExecutableFilter implements Filter {
	/**
	 * Holds if we want to filter executable or not executable.
	 */
	private final boolean isExecutable;

	/**
	 * The constructor of the executable filter.
	 *
	 * @param isExecutable Holds if we want to filter executable or not executable.
	 */
	public ExecutableFilter(boolean isExecutable) {
		this.isExecutable = isExecutable;
	}

	/**
	 * This method is the filter for single file.
	 *
	 * @param file The file we want to check if can pass the filter.
	 * @return True if the filter made it through the filter.
	 */
	public boolean filter(File file) {
		if (this.isExecutable) {
			return file.canExecute();
		} else {
			return !file.canExecute();
		}
	}

}