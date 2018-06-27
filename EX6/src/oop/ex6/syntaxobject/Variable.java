package oop.ex6.syntaxobject;

public class Variable {


	private static final String CONST_EXCEPTION = "final variable must be initialized at declaration";

	/**
	 * flag that holds if the variable is final.
	 */
	private final boolean FINAL;
	/**
	 * The type of the variable.
	 */
	private Type type;
	/**
	 * The name of the variable.
	 */
	private String name;
	/**
	 * flag that holds if the variable is assigned.
	 */
	private boolean assigned;


	/**
	 * The constructor for the variable.
	 *
	 * @param type     The type of the variable.
	 * @param name     The name of the variable.
	 * @param assigned flag that holds if the variable is assigned.
	 * @param FINAL    flag that holds if the variable is final.
	 * @throws IllegalSyntaxException if there is a final variable that is not assigned at declaration,
	 *                                throw an IllegalSyntaxException, because that is illegal in the s-Java specifications.
	 */
	public Variable(Type type, String name, boolean assigned, boolean FINAL) throws IllegalSyntaxException {
		this.type = type;
		this.name = name;
		this.assigned = assigned;
		this.FINAL = FINAL;
		if (FINAL && !assigned) {
			throw new IllegalSyntaxException(CONST_EXCEPTION);
		}
	}

	/**
	 * Getter for the type.
	 *
	 * @return type of the variable.
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Getter for the name.
	 *
	 * @return name of the variable.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the FINAL.
	 *
	 * @return if variable is final.
	 */
	public boolean isFinal() {
		return FINAL;
	}

	/**
	 * Getter for the assigned.
	 *
	 * @return if variable is assigned.
	 */
	public boolean isAssigned() {
		return assigned;
	}

	/**
	 * Setter for the assigned.
	 */
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
