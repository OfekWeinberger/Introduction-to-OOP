package filesprocessing.order;

import java.io.File;
import java.util.Comparator;

/**
 * This class implements the type ordering.
 */
public class TypeOrder implements Order {

	/**
	 * The order's own instance of itself, for singleton implementation.
	 */
	private static TypeOrder typeOrder = new TypeOrder();

	/**
	 * Empty default constructor.
	 */
	private TypeOrder() {
	}

	/**
	 * @return The single instance of order.
	 */
	public static TypeOrder typeOrder() {
		return TypeOrder.typeOrder;
	}

	/**
	 * This method extract the file type of file.
	 *
	 * @param file The file we want to get the type of.
	 * @return String that represents the type of file.
	 */
	private static String fileType(File file) {
		int index = file.getName().lastIndexOf(".");
		if (index == -1 || index == 0) {
			return "";
		} else {
			return file.getName().substring(index + 1);
		}
	}

	/**
	 * @return A comparator for the files, according to type of the file.
	 */
	public Comparator<File> comparator() {
		return (file1, file2) -> {
			String type1 = fileType(file1);
			String type2 = fileType(file2);
			if (type1.compareTo(type2) == 0) {
				return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
			}
			return type1.compareTo(type2);
		};
	}

}