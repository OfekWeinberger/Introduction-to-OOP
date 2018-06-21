package oop.ex6.syntaxobject.scope;

import oop.ex6.syntaxobject.Type;

import java.util.ArrayList;

public class Method extends Scope {
	private String name;
	private ArrayList<Type> params;


	public Method(String name, ArrayList<Type> params) {
		super(Root.Instant(), null, null, null);
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
}
