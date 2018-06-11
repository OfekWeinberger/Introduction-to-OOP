package filesprocessing.filehandlers;

/**
 * This exception represents the general type I exception.
 */
public class TypeTwoException extends Exception {

	/**
	 * Constructs a new exception with the specified detail message.
	 *
	 * @param message the detail message, should be informative.
	 */
	public TypeTwoException(String message) {
		super("ERROR: " + message);
	}
}