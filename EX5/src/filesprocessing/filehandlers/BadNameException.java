package filesprocessing.filehandlers;

/**
 * This exception represents a type I, bad name exception.
 */
public class BadNameException extends TypeOneException {

	/**
	 * Constructs a new exception with the specified line number.
	 *
	 * @param line line where exception was thrown.
	 */
	public BadNameException(int line) {
		super(line);
	}
}
