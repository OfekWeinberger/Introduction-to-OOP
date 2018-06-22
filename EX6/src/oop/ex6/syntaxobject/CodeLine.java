package oop.ex6.syntaxobject;

import oop.ex6.syntaxobject.scope.Scope;

public class CodeLine extends SyntaxObject {

	private static final String ILLEGAL_START_EXCEPTION;
	private static final String ILLEGAL_VARIABLE_EXCEPTION;

	static {
		ILLEGAL_START_EXCEPTION = "The line starts with illegal word or expression";
		ILLEGAL_VARIABLE_EXCEPTION = "Illegal variable written";
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
			String[] lineContent = line.split(",");
			if (lineContent.length > 1) {
				String varType = lineContent[0].substring(lineContent[0].indexOf(" ") + 1);
				for (String s : lineContent) {
					String[] varDeclaration = s.split(" ");
					String varName = varDeclaration[0];

					boolean isAssigned = false;
					if (varDeclaration.length >= 3 && "=".equals(varDeclaration[2])) {
						isAssigned = true;
					}
					if (!Type.match(varDeclaration[3], varDeclaration[0]) || !scope.isAssigned(Type
							.getType(varDeclaration[0]), varDeclaration[3])) {
						throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION + ": " + line);
					}
					Variable var = new Variable(Type.BOOLEAN, varName, isAssigned, isFinal);
					scope.addVariable(var);
				}
			}
		} else {
			// check for variable assignment
			String[] lineContent = line.split(" ");
			//TODO: the function that checks if a variable is declared if
			if (scope.isVarDecleared(lineContent[0]) && Type.match(lineContent[2],
					scope.getVarType(lineContent[0]))) {
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