import static java.util.Objects.hash;

/**
 * this class implements open hash set
 */
public class OpenHashSet extends SimpleHashSet {
	/**
	 * the table, array of all the table.
	 */
	private ListWrapper[] table;
	/**
	 * the number of values stored in the set
	 */
	private int size;

	/**
	 * default constructor.
	 */
	public OpenHashSet() {
		super();
		table = new ListWrapper[INITIAL_CAPACITY];
		capacityMinusOne = INITIAL_CAPACITY - 1;
	}

	/**
	 * constructor adding the elements in the string array to the set
	 *
	 * @param data array of strings we want to insert into the set
	 */
	public OpenHashSet(String[] data) {
		//super();
		table = new ListWrapper[INITIAL_CAPACITY];
		capacityMinusOne = INITIAL_CAPACITY - 1;
		for (String value : data) {
			add(value);
		}
	}

	/**
	 * constructor (default) with custom load factors
	 *
	 * @param upperLoadFactor A custom upper load factor to replace default values
	 * @param lowerLoadFactor A custom lower load factor to replace default values
	 */
	public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
		super(upperLoadFactor, lowerLoadFactor);
		table = new ListWrapper[INITIAL_CAPACITY];
		capacityMinusOne = INITIAL_CAPACITY - 1;

	}

	/**
	 * Add a new value to the table
	 *
	 * @param newValue value to add
	 * @return Return true if succedded, false if value exists already.
	 */
	public boolean add(String newValue) {
		if ((size + 1) >= (int) (upperLoadFactor * capacity())) {
			//If adding the element will excceed the table limit.
			reformatTable(false);
		}
		int index = hash(newValue) & (capacity() - 1);
		if (table[index] == null) {//If no items in this index.
			table[index] = new ListWrapper();
		}
		if (table[index].search(newValue)) {//If item is already in
			return false;
		}
		table[index].add(newValue);//Add item
		size += 1;
		return true;
	}

	/**
	 * Delete specific value from the table
	 *
	 * @param toDelete value to delete
	 * @return True if deleted, false if value not found.
	 */
	public boolean delete(String toDelete) {
		if ((size - 1) <= (int) (lowerLoadFactor * capacity()) && capacity() > MINIMAL_SIZE) {
			reformatTable(true);
		}
		int index = hash(toDelete) & (capacity() - 1);
		if (table[index] == null) {
			return false;
		}
		if (table[index].remove(toDelete)) {
			size--;
			return true;
		}
		return false;
	}

	/**
	 * Search a value
	 *
	 * @param searchVal Value to be searched
	 * @return True if found, False otherwise.
	 */
	public boolean contains(String searchVal) {
		int index = hash(searchVal) & (capacity() - 1);
		return table[index] != null && table[index].search(searchVal);
	}

	/**
	 * Reformat the table according to the current situation. If it's too large, divide it's size.
	 * If it's too small, double it.
	 *
	 * @param isTooLarge Is the table too large?
	 */
	private void reformatTable(boolean isTooLarge) {
		ListWrapper[] newTable; ///The new table to be used
		if (!isTooLarge) {  //If too small, we'll double the table.
			newTable = new ListWrapper[capacity() * 2];
			capacityMinusOne = capacity() * 2 - 1;
		} else {///If too large, the new table will be two times smaller
			newTable = new ListWrapper[capacity() / 2];
			capacityMinusOne = capacity() / 2 - 1;
		}
		for (ListWrapper list : table) { ///For every list in the current table
			while (list != null && !list.isEmpty()) { ///While the list isn't null or empty
				int newIndex = clamp(list.getFirst()); //We clamp a matching index to the item in this
				// index
				String element = list.pop(); //Remove it
				if (newTable[newIndex] == null) { //And move it to the new table
					newTable[newIndex] = new ListWrapper();
				}
				newTable[newIndex].add(element);
			}
		}
		table = newTable; //Make the created table the current, updated table.
	}

	/**
	 * get the correct index in the table for a string value
	 *
	 * @param value the value in the table we need to hash and clamp
	 * @return the index of the item in the table
	 */
	protected int clamp(String value) {
		return hash(value) & (capacity() - 1);
	}

	/**
	 * @param index the index of the value
	 * @return clamped index of the value
	 */
	@Override
	protected int clamp(int index) {
		return hash(table[index].getFirst()) & (capacity() - 1);
	}

	/**
	 * @return the number of elements in the set
	 */
	public int size() {
		return size;
	}
}


