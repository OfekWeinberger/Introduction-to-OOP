import java.util.LinkedList;

/**
 * This call wraps a string linked list
 */
public class ListWrapper {
	/**
	 * The linked list itself
	 */
	private LinkedList<String> list;

	/**
	 * Constructor- builds an empty list
	 */
	public ListWrapper() {
		list = new LinkedList<>();
	}

	/**
	 * Add a string to the list
	 *
	 * @param s the string to be inserted
	 */
	public void add(String s) {
		list.add(s);
	}

	/**
	 * Search a string in the list
	 *
	 * @param s the string to be searched
	 * @return true if found the string, and false otherwise.
	 */
	public boolean search(String s) {
		return list.contains(s);
	}

	/**
	 * Delete a string from the list
	 *
	 * @param s the string to be deleted
	 * @return true if succedded, false otherwise
	 */
	public boolean remove(String s) {
		if (list.contains(s)) {
			list.remove(s);
			return true;
		}
		return false;
	}

	/**
	 * @return is the list empty
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Take the first value out of the list and pop it.
	 *
	 * @return The first value in the list
	 */
	public String pop() {
		return list.pop();
	}

	/**
	 * @return the first value in the list.
	 */
	public String getFirst() {
		return list.getFirst();
	}
}
