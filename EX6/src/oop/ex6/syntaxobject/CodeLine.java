package oop.ex6.syntaxobject;

import oop.ex6.syntaxobject.scope.Root;
import oop.ex6.syntaxobject.scope.Scope;

public class CodeLine extends SyntaxObject {

	private static final String ILLEGAL_START_EXCEPTION;
	private static final String ILLEGAL_VARIABLE_EXCEPTION;

	static {
		ILLEGAL_START_EXCEPTION = "The line starts with illegal word or expression";
		ILLEGAL_VARIABLE_EXCEPTION = "Illegal variable written";
	}

	public CodeLine(String line, Scope scope) throws IllegalSyntaxException {
		if (line.startsWith("//")) {
			// comment line
		} else if (line.startsWith("if")) {
			String[] contents = line.split("\\(|\\)");
			Condition condition = new Condition(contents[1], scope);
		} else if (line.startsWith("while")) {
			String[] contents = line.split("\\(|\\)");
			Condition condition = new Condition(contents[1], scope);
		} else if (line.startsWith("void")) {
			line = line.substring(5, line.length());
			String[] contents = line.split("|\\(|\\)");
			MethodDeclaration md = new MethodDeclaration(contents[0], contents[1].split(", "), scope);
		} else {
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
						Type t;
						switch (varDeclaration[0]) {
							case "boolean":
								t = Type.BOOLEAN;
								if (!Type.BOOLEAN.match(varDeclaration[3]) && !scope.isAssigned(Type.BOOLEAN,
										varDeclaration[3])) {
									throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION + ": " + line);
								}
								break;
							case "int":
								t = Type.INT;
								if (!Type.INT.match(varDeclaration[3]) && !scope.isAssigned(Type.BOOLEAN,
										varDeclaration[3])) {
									throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION + ": " + line);
								}
								break;
							case "double":
								t = Type.DOUBLE;
								if (!Type.DOUBLE.match(varDeclaration[3]) && !scope.isAssigned(Type.BOOLEAN,
										varDeclaration[3])) {
									throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION + ": " + line);
								}
								break;
							case "char":
								t = Type.CHAR;
								if (!Type.CHAR.match(varDeclaration[3]) && !scope.isAssigned(Type.BOOLEAN,
										varDeclaration[3])) {
									throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION + ": " + line);
								}
								break;
							case "String":
								t = Type.STRING;
								if (!Type.STRING.match(varDeclaration[3]) && !scope.isAssigned(Type.BOOLEAN,
										varDeclaration[3])) {
									throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION + ": " + line);
								}
								break;
						}
						Variable var = new Variable(Type.BOOLEAN, varName, isAssigned, isFinal);
						scope.addVariable(var);
					}
				}
			} else {
				// check for variable assignment
				String[] lineContent = line.split(" ");
				//TODO: the function that checks if a variable is declared if
				if (scope.isDecleared(lineContent[0]) && /*some way to if valid*/){
					//set variable to assigned
				}
				throw new IllegalSyntaxException(ILLEGAL_START_EXCEPTION);
			}
		}
	}
}
