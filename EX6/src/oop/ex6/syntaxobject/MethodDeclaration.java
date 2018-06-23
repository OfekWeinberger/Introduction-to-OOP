package oop.ex6.syntaxobject;

import oop.ex6.syntaxobject.scope.Scope;

import java.util.ArrayList;
import java.util.Arrays;

public class MethodDeclaration {

	private ArrayList<Variable> params;
	private String name;
	private static final String VARIABLE_TYPE_EXCEPTION;

	static {
		VARIABLE_TYPE_EXCEPTION = "Illegal variable type";
	}

	public MethodDeclaration(){
		params = new ArrayList<>();
		name = "";
	}

	public void check(String line, Scope scope) throws IllegalSyntaxException {
		if (line.startsWith("void")) {
			line = line.substring("void".length() + 1);
			String[] contents = line.split("(\\))|(\\()");
			System.out.println(Arrays.toString(contents));
			name = contents[0];
			System.out.println(name);
			String[] methodParams = contents[1].split(",");
			System.out.println(Arrays.toString(methodParams));
			for(String param : methodParams){
				boolean isFinal = false;
				if (param.startsWith("final")) {
					param = param.substring("final".length() + 1);
					isFinal = true;
				}
				System.out.println("param=" + param);
				if (param.startsWith("boolean ") || param.startsWith("int ") || param.startsWith("double ") ||
						param.startsWith("char ") || param.startsWith("String ")) {
					Type varType = Type.getType(param.substring(0, param.indexOf(" ")));
					String varName = param.substring(param.indexOf(" ") + 1);
					params.add(new Variable(varType, varName, true, isFinal));
				} else
					throw new IllegalSyntaxException(VARIABLE_TYPE_EXCEPTION);
			}

		}
	}

	public ArrayList<Variable> getParams() {
		return params;
	}

	public String getName() {
		return name;
	}
}
