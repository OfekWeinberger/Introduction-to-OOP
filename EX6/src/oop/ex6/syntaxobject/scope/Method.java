package oop.ex6.syntaxobject.scope;

import oop.ex6.syntaxobject.Type;

import java.util.ArrayList;
import java.util.Arrays;

public class Method extends Scope {
	private String name;
	private ArrayList<Type> params;


	public Method(String name, ArrayList<Type> params,ArrayList<String> lines) {
		super(Root.instance(), null, null, lines);
		this.name = name;
		this.params = params;
	}

	public String getName() {
		return name;
	}

	public boolean checkParams(ArrayList<Type> paramsToCheck) {
		if (paramsToCheck.size() != params.size()) {
			return false;
		}
		for (int i = 0; i < params.size(); i++) {
			if (!paramsToCheck.get(i).equals(params.get(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		String str = name;
		for (Type t: params){
			str+=" ,"+t.getClass();
		}
		return str;
	}
}
