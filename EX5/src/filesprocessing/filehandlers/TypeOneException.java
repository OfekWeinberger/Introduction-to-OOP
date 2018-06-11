package filesprocessing.filehandlers;

/**
 * This exception represents the general type I exception.
 */
public class TypeOneException extends Exception {

	/**
	 * Constructs a new exception with the specified line number.
	 *
	 * @param line line where exception was thrown.
	 */
	public TypeOneException(int line) {
		super("Warning in line " + line);
	}
}
