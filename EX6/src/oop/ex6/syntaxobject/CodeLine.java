package oop.ex6.syntaxobject;

import oop.ex6.syntaxobject.scope.Scope;

public class CodeLine extends SyntaxObject{

	private static final String ILLEGAL_START_EXCEPTION;
	private static final String ILLEGAL_BOOLEAN_EXCEPTION;

	static {
		ILLEGAL_START_EXCEPTION = "The line starts with illegal word or expression";
		ILLEGAL_BOOLEAN_EXCEPTION = "Illegal boolean written";
	}

	public CodeLine(String line, Scope scope) throws IllegalSyntaxException {
		if(line.startsWith("if")){
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
			boolean isFinal = false;
			if(line.startsWith("final")){
				line = line.substring(0, 5);
				isFinal = true;
			}
			if(line.startsWith("boolean")){
				String[] lineContent = line.split(" ");
				String varName = lineContent[1];
				boolean isAssigned = "=".equals(lineContent[2]);
				if (Type.BOOLEAN.match(lineContent[3])){
					throw new IllegalSyntaxException(ILLEGAL_BOOLEAN_EXCEPTION + ": " + line);
				}
				Variable var = new Variable(Type.BOOLEAN, varName, isAssigned, isFinal);
			} else if(line.startsWith("int")){

			} else if(line.startsWith("double")){

			} else if(line.startsWith("char")){

			} else if(line.startsWith("String")) {

			} else {
				throw new IllegalSyntaxException(ILLEGAL_START_EXCEPTION);
			}
		}
	}
}
