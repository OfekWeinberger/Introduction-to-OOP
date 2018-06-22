package oop.ex6.syntaxobject;

import oop.ex6.RegularExpressions;
import oop.ex6.syntaxobject.scope.Scope;

import java.util.Arrays;

public class CodeLine extends SyntaxObject {

	private static final String ILLEGAL_START_EXCEPTION;
	private static final String ILLEGAL_VARIABLE_EXCEPTION;
	private static final String ILLEGAL_VARIABLE_NAME_EXCEPTION;

	static {
		ILLEGAL_START_EXCEPTION = "The line starts with illegal word or expression";
		ILLEGAL_VARIABLE_EXCEPTION = "Illegal variable written";
		ILLEGAL_VARIABLE_NAME_EXCEPTION = "Illegal variable name";
	}

	public void check(String line, Scope scope) throws IllegalSyntaxException {
		// test for variable declaration
		boolean isFinal = false;
		if (line.startsWith("final")) {
			line = line.substring(0, 5);
			isFinal = true;
		}
		if (line.startsWith("boolean ") || line.startsWith("int ") || line.startsWith("double ") || line
				.startsWith("char ") || line.startsWith("String ")) {
			String[] lineContent = line.split("[,;]");
			System.out.println(lineContent[0]);
			String varType = lineContent[0].substring(0, lineContent[0].indexOf(" ") + 1);
			for (int i = 1; i < lineContent.length; i++) {
				System.out.println(lineContent[i]);
				String[] varDeclaration = lineContent[i].split(" ");
				Arrays.toString(varDeclaration);
				String varName = varDeclaration[0];
				boolean isAssigned = false;
				if (varDeclaration.length >= 3 && "=".equals(varDeclaration[2])) {
					isAssigned = true;
				}
				if (!RegularExpressions.NAME_PATTERN.matcher(varName).matches()) {
					throw new IllegalSyntaxException(ILLEGAL_VARIABLE_NAME_EXCEPTION + ": " + line);
				}
				if (!Type.match(varDeclaration[2], Type.getType(varType)) || !scope.isAssigned(Type
						.getType(varType), varDeclaration[3])) {
					throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION + ": " + line);
				}
				Variable var = new Variable(Type.BOOLEAN, varName, isAssigned, isFinal);
				scope.addVariable(var);
			}

		} else {
			// check for variable assignment
			String[] lineContent = line.split(" ");
			//TODO: the function that checks if a variable is declared if
			if (/*scope.isVarDecleared(lineContent[0]) && */Type.match(lineContent[2], scope.getVarType
					(lineContent[0]))) {
				//set variable to assigned
			}

		}
		if (line.startsWith("//")) {
			throw new IllegalSyntaxException(ILLEGAL_START_EXCEPTION);
		}
	}
}

/*
if (line.startsWith("if") || line.startsWith("while")) {
	String[] contents = line.split("\\(|\\)");
	Condition cond = new Condition();
	cond.check(contents[1], scope);
} else if (line.startsWith("void")) {
	line = line.substring(5, line.length());
	String[] contents = line.split("|\\(|\\)");
	MethodDeclaration md = new MethodDeclaration(contents[0], contents[1].split(", "), scope);
}
 */