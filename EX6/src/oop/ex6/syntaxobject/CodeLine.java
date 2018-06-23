package oop.ex6.syntaxobject;

import oop.ex6.RegularExpressions;
import oop.ex6.syntaxobject.scope.Scope;

import java.util.ArrayList;
import java.util.Arrays;

public class CodeLine extends SyntaxObject {

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

	public void check(String line, Scope scope) throws IllegalSyntaxException {
		// test for variable declaration
		boolean passed = false;
		boolean isFinal = false;
		if (line.startsWith("final")) {
			line = line.substring("final".length() + 1);
			isFinal = true;
		}
		if (line.startsWith("boolean ") || line.startsWith("int ") || line.startsWith("double ") || line
				.startsWith("char ") || line.startsWith("String ")) {
			String[] lineContent = line.split("(;)|(, )");
			String varType = lineContent[0].substring(0, lineContent[0].indexOf(" "));
			lineContent[0] = lineContent[0].substring(lineContent[0].indexOf(" ") + 1);
			for (int i = 0; i < lineContent.length; i++) {
				String[] varDeclaration = lineContent[i].split(" ");
				String varName = varDeclaration[0];

				if (!RegularExpressions.NAME_PATTERN.matcher(varName).matches()) {
					throw new IllegalSyntaxException(ILLEGAL_VARIABLE_NAME_EXCEPTION + ": " + line);
				}
				if(varDeclaration.length == 1){
					if(scope.isVarDeclaredHere(Type.getType(varType), varName)){
						throw new IllegalSyntaxException(OVERRIDE_EXCEPTION + ": " + line);
					}

					Variable var = new Variable(Type.getType(varType), varName, false, isFinal);
					scope.addVariable(var);
				} else {
					if (!"=".equals(varDeclaration[1])) {
						throw new IllegalSyntaxException(ILLEGAL_VARIABLE_NAME_EXCEPTION + ": " + line);
					}
					if (!Type.match(varDeclaration[2], Type.getType(varType)) &&
							!scope.isVarAssigned(Type.getType(varType), varDeclaration[2])) {
						throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION + ": " + line);
					}
					Variable var = new Variable(Type.getType(varType), varName, true, isFinal);
					scope.addVariable(var);
				}
			}
			passed = true;
		} else {
			// check for variable assignment
			String[] lineContent = line.split(" ");

			if (scope.isVarDecleared(scope.getVarType(lineContent[0]), lineContent[0]) &&
					(scope.isVarAssigned(scope.getVarType(lineContent[0]), lineContent[2]) ||
					Type.match(lineContent[2], scope.getVarType(lineContent[0])))) {
				Variable var = scope.getVarByName(lineContent[0], false);
				if(var.isFinal() && var.isAssigned())
					throw new IllegalSyntaxException(FINAL_VARIABLE_ASSIGNMENT_EXCEPTION + ": " + line);
				var.setAssigned();
				passed = true;
			}
		}
		System.out.println(passed);
		if (!line.startsWith("//") && !passed) {
			throw new IllegalSyntaxException(ILLEGAL_START_EXCEPTION);
		}
	}
}

/*
if (line.startsWith("if") || line.startsWith("while")) {
	String[] contents = line.split("\\(|\\)");
	Condition cond = new Condition();
	cond.check(contents[1], scope);
} */