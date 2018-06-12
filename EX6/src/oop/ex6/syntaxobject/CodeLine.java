package oop.ex6.syntaxobject;

import oop.ex6.syntaxobject.scope.Scope;

public class CodeLine extends SyntaxObject{

	public CodeLine(String line, Scope scope) throws IllegalSyntaxException {
		if(line.startsWith("if")){
			String[] contents = line.split("\\(|\\)");
			Condition condition = new Condition(contents[1], scope);
		} else if(line.startsWith("while")){
			String[] contents = line.split("\\(|\\)");
			Condition condition = new Condition(contents[1], scope);
		} else if(line.startsWith("void")){
			String[] contents = line.split(" |");
		} else if(line.startsWith("boolean")){

		} else
			throw new IllegalSyntaxException();
	}
}
