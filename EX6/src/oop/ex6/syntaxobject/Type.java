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

	boolean match(String s) {
		return pattern.matcher(s).matches();
	}
}