package filesprocessing.filehandlers;

/**
 * This exception represents the case there are multiple numeric parameter with illegal format (e.g. #12#3
 * instead of #3#12).
 */
public class IllegalParameterMultipleNumericException extends IllegalParameterException {

	/**
	 * Constructs a new exception with the specified line number.
	 *
	 * @param line line where exception was thrown.
	 */
	public IllegalParameterMultipleNumericException(int line) {
		super(line);
	}
}
