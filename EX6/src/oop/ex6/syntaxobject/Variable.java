package oop.ex6.syntaxobject;

public class Variable {

	private Type type;
	private String name;
	private boolean assigned;
	private boolean FINAL;

	public Variable(Type type, String name, boolean assigned, boolean FINAL) {
		this.type = type;
		this.name = name;
		this.assigned = assigned;
		this.FINAL = FINAL;
	}

	public Type getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public boolean isFINAL() {
		return FINAL;
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
