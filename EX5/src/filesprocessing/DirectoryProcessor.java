package filesprocessing;

import filesprocessing.filehandlers.CommandFileHandler;
import filesprocessing.filehandlers.SourceDirectoryHandler;
import filesprocessing.filehandlers.TypeTwoException;
import filesprocessing.section.Section;
import filesprocessing.section.SectionFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is the main class of the exercise, given arguments are source directory and command file.
 */
public class DirectoryProcessor {
	/**
	 * Int marker to mark source directory.
	 */
	private static final int SOURCE_DIR = 0;
	/**
	 * Int marker to mark command file.
	 */
	private static final int COMMAND_FILE = 1;

	/**
	 * The main method of the program, will display the files in source directory according to command file.
	 *
	 * @param args source directory path and command file path (both relative or absolute).
	 */
	public static void main(String[] args) {
		try {
			checkArgs(args);
			String sourceDirectory = args[SOURCE_DIR];
			String commandFile = args[COMMAND_FILE];

			CommandFileHandler commandHandler = new CommandFileHandler(commandFile);
			SourceDirectoryHandler sourceHandler = new SourceDirectoryHandler(sourceDirectory);

			Section[] sections = SectionFactory.createSectionArray(commandHandler.getCouples());

			for (Section section : sections) {
				ArrayList<File> files = sourceHandler.getFiles();
				section.filterAndOrder(files);
				for (Exception e : section.getExceptions())
					System.err.println(e.getMessage());
				for (File f : files)
					System.out.println(f.getName());
			}
		} catch (TypeTwoException | IllegalArgumentException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println("ERROR: " + e.getMessage());
		}
	}

	// this method will make sure there are exactly two arguments otherwise throws relevant exception.
	private static void checkArgs(String[] args) throws IllegalArgumentException {
		if (args.length != 2)
			throw new IllegalArgumentException("ERROR: Illegal program arguments, should be " +
					"\"sourceDirectory commandFile\"");
	}
}


