package oop.ex6.syntaxobject;

public class Variable {

	private Type type;
	private String value;
	private boolean assigned;

	public Variable(Type type, String value, boolean assigned) {
		this.type = type;
		this.value = value;
		this.assigned = assigned;
	}

	public Type getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}
}
