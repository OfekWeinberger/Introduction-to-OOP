package filesprocessing.filter;

import java.io.File;

/**
 * This class implements the file filter.
 */
public class FileFilter implements Filter {

	/**
	 * The file name we want to find.
	 */
	private final String fileName;

	/**
	 * The constructor for the file filter.
	 *
	 * @param fileName: The file name we want to find.
	 */
	public FileFilter(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * This method is the filter for single file.
	 *
	 * @param file The file we want to check if can pass the filter.
	 * @return True if the filter made it through the filter.
	 */
	public boolean filter(File file) {
		return file.getName().equals(fileName);
	}

}
