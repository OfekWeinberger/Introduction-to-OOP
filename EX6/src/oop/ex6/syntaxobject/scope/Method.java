package oop.ex6.syntaxobject.scope;

import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.Type;
import oop.ex6.syntaxobject.Variable;

import java.util.ArrayList;
import java.util.Arrays;

public class Method extends Scope {
	private String name;
	private ArrayList<Variable> params;


	/**
	 * a constractor for the method class
	 * @param name - the method name
	 * @param params - the parameters a mehtod should get when called upon
	 * @param lines - the lines inside the method scope
	 * @throws IllegalSyntaxException
	 */
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

	/**
	 *
	 * @return the method name
	 */
	public String getName() {
		return name;
	}

	/**
	 * check if a list of variable types is maching the method signiture
	 * @param paramsToCheck - the variables Type in an ArrayList
	 * @return true if match false otherwise
	 */
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

	/**
	 * return a list of variables that the method expect to get
	 * @return an array list of Variables
	 */
	public ArrayList<Variable> getParams(){
		return new ArrayList<Variable>(params);
	}

	public String toString() {
		String str = name;
		for (Variable var: params){
			str+=" ,"+var.getType().getClass();
		}
		return str;
	}
}
