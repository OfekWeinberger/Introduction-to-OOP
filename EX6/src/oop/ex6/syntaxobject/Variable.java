package oop.ex6.syntaxobject;

public class Variable extends SyntaxObject{

	private final boolean FINAL;
	private Type type;
	private String name;
	private boolean assigned;

	public Variable(Type type, String name, boolean assigned, boolean FINAL) throws IllegalSyntaxException {
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
