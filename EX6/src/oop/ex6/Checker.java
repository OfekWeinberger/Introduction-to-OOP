package oop.ex6;

import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.scope.Scope;

public interface Checker {
	void check(String string, Scope scope) throws IllegalSyntaxException;
}
