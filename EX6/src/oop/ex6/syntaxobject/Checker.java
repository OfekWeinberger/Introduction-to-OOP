package oop.ex6.syntaxobject;

import oop.ex6.syntaxobject.scope.Scope;

public interface Checker {
	/**
	 * This method checks the given string if it is applicable to the s-Java specifications.
	 *
	 * @param string The string we want to check.
	 * @param scope  The scope where this string lies.
	 * @throws IllegalSyntaxException The exception thrown if the method finds a syntax error in the code.
	 */
	void check(String string, Scope scope) throws IllegalSyntaxException;
}
