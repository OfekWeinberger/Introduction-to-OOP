package oop.ex6.syntaxobject;

public class Variable {

	private Type type;
	private String name;
	private boolean assigned;

	public Variable(Type type, String name, boolean assigned) {
		this.type = type;
		this.name = name;
		this.assigned = assigned;
	}

	public Type getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setValue(String value) {
		this.name = value;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}
}
