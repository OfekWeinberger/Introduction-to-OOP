package oop.ex6.syntaxobject.scope;

import java.util.ArrayList;

public class IfWhile extends Scope {
	public IfWhile(Scope father, ArrayList<String> lines) {
		super(father, null, null, lines);
	}
}
