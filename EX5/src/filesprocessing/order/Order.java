package filesprocessing.order;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * The interface every filter should implement.
 */
public interface Order {

	/**
	 * @return A comparator for the files, according to implementation of the order.
	 */
	Comparator<File> comparator();

	/**
	 * The default implementation for every order type, should work for every order (given the comparator
	 * it creates is correct).
	 *
	 * @param files Array of files we want to re-order.
	 */
	default void order(ArrayList<File> files) {
		files.sort(comparator());
	}

}