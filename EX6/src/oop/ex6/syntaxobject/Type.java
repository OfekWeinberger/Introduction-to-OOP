package oop.ex6.syntaxobject;

import oop.ex6.RegularExpressions;

import java.util.regex.Pattern;

public enum Type {


	/**
	 * The int representative.
	 */
	INT(RegularExpressions.INTEGER_PATTERN),
	/**
	 * The double representative.
	 */
	DOUBLE(RegularExpressions.DOUBLE_PATTERN),
	/**
	 * The boolean representative.
	 */
	BOOLEAN(RegularExpressions.BOOLEAN_PATTERN),
	/**
	 * The String representative.
	 */
	STRING(RegularExpressions.STRING_PATTERN),
	/**
	 * The char representative.
	 */
	CHAR(RegularExpressions.CHARACTER_PATTERN);

	/**
	 * The pattern each type have to decide whether strings are applicable to it according to the s-Java
	 * specifications.
	 */
	private Pattern pattern;

	/**
	 * The keyword for the final modifier, set public because some classes use it.
	 */
	public static final String FINAL_MODIFIER;
	/**
	 * The space character in s-Java, set public because some classes use it.
	 */
	public static final String SPACE_CHARACTER;
	/**
	 * An empty String, set public because some classes use it.
	 */
	public static final String EMPTY_STRING;
	private static final String INT_KEYWORD = "int";
	private static final String DOUBLE_KEYWORD = "double";
	private static final String BOOLEAN_KEYWORD = "boolean";
	private static final String CHAR_KEYWORD = "char";
	private static final String STRING_KEYWORD = "String";

	static {
		FINAL_MODIFIER = "final";
		SPACE_CHARACTER = " ";
		EMPTY_STRING = "";
	}

	/**
	 * The constructor for Type
	 *
	 * @param pattern the The pattern each type have to decide whether strings are applicable to it
	 *                according to the s-Java specifications.
	 */
	Type(Pattern pattern) {
		this.pattern = pattern;
	}

	/**
	 * A method used to decide if the given string matches the given type.
	 * @param s The string to match.
	 * @param type The type we want to find if the string matches to.
	 * @return true if the string matches, false otherwise.
	 */
	public static boolean match(String s, Type type) {
		if (type != null) {
			switch (type) {
				case BOOLEAN:
					return BOOLEAN.match(s);
				case INT:
					return INT.match(s);
				case DOUBLE:
					return DOUBLE.match(s);
				case CHAR:
					return CHAR.match(s);
				case STRING:
					return STRING.match(s);
				default:
					return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * This method will return a typ
	 * @param name String representation of the type.
	 * @return The type related to this string, null if there is no such type.
	 */
	public static Type getType(String name) {
		switch (name) {
			case BOOLEAN_KEYWORD:
				return BOOLEAN;
			case INT_KEYWORD:
				return INT;
			case DOUBLE_KEYWORD:
				return DOUBLE;
			case CHAR_KEYWORD:
				return CHAR;
			case STRING_KEYWORD:
				return STRING;
			default:
				return null;
		}
	}

	/**
	 * A default implementation for all the types of the match
	 * @param s The string we want to test for a match.
	 * @return True if matches, false else.
	 */
	public boolean match(String s) {
		return pattern.matcher(s).matches();
	}

	/**
	 *
	 * @param other
	 * @return
	 */
	public boolean equals(Type other) {
		switch (this) {
			case INT:
				return other == INT;
			case BOOLEAN:
				return (other == INT) || (other == DOUBLE) || (other == BOOLEAN);
			case DOUBLE:
				return (other == INT) || (other == DOUBLE);
			case CHAR:
				return other == CHAR;
			case STRING:
				return other == STRING;
			default:
				return false;
		}
	}
}