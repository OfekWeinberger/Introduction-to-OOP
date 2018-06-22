package oop.ex6.syntaxobject.scope;

import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.Type;
import oop.ex6.syntaxobject.Variable;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Scope {

	private Scope parent;
	private ArrayList<Scope> children;
	private HashMap<String, Variable> variables;
	private ArrayList<String> lines;
	private static final String VARIABLE_CROSS_NAME_EXCEPTION= "Two variabels in the same scope canot have the same name";

	public Scope() {
		this.parent = null;
		this.children = new ArrayList<Scope>();
		this.variables = new HashMap<String, Variable>();
		this.lines = new ArrayList<String>();
	}

	public Scope(Scope parent, ArrayList<Scope> children, ArrayList<Variable> variables, ArrayList<String> lines) {
		this.parent = parent;
		if(children == null) {
			this.children = new ArrayList<Scope>();
		}
		else {
			this.children = children;
		}
		if(variables == null) {
			this.variables = new HashMap<String, Variable>();
		}
		else {
			this.setVariables(variables);
		}
		if(lines == null) {
			this.lines = new ArrayList<String>();
		}
		else {
			this.lines = lines;
		}
	}

	public ArrayList<String> getLines() {
		return lines;
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
		if(variables!=null) {
			for (Variable var : variables) {
				this.variables.put(var.getName(), var);
			}
		}
	}

	public void addVariable(Variable variable) throws IllegalSyntaxException {
		if(this.variables.containsKey(variable.getName())){
			throw new IllegalSyntaxException(VARIABLE_CROSS_NAME_EXCEPTION);
		}
		this.variables.put(variable.getName(), variable);
	}

	public boolean isVarAssigned(Type type, String varName) {
		Variable var = getVarByName(varName);
		if(var == null){//var is not decleared at all
			return false;
		}
		if (var.getType().equals(type) && var.isAssigned()) {
			return true;
		} else {//var has the dame name but the wrong type
			return false;
		}
	}

	public boolean isVarDecleared(Type type,String varName){
		Variable var = getVarByName(varName);
		if(var==null){//var is not decleared at all
			return false;
		}
		if (var.getType().equals(type)) {
			return true;
		} else {//var has the dame name but the wrong type
			return false;
		}
	}

	public Type getVarType(String varName){
		Variable var = getVarByName(varName);
		if(var == null){
			return null;
		}
		return var.getType();
	}

	private Variable getVarByName(String varName){
		if (variables != null) {
			if (variables.containsKey(varName)) {
				return variables.get(varName);
			}
		}
		if(!isRoot()) {
			return parent.getVarByName(varName);
		}
		return null;
	}

	public boolean isDecleared(String methodName, ArrayList<Type> params) {
		Root root = Root.instance();
		return root.isDecleared(methodName, params);
	}

	public boolean isRoot() {
		if (parent == null) {
			return true;
		}
		return false;
	}
}
