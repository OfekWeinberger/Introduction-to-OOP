package oop.ex6.syntaxobject;

import oop.ex6.RegularExpressions;
import oop.ex6.syntaxobject.scope.Method;
import oop.ex6.syntaxobject.scope.Scope;

import java.util.ArrayList;

public class CodeLine {

	// these are strings that hold the exception messages
	private static final String ILLEGAL_START_EXCEPTION;
	private static final String ILLEGAL_VARIABLE_EXCEPTION;
	private static final String ILLEGAL_VARIABLE_NAME_EXCEPTION;
	private static final String OVERRIDE_EXCEPTION;
	private static final String FINAL_VARIABLE_ASSIGNMENT_EXCEPTION;
	private static final String METHOD_NOT_DECLARED_EXCEPTION;
	private static final String RETURN_IN_ROOT_EXCEPTION;

	static {
		ILLEGAL_START_EXCEPTION = "The line starts with illegal word or expression";
		ILLEGAL_VARIABLE_EXCEPTION = "Illegal variable written";
		ILLEGAL_VARIABLE_NAME_EXCEPTION = "Illegal variable name";
		OVERRIDE_EXCEPTION = "Cannot override variable from the same scope";
		FINAL_VARIABLE_ASSIGNMENT_EXCEPTION = "Final variable cannot be re-assigned";
		METHOD_NOT_DECLARED_EXCEPTION = " method is not declared";
		RETURN_IN_ROOT_EXCEPTION = "return can be only in methods";
	}

	/**
	 * This method gets a line, without the ';', and checks whether it is legal or not, except for method
	 * declaration that is handled by another class (MethodDeclaration).
	 * @param line The line, without semicolon in the end.
	 * @param scope The scope where the line lies.
	 * @throws IllegalSyntaxException If the line is illegal according to s-Java specifications, throws an
	 * exception with informative message.
	 */
	public static void check(String line, Scope scope) throws IllegalSyntaxException {

		//check if the we are talking about a final variable
		boolean isFinal = false;
		if (line.startsWith("final")) {
			line = line.substring("final".length() + 1);
			isFinal = true;
		}

		// check for variable declaration
		if (line.startsWith("boolean ") || line.startsWith("int ") || line.startsWith("double ") ||
				line.startsWith("char ") || line.startsWith("String ")) {

			// check if the variable declaration is in the pattern we expect in s-Java
			if (!RegularExpressions.VARIABELS_PATTERN.matcher(line).matches()) {
				throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION);
			}

			// split the line using comma, get the type of the declared variable
			String[] lineContent = line.split("(,)");
			Type varType = Type.getType(lineContent[0].substring(0, lineContent[0].indexOf(" ")));
			lineContent[0] = lineContent[0].substring(lineContent[0].indexOf(" ") + 1);

			// for each of the declarations - declare a variable (declaration are separated with comma)
			for (String rawDeclaration : lineContent)
				declareVariable(isFinal, rawDeclaration, varType, scope, line);

		} else if (!line.equals("return")) {
			// if line doesn't accept the method call pattern, it is a method call
			if (RegularExpressions.METHOD_CALL_PATTERN.matcher(line).matches()) {
				methodCallHandler(line, scope);
			} else if (!line.startsWith("//"))
				// else, we are talking about a variable assignment
				assignmentHandler(line, scope);
		} else if (scope.isRoot()) {
			// if line was a return statement, we must make sure it was not in the root scope because that
			// is impossible.
			throw new IllegalSyntaxException(RETURN_IN_ROOT_EXCEPTION);
		}

	}

	// This method is responsible on the declaration of a variable
	private static void declareVariable(boolean isFinal, String rawDeclaration, Type varType, Scope scope,
										String
												line) throws IllegalSyntaxException {
		String[] varDeclaration = rawDeclaration.split("=");
		String varName = varDeclaration[0];
		if (!RegularExpressions.NAME_PATTERN.matcher(varName).matches())
			throw new IllegalSyntaxException(ILLEGAL_VARIABLE_NAME_EXCEPTION + ": " + line);
		if (varDeclaration.length == 1) {
			if (scope.isVarDeclaredHere(varType, varName))
				throw new IllegalSyntaxException(OVERRIDE_EXCEPTION + ": " + line);
			Variable var = new Variable(varType, varName, false, isFinal);
			scope.addVariable(var);
		} else {
			String varAssignment = varDeclaration[1];
			if (!Type.match(varAssignment, varType) &&
					!scope.isVarAssigned(varType, varAssignment))
				throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION + ": " + line);
			Variable var = new Variable(varType, varName, true, isFinal);
			scope.addVariable(var);
		}
	}

	// This method is responsible for handling assignment.
	private static void assignmentHandler(String line, Scope scope) throws IllegalSyntaxException {
		// check for variable assignment
		String[] lineContent = line.split("=");
		if (lineContent.length == 2) {
			String varName = lineContent[0];
			String varAssignment = lineContent[1];
			Type varType = scope.getVarType(varName);
			if (scope.isVarDeclared(varType, varName) && (scope.isVarAssigned(varType, varAssignment) ||
					Type.match(varAssignment, varType))) {
				Variable var = scope.getVarByName(varName, true);
				if (var == null) {
					throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION);
				}
				if (var.isFinal() && var.isAssigned())
					throw new IllegalSyntaxException(FINAL_VARIABLE_ASSIGNMENT_EXCEPTION + ": " + line);
				if(scope.getVarByName(varName,false)!=null) {
					var.setAssigned();
				}
			} else
				throw new IllegalSyntaxException(FINAL_VARIABLE_ASSIGNMENT_EXCEPTION + ": " + line);
		} else
			throw new IllegalSyntaxException(ILLEGAL_START_EXCEPTION + ": " + line);
	}

	// This method is responsible for checking a method call is legal
	private static void methodCallHandler(String line, Scope scope) throws IllegalSyntaxException {
		line = line.substring(0, line.length() - 1);
		String[] lineContent = line.split(RegularExpressions.METHOD_CALL_SPLITTER_REGEX);
		Method method = scope.getMethodByName(lineContent[0]);
		if (method == null) {
			throw new IllegalSyntaxException(METHOD_NOT_DECLARED_EXCEPTION);
		}
		ArrayList<Variable> params = method.getParams();
		if (lineContent.length != params.size() + 1) {
			throw new IllegalSyntaxException(METHOD_NOT_DECLARED_EXCEPTION);
		}
		for (int i = 1; i < lineContent.length; i++) {
			if (!Type.match(lineContent[i], params.get(i - 1).getType())) {
				Variable param = scope.getVarByName(lineContent[i], true);
				if (param == null) {
					throw new IllegalSyntaxException(METHOD_NOT_DECLARED_EXCEPTION);
				}
				if (!param.isAssigned()) {
					throw new IllegalSyntaxException(METHOD_NOT_DECLARED_EXCEPTION);
				}
				if (param.getType() != params.get(i - 1).getType()) {
					throw new IllegalSyntaxException(METHOD_NOT_DECLARED_EXCEPTION);
				}
			}
		}
	}
}
