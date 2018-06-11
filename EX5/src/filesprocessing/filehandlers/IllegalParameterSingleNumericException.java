package filesprocessing.filehandlers;

/**
 * This exception represents the case there are single numeric parameter with illegal format (e.g. #-1
 * instead of #1).
 */
public class IllegalParameterSingleNumericException extends IllegalParameterException {

	/**
	 * Constructs a new exception with the specified line number.
	 *
	 * @param line line where exception was thrown.
	 */
	public IllegalParameterSingleNumericException(int line) {
		super(line);
	}
}
