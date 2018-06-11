package filesprocessing.section;

import filesprocessing.filehandlers.TypeOneException;
import filesprocessing.filter.Filter;
import filesprocessing.order.Order;

import java.io.File;
import java.util.ArrayList;

/**
 * This class represents a single section
 */
public class Section {

	/**
	 * The filter specified in the section.
	 */
	private Filter filter;
	/**
	 * The order specified in the section.
	 */
	private Order order;
	/**
	 * The list of exceptions related two this specific section.
	 */
	private ArrayList<TypeOneException> exceptions;

	/**
	 * The constructor of the section class, creates new section with the given parameters.
	 *
	 * @param filter:     The filter of this section.
	 * @param order:      The order of this section.
	 * @param exceptions: The relevant exceptions (list) for this section.
	 */
	public Section(Filter filter, Order order, ArrayList<TypeOneException> exceptions) {
		this.filter = filter;
		this.order = order;
		this.exceptions = exceptions;
	}

	/**
	 * This is the method actually filtering and ordering the files in the given list.
	 *
	 * @param files The list of files we want to filter or order.
	 */
	public void filterAndOrder(ArrayList<File> files) {
		this.filter.filter(files);
		this.order.order(files);
	}

	/**
	 * @return The list of exceptions relevant to this section.
	 */
	public ArrayList<TypeOneException> getExceptions() {
		return exceptions;
	}
}
