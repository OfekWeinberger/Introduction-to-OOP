package oop.ex6.syntaxobject.scope;

import oop.ex6.syntaxobject.Type;

import java.util.ArrayList;

public class IfWhile extends Scope {
	public IfWhile(Scope father, ArrayList<String> lines) {
		super(father, null, null, lines);
	}
}
