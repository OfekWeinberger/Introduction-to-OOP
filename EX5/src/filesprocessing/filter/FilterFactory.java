package filesprocessing.filter;

/**
 * This class is responsible on creating filters from the string of the filter description.
 */
public class FilterFactory {
	/**
	 * This string represents the way all filter name is written in the command file.
	 */
	public static final String ALL_FILTER = "all";
	/**
	 * This string represents the way between filter name is written in the command file.
	 */
	public static final String BETWEEN_FILTER = "between";
	/**
	 * This string represents the way contains filter name is written in the command file.
	 */
	public static final String CONTAINS_FILTER = "contains";
	/**
	 * This string represents the way executable filter name is written in the command file.
	 */
	public static final String EXECUTABLE_FILTER = "executable";
	/**
	 * This string represents the way file filter name is written in the command file.
	 */
	public static final String FILE_FILTER = "file";
	/**
	 * This string represents the way greater than filter name is written in the command file.
	 */
	public static final String GREATER_THAN_FILTER = "greater_than";
	/**
	 * This string represents the way hidden filter name is written in the command file.
	 */
	public static final String HIDDEN_FILTER = "hidden";
	/**
	 * This string represents the way prefix filter name is written in the command file.
	 */
	public static final String PREFIX_FILTER = "prefix";
	/**
	 * This string represents the way smaller than filter name is written in the command file.
	 */
	public static final String SMALLER_THAN_FILTER = "smaller_than";
	/**
	 * This string represents the way suffix filter name is written in the command file.
	 */
	public static final String SUFFIX_FILTER = "suffix";
	/**
	 * This string represents the way writable filter name is written in the command file.
	 */
	public static final String WRITABLE_FILTER = "writable";


	/**
	 * Int marker to mark the index in lower bound in parameter array (for between filter).
	 */
	public static final int LOWER_BOUND_INDEX = 0;
	/**
	 * Int marker to mark the index in upper bound in parameter array (for between filter).
	 */
	public static final int UPPER_BOUND_INDEX = 1;

	/**
	 * This method is the constructor itself, creating a filter.
	 *
	 * @param filterString: The filter description string.
	 * @return The filter created from the string.
	 */
	public static Filter createFilter(String filterString) {
		Filter filter;
		int parameterIndex = filterString.indexOf('#');

		if (parameterIndex > 0) {
			String filterName = filterString.substring(0, parameterIndex);
			String filterValue = filterString.substring(parameterIndex + 1);

			boolean not = filterValue.contains("#NOT");
			if (not)
				filterValue = filterValue.substring(0, filterValue.lastIndexOf("#NOT"));

			switch (filterName) {
				case BETWEEN_FILTER:
					String[] bounds = filterValue.split("#");
					filter = new BetweenFilter(Double.parseDouble(bounds[LOWER_BOUND_INDEX]),
							Double.parseDouble(bounds[UPPER_BOUND_INDEX]));
					break;

				case CONTAINS_FILTER:
					filter = new ContainsFilter(filterValue);
					break;

				case EXECUTABLE_FILTER:
					filter = new ExecutableFilter((filterValue.equals("YES")));
					break;

				case FILE_FILTER:
					filter = new FileFilter(filterValue);
					break;

				case GREATER_THAN_FILTER:
					filter = new GreaterThanFilter(Double.parseDouble(filterValue));
					break;

				case HIDDEN_FILTER:
					filter = new HiddenFilter(filterValue.equals("YES"));
					break;

				case PREFIX_FILTER:
					filter = new PrefixFilter(filterValue);
					break;

				case SMALLER_THAN_FILTER:
					filter = new SmallerThanFilter(Double.parseDouble(filterValue));
					break;

				case SUFFIX_FILTER:
					filter = new SuffixFilter(filterValue);
					break;

				case WRITABLE_FILTER:
					filter = new WritableFilter(filterValue.equals("YES"));
					break;

				default:
					filter = new NotFilter(new AllFilter());
					break;
			}

			if (not)
				filter = new NotFilter(filter);

		} else
			filter = new AllFilter();

		return filter;
	}

}