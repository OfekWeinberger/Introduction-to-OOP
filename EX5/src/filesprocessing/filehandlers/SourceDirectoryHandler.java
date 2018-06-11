package filesprocessing.filehandlers;

import java.io.File;
import java.util.ArrayList;

/**
 * This class is handling the reading of the source directory content.
 */
public class SourceDirectoryHandler {

	/**
	 * The list of files in the source directory.
	 */
	private ArrayList<File> files;

	/**
	 * Creates new SourceDirectoryHandler, and lists the files in it (only files).
	 *
	 * @param sourceDirectory The path of the source directory, absolute or relative.
	 */
	public SourceDirectoryHandler(String sourceDirectory) {
		File directory = new File(sourceDirectory);
		// make sure it is a directory
		if (directory.isDirectory()) {
			files = new ArrayList<>();
			File[] listFiles = directory.listFiles();
			// add all the files in the directory to the file list
			if (listFiles != null)
				for (File file : directory.listFiles())
					if (!file.isDirectory())
						files.add(file);
		} else
			throw new IllegalArgumentException("ERROR: The first parameter should be a directory");
	}

	public ArrayList<File> getFiles() {
		return new ArrayList<>(files);
	}
}
