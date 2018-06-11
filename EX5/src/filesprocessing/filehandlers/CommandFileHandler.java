package filesprocessing.filehandlers;

import filesprocessing.CoupleAndExceptions;
import filesprocessing.filter.FilterFactory;
import filesprocessing.order.OrderFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is handling the reading of the command file content, checking syntax of commands and creating
 * a list of all the sections, ready to be constructed into a list of sections.
 */
public class CommandFileHandler {
	/**
	 * String to store the word for filter in command file.
	 */
	public static final String FILTER_MARKER = "FILTER";
	/**
	 * String to store the word for order in command file.
	 */
	public static final String ORDER_MARKER = "ORDER";
	/**
	 * List of couples to be created from the given file (and afterwards sent to the SectionFactory).
	 */
	private ArrayList<CoupleAndExceptions<String>> couples;

	/**
	 * The constructor of the CommandFileHandler, reads file, then creates a list of all sections from it.
	 *
	 * @param path The relative or absolute path of the command file.
	 * @throws IOException        Throws IOException in any case of problem with reading the command file.
	 * @throws BadFormatException Throws BadFormatException in case of problem with the format of the file
	 */
	public CommandFileHandler(String path) throws IOException, BadFormatException {
		ArrayList<String> commands = readFile(path);
		this.couples = new ArrayList<>();
		iterateOverSections(commands);
	}

	// this method reads the file in the given path
	private ArrayList<String> readFile(String path) throws IOException {
		ArrayList<String> stringList = new ArrayList<>();
		File file = new File(path);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			stringList.add(line);
		}
		fileReader.close();
		return stringList;
	}

	// this method iterates over all the sections in the command file
	private void iterateOverSections(ArrayList<String> commands) throws BadFormatException {
		// index we are iterating with on commands
		int index = 0;
		while (index < commands.size()) {
			String filterName;
			String orderName;
			ArrayList<TypeOneException> exceptions = new ArrayList<>();
			// make sure the first line of the section is "FILTER"
			if (!commands.get(index).equals(FILTER_MARKER))
				throw new BadFormatException(FILTER_MARKER);
			index++;

			if (index < commands.size()) {
				// in case filer description is empty - check for order
				if (commands.get(index).equals(ORDER_MARKER)) {
					filterName = FilterFactory.ALL_FILTER;
					index--;
				} else
					filterName = tryFilterAssignment(index, commands, exceptions);
				index++;
				if (index < commands.size()) {
					// make sure there is "ORDER" after filter sub-section
					if (!commands.get(index).equals(ORDER_MARKER))
						throw new BadFormatException(ORDER_MARKER);
					index++;
					// check for end of file or empty order description
					if (index >= commands.size() || commands.get(index).equals(FILTER_MARKER)) {
						orderName = OrderFactory.ABS;
						index--;
					} else
						orderName = tryOrderAssignment(index, commands, exceptions);
					index++;
				} else
					throw new BadFormatException(ORDER_MARKER);
			} else
				throw new BadFormatException(ORDER_MARKER);
			// finally add correct filter name, order name and list of exceptions to the couples
			couples.add(new CoupleAndExceptions<>(filterName, orderName, exceptions));
		}
	}

	// this method checks if filter is legal
	private String tryFilterAssignment(int index, ArrayList<String> commands, ArrayList<TypeOneException>
			exceptions) {
		try {
			checkFilter(commands.get(index), index + 1);
			return commands.get(index);
		} catch (BadNameException | IllegalParameterMultipleNumericException |
				IllegalParameterSingleNumericException | IllegalParameterBooleanException e) {
			exceptions.add(e);
			return FilterFactory.ALL_FILTER;
		} catch (IllegalParameterException e) {
			// This should be unreachable
			e.printStackTrace();
			return null;
		}
	}

	// this method checks if order is legal
	private String tryOrderAssignment(int index, ArrayList<String> commands, ArrayList<TypeOneException>
			exceptions) {
		try {
			checkOrder(commands.get(index), index + 1);
			return commands.get(index);
		} catch (BadNameException e) {
			exceptions.add(e);
			return OrderFactory.ABS;
		}
	}

	// this method checks if filter is one of the legal order names
	private void checkFilter(String name, int line) throws IllegalParameterException,
			BadNameException {
		String filterName;
		String filterValue = "";
		int index = name.indexOf("#");
		if (index > 0) {
			filterName = name.substring(0, index);
			filterValue = name.substring(index + 1);
			if (filterValue.contains("#NOT")) {
				filterValue = filterValue.substring(0, filterValue.lastIndexOf("#NOT"));
			}
		} else {
			filterName = name;
		}
		switch (filterName) {
			case FilterFactory.FILE_FILTER:
			case FilterFactory.CONTAINS_FILTER:
			case FilterFactory.PREFIX_FILTER:
			case FilterFactory.SUFFIX_FILTER:
			case FilterFactory.ALL_FILTER:
				return;
			case FilterFactory.HIDDEN_FILTER:
			case FilterFactory.WRITABLE_FILTER:
			case FilterFactory.EXECUTABLE_FILTER:
				if (!(filterValue.equals("YES") || filterValue.equals("NO"))) {
					throw new IllegalParameterBooleanException(line);
				}
				break;
			case FilterFactory.GREATER_THAN_FILTER:
			case FilterFactory.SMALLER_THAN_FILTER:
			case FilterFactory.BETWEEN_FILTER:
				String[] stringValues = filterValue.split("#");
				Double[] doubleValues = new Double[stringValues.length];
				for (int i = 0; i < stringValues.length; i++) {
					doubleValues[i] = Double.parseDouble(stringValues[i]);
				}
				for (double v : doubleValues) {
					if (v < 0) {
						throw new IllegalParameterSingleNumericException(line);
					}
				}
				if (filterName.equals(FilterFactory.BETWEEN_FILTER)) {
					if (doubleValues[FilterFactory.LOWER_BOUND_INDEX] >
							doubleValues[FilterFactory.UPPER_BOUND_INDEX]) {
						throw new IllegalParameterMultipleNumericException(line);
					}
				}
				break;
			default:
				throw new BadNameException(line);
		}
	}

	// this method checks if order is one of the legal order names
	private void checkOrder(String name, int line) throws BadNameException {
		String orderName;
		int index = name.indexOf("#");
		if (index > 0) orderName = name.substring(0, index);
		else orderName = name;
		if (!(orderName.equals(OrderFactory.ABS) ||
				orderName.equals(OrderFactory.TYPE) ||
				orderName.equals(OrderFactory.SIZE))) {
			throw new BadNameException(line);
		}
	}

	// this method returns the list of couples
	public ArrayList<CoupleAndExceptions<String>> getCouples() {
		return couples;
	}
}