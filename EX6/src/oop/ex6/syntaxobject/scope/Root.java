package oop.ex6.syntaxobject.scope;

import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class Root extends Scope {
	private static final String METHOD_OVERLOAD_EXCEPTION = "Method overloading is not allowed";
	public static Root singleton = null;
	private HashMap<String, Method> methodsDeclared;

	/**
	 * a constractor for the root scope
	 * @param lines - all the code lines
	 */
	public Root(ArrayList<String> lines) {
		super(null,null,null,lines);
		methodsDeclared = new HashMap<String, Method>();
	}

	public void reset(ArrayList<String> lines){
		singleton = new Root(lines);
	}

	/**
	 * get all the methods declared
	 * @return an ArrayList of method object declared in the root scope
	 */
	public ArrayList<Method> getMethods(){
		return new ArrayList<Method>(methodsDeclared.values());
	}

	/**
	 * search for a method by name
	 * @param methodName - the method name
	 * @return the method if found null else
	 */
	@Override
	public Method getMethodByName(String methodName){
		return methodsDeclared.get(methodName);
	}

	/**
	 * initialize an the single scope. if it is initialized return it
	 * @param lines - the code lines
	 * @return the single instance of this class
	 */
	public static Root instance(ArrayList<String> lines) {
		if (singleton == null) {
			singleton = new Root(lines);
		}
		return singleton;
	}

	/**
	 *
	 * @return the singale instance of this class
	 */
	public static Root instance(){
		return singleton;
	}

	/**
	 * add a method
	 * @param method - the method object to add to the list
	 * @throws IllegalSyntaxException
	 */
	public static void addMethod(Method method) throws IllegalSyntaxException {
		if (singleton.methodsDeclared.containsKey(method.getName())) {
			throw new IllegalSyntaxException(METHOD_OVERLOAD_EXCEPTION);
		}
		singleton.methodsDeclared.put(method.getName(), method);
	}

	/**
	 * check if a specific method is declared in the root scope
	 * @param methodName - the method name
	 * @param params - the method signitur (params)
	 * @return true if the method is found else false
	 */
	@Override
	public boolean isDecleared(String methodName, ArrayList<Type> params) {
		if (methodsDeclared.containsKey(methodName)) {
			return methodsDeclared.get(methodName).checkParams(params);
		}
		return false;
	}
}
