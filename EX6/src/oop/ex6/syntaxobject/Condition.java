package oop.ex6.syntaxobject;

import oop.ex6.RegularExpressions;

import java.util.ArrayList;

public class Condition extends SyntaxObject {

	private static final String T;
	private static final String F;
	private static final String CONDITION_SPLITTER_REGEX;

	static {
		T = "true";
		F = "false";
		CONDITION_SPLITTER_REGEX = "(&&)|(\\|\\|)";
	}

	public Condition(String s, CodeUnit unit) throws IllegalSyntaxException {
		if (s == null || s.equals(""))
			throw new IllegalSyntaxException();
		if (s.startsWith("&&") || s.startsWith("||") || s.endsWith("&&") || s.endsWith("||"))
			throw new IllegalSyntaxException();
		if (!s.equals(T) && !s.equals(F)) {
			if (!RegularExpressions.DOUBLE_PATTERN.matcher(s).matches() &&
					!RegularExpressions.INTEGER_PATTERN.matcher(s).matches()) {
				String[] subCondStrings = s.split(CONDITION_SPLITTER_REGEX);
				if (subCondStrings.length == 1) {
					if (!RegularExpressions.NAME_PATTERN.matcher(subCondStrings[0]).matches())
						throw new IllegalSyntaxException();
					// TODO -------------------- CodeUnit's isAssignedBoolean !!
					// if(!unit.isAssignedBoolean(subCondStrings[0]))
					//	throw new IllegalSyntaxException();
				} else {
					ArrayList<Condition> subCondList = new ArrayList<>();
					for (String cond : subCondStrings)
						subCondList.add(new Condition(cond, unit));
				}
			}
		}
	}
}
