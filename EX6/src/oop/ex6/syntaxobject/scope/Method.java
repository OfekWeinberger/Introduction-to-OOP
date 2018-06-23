package oop.ex6.syntaxobject.scope;

import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.Type;
import oop.ex6.syntaxobject.Variable;

import java.util.ArrayList;
import java.util.Arrays;

public class Method extends Scope {
	private String name;
	private ArrayList<Variable> params;


	public Method(String name, ArrayList<Variable> params, ArrayList<String> lines) throws IllegalSyntaxException {
		super(Root.instance(), null, null, lines);
		this.name = name;
		if(params == null){
			params = new ArrayList<Variable>();
		}
		else {
			this.params = params;
		}
		for (Variable var:params){
			addVariable(var);
		}
	}

	public String getName() {
		return name;
	}

	public boolean checkParams(ArrayList<Type> paramsToCheck) {
		if (paramsToCheck.size() != params.size()) {
			return false;
		}
		for (int i = 0; i < params.size(); i++) {
			if (!paramsToCheck.get(i).equals(params.get(i).getType())) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<Variable> getParams(){
		return new ArrayList<Variable>(params);
	}

	@Override
	public String toString() {
		String str = name;
		for (Variable var: params){
			str+=" ,"+var.getType().getClass();
		}
		return str;
	}
}
