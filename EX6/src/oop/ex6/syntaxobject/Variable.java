package oop.ex6.syntaxobject;

public class Variable{

	private final boolean FINAL;
	private Type type;
	private String name;
	private boolean assigned;
	private static final String CONST_EXCEPTION = "final variabel mast be initialized at decleration";

	public Variable(Type type, String name, boolean assigned, boolean FINAL) throws IllegalSyntaxException {
		this.type = type;
		this.name = name;
		this.assigned = assigned;
		this.FINAL = FINAL;
		if(FINAL&&!assigned){
			throw new IllegalSyntaxException(CONST_EXCEPTION);
		}
	}

	public Type getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public boolean isFinal() {
		return FINAL;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned() {
		this.assigned = true;
	}

	@Override
	public String toString() {
		return "Variable{" +
				"FINAL=" + FINAL +
				", type=" + type +
				", name='" + name + '\'' +
				", assigned=" + assigned +
				'}';
	}
}
