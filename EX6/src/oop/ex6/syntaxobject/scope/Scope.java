package oop.ex6.syntaxobject.scope;

import oop.ex6.syntaxobject.Variable;

import java.util.ArrayList;

public abstract class Scope {

	private Scope parent;
	private ArrayList<Scope> children;
	private ArrayList<Variable> variables;

	public Scope(Scope parent, ArrayList<Scope> children, ArrayList<Variable> variables) {
		this.parent = parent;
		this.children = children;
		this.variables = variables;
	}

	public Scope getParent() {
		return parent;
	}

	public ArrayList<Scope> getChildren() {
		return children;
	}

	public ArrayList<Variable> getVariables() {
		return variables;
	}

	public void setVariables(ArrayList<Variable> variables) {
		this.variables = variables;
	}
}
