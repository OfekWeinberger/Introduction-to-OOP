package oop.ex6.syntaxobject.scope;

import java.util.ArrayList;

public class IfWhile extends Scope {
	/**
	 * a constractor for the scope
	 *
	 * @param father - the parent scope
	 * @param lines  - the line inside the scope section
	 */
	public IfWhile(Scope father, ArrayList<String> lines) {
		super(father, null, null, lines);
	}
}
