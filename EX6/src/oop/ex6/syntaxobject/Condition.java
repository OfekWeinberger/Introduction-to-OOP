package oop.ex6.syntaxobject;

import oop.ex6.RegularExpressions;
import oop.ex6.syntaxobject.scope.Scope;

import java.util.ArrayList;

public class Condition extends SyntaxObject {

	private static final String T;
	private static final String F;
	private static final String CONDITION_SPLITTER_REGEX;

	private static final String AND_OR_START_EXCEPTION = "Condition can't starts with && or ||";
	private static final String EMPTY_CONDITION_EXCEPTION = "Condition can't be empty";
	private static final String VARIBLE_NAME_EXCEPTION = "Varibale name in condition is incorect";
	private static final String VARIABLE_TYPE_EXCEPTION = "Variable in condition should be boolean";

	static {
		T = "true";
		F = "false";
		CONDITION_SPLITTER_REGEX = "(&&)|(\\|\\|)";
	}

	public void check(String s, Scope scope) throws IllegalSyntaxException {
		if (s == null || s.equals(""))
			throw new IllegalSyntaxException(EMPTY_CONDITION_EXCEPTION);
		if (s.startsWith("&&") || s.startsWith("||") || s.endsWith("&&") || s.endsWith("||"))
			throw new IllegalSyntaxException(AND_OR_START_EXCEPTION);
		if (!s.equals(T) && !s.equals(F)) {
			if (!RegularExpressions.DOUBLE_PATTERN.matcher(s).matches() &&
					!RegularExpressions.INTEGER_PATTERN.matcher(s).matches()) {
				String[] subCondStrings = s.split(CONDITION_SPLITTER_REGEX);
				if (subCondStrings.length == 1) {
					if (!RegularExpressions.NAME_PATTERN.matcher(subCondStrings[0]).matches())
						throw new IllegalSyntaxException(VARIBLE_NAME_EXCEPTION);
					if (!scope.isAssigned(Type.BOOLEAN, subCondStrings[0]))
						throw new IllegalSyntaxException(VARIABLE_TYPE_EXCEPTION);
				} else {
					ArrayList<Condition> subCondList = new ArrayList<>();
					for (String cond : subCondStrings)
						check(cond, scope);
				}
			}
		}
	}
}
