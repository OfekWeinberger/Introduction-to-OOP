/**
 * Represents a general hash set/
 */
public abstract class SimpleHashSet implements SimpleSet {
	/**
	 * The initial number of cells in the table
	 */
	protected static int INITIAL_CAPACITY = 16;
	/**
	 * The minimal size the table could get
	 */
	protected static int MINIMAL_SIZE = 1;
	/**
	 * Default upper factor
	 */
	protected static float DEFAULT_UPPER_FACTOR = 0.75f;
	/**
	 * Default lower factor
	 */
	protected static float DEFAULT_LOWER_FACTOR = 0.25f;


	/**
	 * current capacity minus 1
	 */
	protected int capacityMinusOne;

	/**
	 * Upper bound of capacity/num of taken spots
	 */
	protected float upperLoadFactor;

	/**
	 * Sub-bound of capacity/num of taken spots
	 */
	protected float lowerLoadFactor;

	/**
	 * Constructor
	 */
	SimpleHashSet() {
		this.upperLoadFactor = DEFAULT_UPPER_FACTOR;
		this.lowerLoadFactor = DEFAULT_LOWER_FACTOR;
	}

	/**
	 * Constructor
	 *
	 * @param upperLoadFactor the upper bound of load factor
	 * @param lowerLoadFactor the lower bound of load factor
	 */
	SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
		this.upperLoadFactor = upperLoadFactor;
		this.lowerLoadFactor = lowerLoadFactor;
	}

	/**
	 * @return the capacity of the table (cell- wise)
	 */
	public int capacity() {
		return capacityMinusOne + 1;
	}

	/**
	 * Add a new value to the set
	 *
	 * @param newValue value to add
	 * @return Return true if succedded, false if value exists already.
	 */
	public abstract boolean add(String newValue);

	@Override
	public abstract boolean contains(String searchVal);

	@Override
	public abstract boolean delete(String toDelete);

	/**
	 * @return number of values in the set.
	 */
	public abstract int size();

	/**
	 * @param value value in the table
	 * @return The new clamped index of the value, matching the table size.
	 */
	protected abstract int clamp(String value);

	/**
	 * @param index the index of the value
	 * @return clamped index of the value
	 */
	protected abstract int clamp(int index);

	/**
	 * @return The lower bound of the load factor
	 */
	protected float getLowerLoadFactor() {
		return lowerLoadFactor;
	}

	/**
	 * @return The upper bound of the load factor.
	 */
	protected float getUpperLoadFactor() {
		return upperLoadFactor;
	}
}
