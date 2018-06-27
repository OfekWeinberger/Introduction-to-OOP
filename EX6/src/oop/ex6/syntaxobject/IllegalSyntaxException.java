package oop.ex6.syntaxobject;

public class IllegalSyntaxException extends Exception {

	/**
	 * The constructor of the exception, only gets the error message.
	 *
	 * @param message The error message, should be informative.
	 */
	public IllegalSyntaxException(String message) {
		super(message);
	}
}
