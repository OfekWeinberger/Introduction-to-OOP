package filesprocessing.order;

import java.io.File;
import java.util.Comparator;

/**
 * This class implements the size ordering.
 */
public class SizeOrder implements Order {

	/**
	 * The order's own instance of itself, for singleton implementation.
	 */
	private static SizeOrder sizeOrder = new SizeOrder();

	/**
	 * Empty default constructor.
	 */
	private SizeOrder() {
	}

	/**
	 * @return The single instance of order.
	 */
	public static SizeOrder sizeOrder() {
		return SizeOrder.sizeOrder;
	}

	/**
	 * @return A comparator for the files, ordering according to size.
	 */
	public Comparator<File> comparator() {
		return (file1, file2) -> {
			int absComparison = Long.compare(file1.length(), file2.length());
			if (absComparison == 0) {
				return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
			} else {
				return absComparison;
			}
		};
	}

}