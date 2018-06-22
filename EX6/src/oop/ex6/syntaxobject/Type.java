package oop.ex6.syntaxobject;

import oop.ex6.RegularExpressions;

import java.util.regex.Pattern;

public enum Type {
	INT(RegularExpressions.INTEGER_PATTERN),
	DOUBLE(RegularExpressions.DOUBLE_PATTERN),
	BOOLEAN(RegularExpressions.BOOLEAN_PATTERN),
	STRING(RegularExpressions.STRING_PATTERN),
	CHAR(RegularExpressions.CHARACTER_PATTERN);

	private Pattern pattern;

	Type(Pattern pattern) {
		this.pattern = pattern;
	}

	public boolean match(String s) {
		return pattern.matcher(s).matches();
	}

	public static boolean match(String s, String type){
		switch (type) {
			case "boolean":
				return BOOLEAN.match(s);
			case "int":
				return INT.match(s);
			case "double":
				return DOUBLE.match(s);
			case "char":
				return CHAR.match(s);
			case "String":
				return STRING.match(s);
			default:
				return false;
		}
	}

	public static Type getType(String name){
		switch (name) {
			case "boolean":
				return BOOLEAN;
			case "int":
				return INT;
			case "double":
				return DOUBLE;
			case "char":
				return CHAR;
			case "String":
				return STRING;
			default:
				return null;
		}
	}
}