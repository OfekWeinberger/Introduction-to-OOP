package filesprocessing;

import filesprocessing.filehandlers.TypeOneException;

import java.util.ArrayList;

/**
 * This class implements an attached couple of the T type, with relevant exceptions
 *
 * @param <T> The type of the couple
 */
public class CoupleAndExceptions<T> {
	/**
	 * First T stored
	 */
	private T a;
	/**
	 * Second T stored
	 */
	private T b;
	/**
	 * List of relevant exceptions
	 */
	private ArrayList<TypeOneException> exceptions;

	/**
	 * Creates new couple from the given paramters
	 *
	 * @param a          First T stored
	 * @param b          Second T stored
	 * @param exceptions List of relevant exception for this couple
	 */
	public CoupleAndExceptions(T a, T b, ArrayList<TypeOneException> exceptions) {
		this.a = a;
		this.b = b;
		this.exceptions = exceptions;
	}

	/**
	 * @return First T stored
	 */
	public T getA() {
		return a;
	}

	/**
	 * @return First T stored
	 */
	public T getB() {
		return b;
	}

	/**
	 * @return List of exceptions
	 */
	public ArrayList<TypeOneException> getExceptions() {
		return exceptions;
	}
}
