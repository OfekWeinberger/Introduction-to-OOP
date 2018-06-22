package oop.ex6.syntaxobject;

import oop.ex6.syntaxobject.scope.Scope;

public abstract class SyntaxObject {

	abstract static void check(String string, Scope scope) throws IllegalSyntaxException;
}
