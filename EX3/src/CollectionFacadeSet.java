import java.util.Collection;
/**
 * A class that describes a collection of any type.
 */
public class CollectionFacadeSet implements SimpleSet {
	/**
	 * The collection itself
	 */
	private Collection<String> c;

	/**
	 * Constructor
	 *
	 * @param collection the collection this class wraps
	 */
	CollectionFacadeSet(Collection<String> collection) {
		this.c = collection;
	}

	// this method counts how many times toCount is in the collection
	private int count(Collection<String> collection, String toCount) {
		int count = 0;
		for (String  s: collection)
			if (s.equals(toCount))
				count++;
		return count;
	}

	/**
	 * Add a specified element to the set if it's not already in it.
	 *
	 * @param newValue New value to add to the set
	 * @return False iff newValue already exists in the set
	 */
	@Override
	public boolean add(String newValue) {
		return !c.contains(newValue) && c.add(newValue);
	}

	/**
	 * Look for a specified value in the set.
	 *
	 * @param searchVal Value to search for
	 * @return True iff searchVal is found in the set
	 */
	@Override
	public boolean contains(String searchVal) {
		return c.contains(searchVal);
	}

	/**
	 * Remove the input element from the set.
	 *
	 * @param toDelete Value to delete
	 * @return True iff toDelete is found and deleted
	 */
	@Override
	public boolean delete(String toDelete) {
		return c.remove(toDelete);
	}

	/**
	 * @return The number of elements currently in the set
	 */
	@Override
	public int size() {
		return c.size();
	}
}
