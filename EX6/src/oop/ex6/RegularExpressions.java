package oop.ex6;

import java.util.regex.Pattern;

public class RegularExpressions {

	public static final String INTEGER_REGEX;
	public static final String DOUBLE_REGEX;
	public static final String NAME_REGEX;
	public static final String METHOD_NAME_REGEX;
	public static final String STRING_REGEX;
	public static final String LINE_REGEX;
	public static final String CHARACTER_REGEX;
	public static final String BOOLEAN_REGEX;

	public static final Pattern INTEGER_PATTERN;
	public static final Pattern DOUBLE_PATTERN;
	public static final Pattern NAME_PATTERN;
	public static final Pattern METHOD_NAME_PATTERN;
	public static final Pattern STRING_PATTERN;
	public static final Pattern LINE_PATTERN;
	public static final Pattern CHARACTER_PATTERN;
	public static final Pattern BOOLEAN_PATTERN;


	static {
		 INTEGER_REGEX = "([1-9]+[0-9]*)|(0)";
		 DOUBLE_REGEX = "[0-9]+.[0-9]+";
		 NAME_REGEX = "([a-zA-Z]\\w*)|(_\\w+)";
		 METHOD_NAME_REGEX = "([a-zA-Z]\\w*)";
		 STRING_REGEX = "\"[^,`\\\\\"]*\"";
		 LINE_REGEX = "[.]*[;{}]";
		 CHARACTER_REGEX = "\'[^,`\\\\\"]+\'";
		 BOOLEAN_REGEX = "(true)|(false)|(([1-9]+[0-9]*)|(0))|([0-9]+.[0-9]+)";

		 INTEGER_PATTERN = Pattern.compile(INTEGER_REGEX);
		 DOUBLE_PATTERN = Pattern.compile(DOUBLE_REGEX);
		 NAME_PATTERN = Pattern.compile(NAME_REGEX);
		 METHOD_NAME_PATTERN = Pattern.compile(METHOD_NAME_REGEX);
		 STRING_PATTERN = Pattern.compile(STRING_REGEX);
		 LINE_PATTERN = Pattern.compile(LINE_REGEX);
		 CHARACTER_PATTERN = Pattern.compile(CHARACTER_REGEX);
		 BOOLEAN_PATTERN = Pattern.compile(BOOLEAN_REGEX);
	}
}
