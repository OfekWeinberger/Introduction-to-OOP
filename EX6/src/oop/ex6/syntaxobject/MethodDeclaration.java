package oop.ex6.syntaxobject;

import oop.ex6.RegularExpressions;
import oop.ex6.syntaxobject.scope.Scope;

import java.util.ArrayList;

public class MethodDeclaration {

	private static final String VARIABLE_TYPE_EXCEPTION;
	private static final String ILLEGAL_METHOD_NAME_EXCEPTION;
	private static String VOID_KEYWORD;
	private static String METHOD_BRACKET_SPLITTER_REGEX;
	private static String PARAMETER_SPACER_CHARACTER;
	private static String ILLEGAL_SCOPE;

	static {
		VARIABLE_TYPE_EXCEPTION = "Illegal variable type or name";
		ILLEGAL_METHOD_NAME_EXCEPTION = "Illegal method name";
		ILLEGAL_SCOPE = "Illegal scope for method declaration";
		VOID_KEYWORD = "void";
		METHOD_BRACKET_SPLITTER_REGEX = "(\\))|(\\()";
		PARAMETER_SPACER_CHARACTER = ",";

	}

	private ArrayList<Variable> params;
	private String name;

	public MethodDeclaration() {
		params = new ArrayList<>();
		name = "";
	}

	public void check(String line, Scope scope) throws IllegalSyntaxException {
		if(!scope.isRoot()){
			throw new IllegalSyntaxException(ILLEGAL_SCOPE);
		}
		if (line.startsWith(VOID_KEYWORD)) {
			line = line.substring(VOID_KEYWORD.length() + 1);
			String[] contents = line.split(METHOD_BRACKET_SPLITTER_REGEX);
			if (contents.length != 3) {
				throw new IllegalSyntaxException(ILLEGAL_METHOD_NAME_EXCEPTION + ": " + line);
			}
			name = contents[0];
			if (!RegularExpressions.METHOD_NAME_PATTERN.matcher(name).matches())
				throw new IllegalSyntaxException(ILLEGAL_METHOD_NAME_EXCEPTION + ": " + line);
			if (!RegularExpressions.PARAMS_PATTERN.matcher(contents[1]).matches()) {
				throw new IllegalSyntaxException(VARIABLE_TYPE_EXCEPTION + ": " + line);
			}
			String[] methodParams = contents[1].split(PARAMETER_SPACER_CHARACTER);
			if (!Type.EMPTY_STRING.equals(methodParams[0])) {
				for (String param : methodParams)
					declareMethodParams(param, line);
			}
		}
	}

	private void declareMethodParams(String param, String line) throws IllegalSyntaxException {
		boolean isFinal = false;
		if (param.startsWith(Type.FINAL_MODIFIER)) {
			param = param.substring(Type.FINAL_MODIFIER.length() + 1);
			isFinal = true;
		}
		if (line.contains(Type.SPACE_CHARACTER) && Type.getType(line.substring(0, line.indexOf
				(Type.SPACE_CHARACTER))) != null) {
			Type varType = Type.getType(param.substring(0, param.indexOf(Type.SPACE_CHARACTER)));
			String varName = param.substring(param.indexOf(Type.SPACE_CHARACTER) + 1);
			if (!RegularExpressions.NAME_PATTERN.matcher(varName).matches()) {
				throw new IllegalSyntaxException(VARIABLE_TYPE_EXCEPTION + ": " + line);
			}
			params.add(new Variable(varType, varName, true, isFinal));
		} else
			throw new IllegalSyntaxException(VARIABLE_TYPE_EXCEPTION + ": " + line);
	}

	public ArrayList<Variable> getParams() {
		return params;
	}

	public String getName() {
		return name;
	}
}
