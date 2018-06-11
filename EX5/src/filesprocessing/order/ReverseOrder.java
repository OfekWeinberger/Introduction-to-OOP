package filesprocessing.order;

import java.io.File;
import java.util.Comparator;

/**
 * This class implements the reverse ordering.
 */
public class ReverseOrder implements Order {

	/**
	 * The order we want to reverse.
	 */
	private Order order;

	/**
	 * Constructor for the reverse order class.
	 *
	 * @param order The order we want to reverse.
	 */
	public ReverseOrder(Order order) {
		this.order = order;
	}

	/**
	 * @return A comparator for the files, reversing the order from the given order (we build the object
	 * with).
	 */
	public Comparator<File> comparator() {
		return (file1, file2) -> -1 * this.order.comparator().compare(file1, file2);
	}

}