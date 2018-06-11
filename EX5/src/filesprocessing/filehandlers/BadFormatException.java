package filesprocessing.filehandlers;

/**
 * This exception represents a general type II exception.
 */
public class BadFormatException extends TypeTwoException {

	/**
	 * Constructs a new exception with the specified detail message.
	 *
	 * @param subSection the type of format error.
	 */
	public BadFormatException(String subSection) {
		super(subSection + " sub-section missing");
	}
}
