package oop.ex6.syntaxobject;

import oop.ex6.RegularExpressions;
import oop.ex6.syntaxobject.scope.Scope;

import java.util.ArrayList;

public class Condition {

	private static final String TRUE_KEYWORD;
	private static final String FALSE_KEYWORD;
	private static final String AND_KEYWORD;
	private static final String OR_KEYWORD;
	private static final String CONDITION_SPLITTER_REGEX;
	private static final String AND_OR_START_EXCEPTION;
	private static final String EMPTY_CONDITION_EXCEPTION;
	private static final String VARIABLE_NAME_EXCEPTION;
	private static final String VARIABLE_TYPE_EXCEPTION;

	static {
		TRUE_KEYWORD = "true";
		FALSE_KEYWORD = "false";
		AND_KEYWORD = "&&";
		OR_KEYWORD = "||";
		CONDITION_SPLITTER_REGEX = "(&&)|(\\|\\|)";
		AND_OR_START_EXCEPTION = "Condition can't starts with && or ||";
		EMPTY_CONDITION_EXCEPTION = "Condition can't be empty";
		VARIABLE_NAME_EXCEPTION = "Variable name in condition is incorrect";
		VARIABLE_TYPE_EXCEPTION = "Variable in condition should be assigned boolean,int or double";
	}

	public void check(String s, Scope scope) throws IllegalSyntaxException {
		if (s == null || s.equals(Type.EMPTY_STRING))
			throw new IllegalSyntaxException(EMPTY_CONDITION_EXCEPTION);
		if (s.startsWith(AND_KEYWORD) || s.startsWith(OR_KEYWORD) || s.endsWith(AND_KEYWORD) ||
				s.endsWith(OR_KEYWORD))
			throw new IllegalSyntaxException(AND_OR_START_EXCEPTION);
		if (!s.equals(TRUE_KEYWORD) && !s.equals(FALSE_KEYWORD)) {
			if (!RegularExpressions.DOUBLE_PATTERN.matcher(s).matches() &&
					!RegularExpressions.INTEGER_PATTERN.matcher(s).matches()) {
				String[] subCondStrings = s.split(CONDITION_SPLITTER_REGEX);
				if (subCondStrings.length == 1) {
					if (!RegularExpressions.NAME_PATTERN.matcher(subCondStrings[0]).matches())
						throw new IllegalSyntaxException(VARIABLE_NAME_EXCEPTION);
					if (!(scope.isVarAssigned(Type.BOOLEAN, subCondStrings[0])) &&
							!(scope.isVarAssigned(Type.INT, subCondStrings[0])) && !(scope.isVarAssigned
							(Type.DOUBLE, subCondStrings[0]))) {
						throw new IllegalSyntaxException(VARIABLE_TYPE_EXCEPTION);
					}
				} else {
					for (String cond : subCondStrings)
						check(cond, scope);
				}
			}
		}
	}
}
