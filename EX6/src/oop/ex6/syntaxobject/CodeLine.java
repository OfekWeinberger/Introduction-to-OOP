package oop.ex6.syntaxobject;

import oop.ex6.RegularExpressions;
import oop.ex6.syntaxobject.scope.Scope;

public class CodeLine {

	private static final String ILLEGAL_START_EXCEPTION;
	private static final String ILLEGAL_VARIABLE_EXCEPTION;
	private static final String ILLEGAL_VARIABLE_NAME_EXCEPTION;
	private static final String OVERRIDE_EXCEPTION;
	private static final String FINAL_VARIABLE_ASSIGNMENT_EXCEPTION;

	static {
		ILLEGAL_START_EXCEPTION = "The line starts with illegal word or expression";
		ILLEGAL_VARIABLE_EXCEPTION = "Illegal variable written";
		ILLEGAL_VARIABLE_NAME_EXCEPTION = "Illegal variable name";
		OVERRIDE_EXCEPTION = "Cannot override variable from the same scope";
		FINAL_VARIABLE_ASSIGNMENT_EXCEPTION = "Final variable cannot be re-assigned";
	}

	public static void check(String line, Scope scope) throws IllegalSyntaxException {
		// test for variable declaration

		boolean isFinal = false;
		if (line.startsWith("final")) {
			line = line.substring("final".length() + 1);
			isFinal = true;
		}

		if (line.startsWith("boolean ") || line.startsWith("int ") || line.startsWith("double ") ||
				line.startsWith("char ") || line.startsWith("String ")) {
			String[] lineContent = line.split("(,)|(;)");
			Type varType = Type.getType(lineContent[0].substring(0, lineContent[0].indexOf(" ")));
			lineContent[0] = lineContent[0].substring(lineContent[0].indexOf(" ") + 1);

			for (String rawDeclaration : lineContent)
				declareVariable(isFinal, rawDeclaration, varType, scope, line);

		} else if (!line.startsWith("//"))
			assignmentHandler(line, scope);
	}

	public static void declareVariable(boolean isFinal, String rawDeclaration, Type varType, Scope scope,
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

	private static void assignmentHandler(String line, Scope scope) throws IllegalSyntaxException {
		// check for variable assignment
		String[] lineContent = line.split("(=)|(;)");
		if(lineContent.length == 2) {
			String varName = lineContent[0];
			String varAssignment = lineContent[1];
			Type varType = scope.getVarType(varName);
			if (scope.isVarDecleared(varType, varName) && (scope.isVarAssigned(varType, varAssignment) ||
					Type.match(varAssignment, varType))) {
				Variable var = scope.getVarByName(varName, false);
				if (var.isFinal() && var.isAssigned())
					throw new IllegalSyntaxException(FINAL_VARIABLE_ASSIGNMENT_EXCEPTION + ": " + line);
				var.setAssigned();
			} else
				throw new IllegalSyntaxException(FINAL_VARIABLE_ASSIGNMENT_EXCEPTION + ": " + line);
		} else
			throw new IllegalSyntaxException(ILLEGAL_START_EXCEPTION + ": " + line);
	}
}
