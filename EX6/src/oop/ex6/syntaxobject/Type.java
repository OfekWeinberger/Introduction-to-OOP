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

	public static Type getType(String name) {
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

	public boolean match(String s) {
		return pattern.matcher(s).matches();
	}

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