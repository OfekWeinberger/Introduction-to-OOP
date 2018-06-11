package filesprocessing.order;

import java.io.File;
import java.util.Comparator;

/**
 * This class implements the absolute ordering.
 */
public class AbsOrder implements Order {

	/**
	 * The order's own instance of itself, for singleton implementation.
	 */
	private static AbsOrder absOrder = new AbsOrder();

	/**
	 * Empty default constructor.
	 */
	private AbsOrder() {
	}

	/**
	 * @return The single instance of order.
	 */
	public static AbsOrder absOrder() {
		return AbsOrder.absOrder;
	}

	/**
	 * @return A comparator for the files, according to absolute order
	 */
	public Comparator<File> comparator() {
		return Comparator.comparing(File::getAbsolutePath);
	}

}