package oop.ex6;

import java.util.regex.Pattern;

public class RegularExpressions {

	/**
	 * check if a stain String is a valid int value according to sjava specification
	 */
	private static final String INTEGER_REGEX;
	/**
	 * check if a stain String is a valid double value according to sjava specification
	 */
	private static final String DOUBLE_REGEX;
	/**
	 * check if a stain String is a valid variable name according to sjava specification
	 */
	private static final String NAME_REGEX;
	/**
	 * check if a stain String is a valid method name according to sjava specification
	 */
	private static final String METHOD_NAME_REGEX;
	/**
	 * check if a stain String is a valid String value according to sjava specification
	 */
	private static final String STRING_REGEX;
	private static final String FORBIDDEN_CHARS;
	/**
	 * check if a code line (not comment) ends with '{' ';' or '}'
	 */
	private static final String LINE_REGEX;
	/**
	 * check if a stain String is a valid char value according to sjava specification
	 */
	private static final String CHARACTER_REGEX;
	/**
	 * check if a stain String is a valid boolean value according to sjava specification
	 */
	private static final String BOOLEAN_REGEX;
	/**
	 * find the /n in the text code and tell where to split the code
	 */
	public static final String SPLITTER_REGEX;
	/**
	 * all spaces types one or more times
	 */
	public static final String SPACES_REGEX;
	/**
	 * return all the spaces in the start of a line
	 */
	public static final String START_TRIMER_REGEX;
	/**
	 * mach all the spaces that appear before '(' ')' '{' '}' '=' ',' ';' '&&' or '||'
	 * to make it possible to delete them
	 */
	public static final String BEFORE_TRIMER_REGEX;
	/**
	 * mach all the spaces that appear after '(' ')' '{' '}' '=' ',' ';' '&&' or '||'
	 * to make it possible to delete them
	 */
	public static final String AFTER_TRIMER_REGEX;
	/**
	 * check if a stain String is a valid method call line according to sjava specification
	 */
	private static final String METHOD_CALL_REGEX;
	/**
	 * get all the '(' ')' and ',' in the line
	 */
	public static final String METHOD_CALL_SPLITTER_REGEX;
	/**
	 * check if a stain String matches a method (params...) part in
	 * the method deceleration according to sjava specification
	 */
	private static final String PARAMS_REGEX;
	/**
	 * check if a stain String matches a variables deceleration according to sjava specification
	 */
	private static final String VARIABLES_REGEX;

	public static final Pattern INTEGER_PATTERN;
	public static final Pattern DOUBLE_PATTERN;
	public static final Pattern NAME_PATTERN;
	public static final Pattern METHOD_NAME_PATTERN;
	public static final Pattern STRING_PATTERN;
	public static final Pattern LINE_PATTERN;
	public static final Pattern CHARACTER_PATTERN;
	public static final Pattern BOOLEAN_PATTERN;
	public static final Pattern SPLITTER_PATTERN;
	public static final Pattern SPACES_PATTERN;
	public static final Pattern START_TRIMMER_PATTERN;
	public static final Pattern BEFORE_TRIMMER_PATTERN;
	public static final Pattern AFTER_TRIMMER_PATTERN;
	public static final Pattern METHOD_CALL_PATTERN;
	public static final Pattern METHOD_CALL_SPLITTER_PATTERN;
	public static final Pattern PARAMS_PATTERN;
	public static final Pattern VARIABLES_PATTERN;


	static {
		INTEGER_REGEX = "([-]?[1-9]+[0-9]*)|(0)";
		DOUBLE_REGEX = "([-]?[0-9]+\\.[0-9]+)|"+INTEGER_REGEX;
		NAME_REGEX = "([a-zA-Z]\\w*)|(_\\w+)";
		METHOD_NAME_REGEX = "([a-zA-Z]\\w*)";
		FORBIDDEN_CHARS = "[^,`\\\"]*";
		STRING_REGEX = "\\\""+ FORBIDDEN_CHARS +"\\\"";
		LINE_REGEX = "[.]*[;{}]";
		CHARACTER_REGEX = "\\\'"+ FORBIDDEN_CHARS +"\\\'";
		BOOLEAN_REGEX = "(true)|(false)|"+DOUBLE_REGEX;
		SPLITTER_REGEX = "(?<=[\\\n])";//;\{\}
		SPACES_REGEX = "[\\s]+";
		START_TRIMER_REGEX = "^\\s+";
		BEFORE_TRIMER_REGEX = SPACES_REGEX+"(?=[\\{;\\(,=\\)\\}(\\&\\&)(\\|\\|)])";
		AFTER_TRIMER_REGEX = "(?<=[\\{;\\(,=\\)\\}(\\&\\&)(\\|\\|)])"+SPACES_REGEX;
		METHOD_CALL_REGEX = "([a-zA-Z]\\w*)[\\(][\\w,_\\\"\\\'\\-\\.\\s]*[\\)]";
		PARAMS_REGEX = "(([\\w\\s]+,)*[\\w\\s]+)|()";
		VARIABLES_REGEX = "((([\\w\\s\\.\\-_]+)|(\\'[^,`\\\\\"]\\')|(\\\"[^,`\\\\\"]*\\\"))[,\\=])*(([\\w\\s\\.\\-_]+)|(\\'[^,`\\\\\"]\\')|(\\\"[^,`\\\\\"]*\\\"))";
		METHOD_CALL_SPLITTER_REGEX = "[,\\(\\)]";


		INTEGER_PATTERN = Pattern.compile(INTEGER_REGEX);
		DOUBLE_PATTERN = Pattern.compile(DOUBLE_REGEX);
		NAME_PATTERN = Pattern.compile(NAME_REGEX);
		METHOD_NAME_PATTERN = Pattern.compile(METHOD_NAME_REGEX);
		STRING_PATTERN = Pattern.compile(STRING_REGEX);
		LINE_PATTERN = Pattern.compile(LINE_REGEX);
		CHARACTER_PATTERN = Pattern.compile(CHARACTER_REGEX);
		BOOLEAN_PATTERN = Pattern.compile(BOOLEAN_REGEX);
		SPLITTER_PATTERN = Pattern.compile(SPLITTER_REGEX);
		SPACES_PATTERN = Pattern.compile(SPACES_REGEX);
		START_TRIMMER_PATTERN = Pattern.compile(START_TRIMER_REGEX);
		BEFORE_TRIMMER_PATTERN = Pattern.compile(BEFORE_TRIMER_REGEX);
		AFTER_TRIMMER_PATTERN = Pattern.compile(AFTER_TRIMER_REGEX);
		METHOD_CALL_PATTERN = Pattern.compile(METHOD_CALL_REGEX);
		METHOD_CALL_SPLITTER_PATTERN = Pattern.compile(METHOD_CALL_SPLITTER_REGEX);
		PARAMS_PATTERN = Pattern.compile(PARAMS_REGEX);
		VARIABLES_PATTERN = Pattern.compile(VARIABLES_REGEX);
	}
}
