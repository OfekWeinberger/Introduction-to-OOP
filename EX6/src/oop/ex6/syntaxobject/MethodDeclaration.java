package oop.ex6.syntaxobject;

import oop.ex6.syntaxobject.scope.Scope;

import java.util.ArrayList;
import java.util.Arrays;

public class MethodDeclaration extends SyntaxObject {

	private ArrayList<Variable> params;
	private String name;

	public MethodDeclaration(){
		params = new ArrayList<>();
		name = "";
	}
	@Override
	public void check(String line, Scope scope) throws IllegalSyntaxException {
		if (line.startsWith("void")) {
			line = line.substring("void".length() + 1);
			String[] contents = line.split("(\\))|(\\()");
			System.out.println(Arrays.toString(contents));
			name = contents[0];
			System.out.println(name);
			String[] methodParams = contents[1].split(", ");
			System.out.println(Arrays.toString(methodParams));
		}
	}

	public ArrayList<Variable> getParams() {
		return params;
	}

	public String getName() {
		return name;
	}
}
