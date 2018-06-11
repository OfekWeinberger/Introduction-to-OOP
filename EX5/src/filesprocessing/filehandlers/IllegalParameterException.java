package filesprocessing.filehandlers;

/**
 * This exception represents the case where general parameter illegal.
 */
public class IllegalParameterException extends TypeOneException {

	/**
	 * Constructs a new exception with the specified line number.
	 *
	 * @param line line where exception was thrown.
	 */
	public IllegalParameterException(int line) {
		super(line);
	}
}
