package filesprocessing.filehandlers;

/**
 * This exception represents the case the boolean parameter "#YES" or #NO is illegal (e.g #N instead of #NO).
 */
public class IllegalParameterBooleanException extends IllegalParameterException {

	/**
	 * Constructs a new exception with the specified line number.
	 *
	 * @param line line where exception was thrown.
	 */
	public IllegalParameterBooleanException(int line) {
		super(line);
	}
}
