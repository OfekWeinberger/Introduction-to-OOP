package oop.ex6.syntaxobject;

import com.sun.deploy.security.ValidationState;
import oop.ex6.syntaxobject.scope.Scope;

public class CodeLine extends SyntaxObject{

	private static final String ILLEGAL_START_EXCEPTION;
	private static final String ILLEGAL_VARIABLE_EXCEPTION;

	static {
		ILLEGAL_START_EXCEPTION = "The line starts with illegal word or expression";
		ILLEGAL_VARIABLE_EXCEPTION = "Illegal variable written";
	}

	public CodeLine(String line, Scope scope) throws IllegalSyntaxException {
		if(line.startsWith("//")){
			// comment line
		} else if(line.startsWith("if")){
			String[] contents = line.split("\\(|\\)");
			Condition condition = new Condition(contents[1], scope);
		} else if(line.startsWith("while")){
			String[] contents = line.split("\\(|\\)");
			Condition condition = new Condition(contents[1], scope);
		} else if(line.startsWith("void")){
			line = line.substring(5, line.length());
			String[] contents = line.split("|\\(|\\)");
			MethodDeclaration md = new MethodDeclaration(contents[0], contents[1].split(", "), scope);
		} else{
			// test for variable declaration
			boolean isFinal = false;
			if(line.startsWith("final")){
				line = line.substring(0, 5);
				isFinal = true;
			}
			if(line.startsWith("boolean") || line.startsWith("int") || line.startsWith("double") || line
					.startsWith("char") || line.startsWith("String")){
				String[] lineContent = line.split(" ");
				String varName = lineContent[1];

				boolean isAssigned = false;
				if(lineContent.length >= 3 &&"=".equals(lineContent[2])){
					isAssigned = true;
				}
				Type t;
				switch (lineContent[0]) {
					case "boolean":
						t = Type.BOOLEAN;
						if (!Type.BOOLEAN.match(lineContent[3]) && !scope.isAssigned(Type.BOOLEAN,
							lineContent[3])) {
							throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION + ": " + line);
						}
						break;
					case "int":
						t = Type.INT;
						if (!Type.INT.match(lineContent[3]) && !scope.isAssigned(Type.BOOLEAN,
								lineContent[3])) {
							throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION + ": " + line);
						}
						break;
					case "double":
						t = Type.DOUBLE;
						if (!Type.DOUBLE.match(lineContent[3]) && !scope.isAssigned(Type.BOOLEAN,
								lineContent[3])) {
							throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION + ": " + line);
						}
						break;
					case "char":
						t = Type.CHAR;
						if (!Type.CHAR.match(lineContent[3]) && !scope.isAssigned(Type.BOOLEAN,
								lineContent[3])) {
							throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION + ": " + line);
						}
						break;
					case "String":
						t = Type.STRING;
						if (!Type.STRING.match(lineContent[3]) && !scope.isAssigned(Type.BOOLEAN,
								lineContent[3])) {
							throw new IllegalSyntaxException(ILLEGAL_VARIABLE_EXCEPTION + ": " + line);
						}
						break;
				}
				Variable var = new Variable(Type.BOOLEAN, varName, isAssigned, isFinal);
			} else {
				// check for variable assignment
				String[] lineContent = line.split(" ");
				if(//TODO: check for only declared variable in scope)
				throw new IllegalSyntaxException(ILLEGAL_START_EXCEPTION);
			}
		}
	}
}
