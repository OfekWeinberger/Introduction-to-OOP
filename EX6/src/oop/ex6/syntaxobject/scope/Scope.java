package oop.ex6.syntaxobject.scope;

import oop.ex6.syntaxobject.Type;
import oop.ex6.syntaxobject.Variable;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Scope {

	private Scope parent;
	private ArrayList<Scope> children;
	private HashMap<String, Variable> variables;
	private ArrayList<String> lines;

	public Scope() {
		this.parent = null;
		this.children = null;
		this.variables = null;
	}

	public Scope(Scope parent, ArrayList<Scope> children, ArrayList<Variable> variables, ArrayList<String> lines) {
		this.parent = parent;
		this.children = children;
		this.setVariables(variables);
		this.lines = lines;
	}

	public Scope getParent() {
		return parent;
	}

	public ArrayList<Scope> getChildren() {
		return children;
	}

	public void addChild(Scope child) {
		children.add(child);
		child.parent = this;
	}

	public ArrayList<Variable> getVariables() {
		ArrayList<Variable> vars = new ArrayList<Variable>(variables.values());
		return vars;
	}

	public void setVariables(ArrayList<Variable> variables) {
		for (Variable var : variables) {
			this.variables.put(var.getName(), var);
		}
	}

	public void addVariable(Variable variable) {
		this.variables.put(variable.getName(), variable);
	}

	public boolean isAssigned(Type type, String varName) {
		if (variables.containsKey(varName)) {
			Variable var = variables.get(varName);
			if (var.getType().equals(type)) {
				return true;
			} else {//var has the dame name but the wrong type
				return false;
			}
		}
		if (isRoot()) {
			return false;
		}
		return parent.isAssigned(type, varName);
	}

	public boolean isDecleared(String methodName, ArrayList<Type> params) {
		Root root = Root.Instant();
		return root.isDecleared(methodName, params);
	}

	private boolean isRoot() {
		if (parent == null) {
			return true;
		}
		return false;
	}
}
