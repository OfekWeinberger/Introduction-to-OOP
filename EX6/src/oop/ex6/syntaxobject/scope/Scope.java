package oop.ex6.syntaxobject.scope;

import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.Type;
import oop.ex6.syntaxobject.Variable;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Scope {

	private static final String VARIABLE_CROSS_NAME_EXCEPTION = "Two variabels in the same scope canot have the same name";
	private Scope parent;
	private ArrayList<Scope> children;
	private HashMap<String, Variable> variables;
	private ArrayList<String> lines;

	/**
	 * default constructor for a scope object. initialize the collections.
	 */
	public Scope() {
		this.parent = null;
		this.children = new ArrayList<Scope>();
		this.variables = new HashMap<String, Variable>();
		this.lines = new ArrayList<String>();
	}

	/**
	 * constructor for a scope object
	 *
	 * @param parent    - the parent scope in the scope hirarchi
	 * @param children  - the scopes inside this scope
	 * @param variables - the variables that are decleared in this scope
	 * @param lines     - the lines of code this scope contains
	 */
	public Scope(Scope parent, ArrayList<Scope> children, ArrayList<Variable> variables, ArrayList<String> lines) {
		this.parent = parent;
		if (children == null) {
			this.children = new ArrayList<Scope>();
		} else {
			this.children = children;
		}
		if (variables == null) {
			this.variables = new HashMap<String, Variable>();
		} else {
			this.setVariables(variables);
		}
		if (lines == null) {
			this.lines = new ArrayList<String>();
		} else {
			this.lines = lines;
		}
	}

	/**
	 * returnn the lines if coed in this scope
	 *
	 * @return ArratList of the line. each line is a string
	 */
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

	/**
	 * add a list of variebles to the variabels list
	 *
	 * @param variables - the variables to add
	 */
	public void setVariables(ArrayList<Variable> variables) {
		if (variables != null) {
			for (Variable var : variables) {
				this.variables.put(var.getName(), var);
			}
		}
	}

	/**
	 * add a singal variable to the variable list
	 *
	 * @param variable - the variable to add
	 * @throws IllegalSyntaxException
	 */
	public void addVariable(Variable variable) throws IllegalSyntaxException {
		if (this.variables.containsKey(variable.getName())) {
			throw new IllegalSyntaxException(VARIABLE_CROSS_NAME_EXCEPTION);
		}
		this.variables.put(variable.getName(), variable);
	}

	/**
	 * check if there is a variable assigned with a specific type and name
	 *
	 * @param type    - the type of the variable
	 * @param varName - the variable name
	 * @return true if there is a variable assigned somewhere up the scopes tree else false
	 */
	public boolean isVarAssigned(Type type, String varName) {
		Variable var = getVarByName(varName, true);
		if (var == null) {//var is not decleared at all
			return false;
		}
		if (type.equals(var.getType()) && var.isAssigned()) {
			return true;
		} else {//var has the dame name but the wrong type
			return false;
		}
	}

	/**
	 * check if there is a variable decleared with a specific type and name
	 *
	 * @param type    - the type of the variable
	 * @param varName - the variable name
	 * @return true if there is a variable decleared somewhere up the scopes tree else false
	 */
	public boolean isVarDecleared(Type type, String varName) {
		Variable var = getVarByName(varName, true);
		if (var == null) {//var is not decleared at all
			return false;
		}
		if (var.getType().equals(type)) {
			return true;
		} else {//var has the dame name but the wrong type
			return false;
		}
	}

	/**
	 * check if there is a variable decleared with a specific type and name
	 *
	 * @param type    - the type of the variable
	 * @param varName - the variable name
	 * @return true if there is a variable decleared sin this specific scope else false
	 */
	public boolean isVarDeclaredHere(Type type, String varName) {
		Variable var = getVarByName(varName, false);
		if (var == null) {//var is not decleared at all
			return false;
		}
		if (var.getType().equals(type)) {
			return true;
		} else {//var has the dame name but the wrong type
			return false;
		}
	}


	/**
	 * get a variable type by its name
	 *
	 * @param varName - the variable name
	 * @return the type of the variable null if the variable is null
	 */
	public Type getVarType(String varName) {
		Variable var = getVarByName(varName, true);
		if (var == null) {
			return null;
		}
		return var.getType();
	}


	/**
	 * search for a variable by its name.
	 *
	 * @param varName    - the variable name
	 * @param deepSearch - if to search up the scope tree or not
	 * @return the most deep Variable in the scope tree with that name if found else null.
	 */
	public Variable getVarByName(String varName, boolean deepSearch) {
		if (variables != null) {
			if (variables.containsKey(varName)) {
				return variables.get(varName);
			}
		}
		if (deepSearch) {
			if (!isRoot()) {
				return parent.getVarByName(varName, deepSearch);
			}
		}
		return null;
	}


	/**
	 * search for a method by its name in the root scope
	 *
	 * @param methodName - the method name
	 * @return the method object if found else null
	 */
	public Method getMethodByName(String methodName) {
		return Root.instance().getMethodByName(methodName);
	}

	public boolean isDeclared(String methodName, ArrayList<Type> params) {
		Root root = Root.instance();
		return root.isDeclared(methodName, params);
	}

	/**
	 * check if the scope is the root scope
	 *
	 * @return - if the scope have no parent scope
	 */
	public boolean isRoot() {
		if (parent == null) {
			return true;
		}
		return false;
	}
}
