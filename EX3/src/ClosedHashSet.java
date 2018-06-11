import static java.util.Objects.hash;

/***
 * this class implements a closed hash set.
 */
public class ClosedHashSet extends SimpleHashSet {
	/**
	 * hash table
	 */
	private String[] table;
	/**
	 * this array will tell if the i'th cell was deleted - if the i'th cell was deleted in the table,
	 * we will have a true value here.
	 */
	private boolean[] deletedCells;
	/**
	 * the size of the Hashtable, representing the number of elements
	 */
	private int size;

	/**
	 * default constructor
	 */
	public ClosedHashSet() {
		super();
		this.table = new String[INITIAL_CAPACITY];
		this.deletedCells = new boolean[INITIAL_CAPACITY];
		this.capacityMinusOne = INITIAL_CAPACITY - 1;
	}

	/**
	 * constructor adding the elements in the string array to the set
	 *
	 * @param data array of strings to insert into the set
	 */
	public ClosedHashSet(String[] data) {
		super();
		this.table = new String[INITIAL_CAPACITY];
		this.deletedCells = new boolean[INITIAL_CAPACITY];
		this.capacityMinusOne = INITIAL_CAPACITY - 1;
		// add elements in data
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
	public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
		super(upperLoadFactor, lowerLoadFactor);
		table = new String[INITIAL_CAPACITY];
		capacityMinusOne = INITIAL_CAPACITY - 1;
		deletedCells = new boolean[INITIAL_CAPACITY];

	}

	/**
	 * Add a new value to the table, makes sure no there are no duplicates
	 *
	 * @param newValue value to add
	 * @return reports if action was successful, false otherwise
	 */
	public boolean add(String newValue) {
		// if the set contains new value - return false
		if (contains(newValue))
			return false;
		// check if we need to reHash
		if (size + 1 >= (int) (upperLoadFactor * capacity()))
			reHash(true);
		// probe
		for (int i = 0; i < table.length; i++) {
			int index = clamp(newValue, i);
			// test for empty cell
			if (table[index] == null) {
				table[index] = newValue;
				size++;
				deletedCells[index] = false;
				return true;
			}
		}
		return false;
	}

	/**
	 * Delete specific value from the table
	 *
	 * @param toDelete value to delete
	 * @return True if deleted, false if value not found.
	 */
	public boolean delete(String toDelete) {
		// check if toDelete value is in the set
		if (!contains(toDelete))
			return false;
		// check if we need to reHash
		if (size() - 1 <= (int) (lowerLoadFactor * capacity()) && capacity() > MINIMAL_SIZE)
			reHash(false);
		// probe
		for (int i = 0; i < table.length; i++) {
			int index = clamp(toDelete, i);
			// check for empty cell the wasn't deleted
			if (table[index] == null && !deletedCells[index])
				return false;
			if (toDelete.equals(table[index])) {
				table[index] = null;
				deletedCells[index] = true;
				size--;
				return true;
			}
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
		for (int i = 0; i < capacity(); i++) {//Search for val in quadratic probing
			int index = clamp(searchVal, i);
			// finally reached and empty cell - not found
			if (table[index] == null && !deletedCells[index])
				return false;
			// found value
			if (searchVal.equals(table[index]))
				return true;

		}
		return false;
	}

	/**
	 * rehash set according to state- if it's too large, divide it's size by 2, if it's too small, double it.
	 *
	 * @param extend true if table is to be doubled
	 */
	private void reHash(boolean extend) {
		String[] newTable; ///The new table to be used
		if (extend) {
			capacityMinusOne = table.length * 2 - 1;
			newTable = new String[capacity()];
		} else {
			capacityMinusOne = table.length / 2 - 1;
			newTable = new String[capacity()];
		}

		for (String aTable : table) {
			if (aTable != null) {
				// probe in new table
				for (int j = 0; j < capacity(); j++) {
					int newIndex = clamp(aTable, j);
					if (newTable[newIndex] == null) {
						newTable[newIndex] = aTable;
						break;
					}
				}
			}
		}
		// nothing is deleted in new table
		deletedCells = new boolean[capacity()];
		table = newTable;
	}

	/**
	 * get the correct index in the table for a string value and a probing iteration, using quadratic probing
	 *
	 * @param value the value
	 * @param i     the iteration of the probing
	 * @return The updated index of the item
	 */
	private int clamp(String value, int i) {
		return (hash(value) + (i + i * i) / 2) & (capacity() - 1);
	}

	/**
	 * get the correct index in the table for a string value
	 *
	 * @param value the value
	 * @return The correct index of the item
	 */
	protected int clamp(String value) {
		return clamp(value, 0);
	}

	/**
	 * @param index the index of the value
	 * @return clamped index of the value
	 */
	@Override
	protected int clamp(int index) {
		return hash(table[index]) & (capacity() - 1);
	}

	public int size() {
		return size;
	}
}

