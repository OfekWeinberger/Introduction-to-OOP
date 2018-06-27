package oop.ex6.syntaxobject;

import oop.ex6.RegularExpressions;
import oop.ex6.syntaxobject.scope.Scope;

import java.util.ArrayList;

public class MethodDeclaration {

	// these are some stings that we will use later, they are error messages and some are part of the
	// s-Java specifications
	private static final String VARIABLE_TYPE_EXCEPTION;
	private static final String ILLEGAL_METHOD_NAME_EXCEPTION;
	private static String VOID_KEYWORD;
	private static String METHOD_BRACKET_SPLITTER_REGEX;
	private static String PARAMETER_SPACER_CHARACTER;
	private static String ILLEGAL_SCOPE;

	static {
		VARIABLE_TYPE_EXCEPTION = "Illegal variable type or name";
		ILLEGAL_METHOD_NAME_EXCEPTION = "Illegal method name";
		ILLEGAL_SCOPE = "Illegal scope for method declaration";
		VOID_KEYWORD = "void";
		METHOD_BRACKET_SPLITTER_REGEX = "(\\))|(\\()";
		PARAMETER_SPACER_CHARACTER = ",";

	}

	/**
	 * Array that will hold the parameters given to the method.
	 */
	private ArrayList<Variable> params;
	/**
	 * Will hold the name of the method.
	 */
	private String name;

	/**
	 * The constructor of the MethodDeclaration, initializes the members of the class.
	 */
	public MethodDeclaration() {
		params = new ArrayList<>();
		name = Type.EMPTY_STRING;
	}

	/**
	 * This method gets a line of the declaration, and checks whether it is legal or not.
	 *
	 * @param line  The line of the declaration.
	 * @param scope The scope where the line lies (should always be a reference to the Root scope)
	 * @throws IllegalSyntaxException If the line is illegal according to s-Java specifications, throws an
	 *                                exception with informative message.
	 */
	public void check(String line, Scope scope) throws IllegalSyntaxException {
		// if the scope given is not root - illegal!
		if(!scope.isRoot()){
			throw new IllegalSyntaxException(ILLEGAL_SCOPE);
		}
		// make sure the line starts with a "void" keyword.
		if (line.startsWith(VOID_KEYWORD)) {
			line = line.substring(VOID_KEYWORD.length() + 1);
			// split the line with brackets
			String[] contents = line.split(METHOD_BRACKET_SPLITTER_REGEX);
			// make sure we have the expected length (will always be 3 if the method declaration is legal)
			if (contents.length != 3) {
				throw new IllegalSyntaxException(ILLEGAL_METHOD_NAME_EXCEPTION + ": " + line);
			}
			// name with be the 1st
			name = contents[0];
			// check whether the name of the method is legal
			if (!RegularExpressions.METHOD_NAME_PATTERN.matcher(name).matches())
				throw new IllegalSyntaxException(ILLEGAL_METHOD_NAME_EXCEPTION + ": " + line);
			// check whether the pattern of the method parameters is legal
			if (!RegularExpressions.PARAMS_PATTERN.matcher(contents[1]).matches()) {
				throw new IllegalSyntaxException(VARIABLE_TYPE_EXCEPTION + ": " + line);
			}
			// declare all the params of the method
			String[] methodParams = contents[1].split(PARAMETER_SPACER_CHARACTER);
			if (!Type.EMPTY_STRING.equals(methodParams[0])) {
				for (String param : methodParams)
					declareMethodParams(param, line);
			}
		}
	}

	// This method is responsible for declaring the variables that are declared in the method declaration -
	// it doesn't add it to the scope, but rather creates array of all the variables.
	private void declareMethodParams(String param, String line) throws IllegalSyntaxException {
		// check for final modifier
		boolean isFinal = false;
		if (param.startsWith(Type.FINAL_MODIFIER)) {
			param = param.substring(Type.FINAL_MODIFIER.length() + 1);
			isFinal = true;
		}
		// check the declaration starts with legal variable name
		if (line.contains(Type.SPACE_CHARACTER) && Type.getType(line.substring(0, line.indexOf
				(Type.SPACE_CHARACTER))) != null) {
			Type varType = Type.getType(param.substring(0, param.indexOf(Type.SPACE_CHARACTER)));
			String varName = param.substring(param.indexOf(Type.SPACE_CHARACTER) + 1);
			// check the name of the variable is legal
			if (!RegularExpressions.NAME_PATTERN.matcher(varName).matches()) {
				throw new IllegalSyntaxException(VARIABLE_TYPE_EXCEPTION + ": " + line);
			}
			// add the new variable to the array of the parameters.
			params.add(new Variable(varType, varName, true, isFinal));
		} else
			throw new IllegalSyntaxException(VARIABLE_TYPE_EXCEPTION + ": " + line);
	}

	/**
	 * Getter for the parameter array.
	 * @return The parameter array that holds all the variables declared in this method declaration.
	 */
	public ArrayList<Variable> getParams() {
		return params;
	}

	/**
	 * Getter for the method name.
	 * @return The name of the method as declared.
	 */
	public String getName() {
		return name;
	}
}
