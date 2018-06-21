package oop.ex6.syntaxobject.scope;

import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class Root extends Scope {
    private static final String METHOD_OVERLOAD_EXCEPTIN = "Method overloading is not allowed";
    private HashMap<String,Method> methodsDecleared;
    private static Root singeltone = null;
    private Root(){
        super();
        methodsDecleared = null;
    }

    public static Root Instant(){
        if(singeltone == null){
            singeltone = new Root();
        }
        return singeltone;
    }

    public static void addMethod(Method method) throws IllegalSyntaxException{
        if(singeltone.methodsDecleared.containsKey(method.getName())){
            throw new IllegalSyntaxException(METHOD_OVERLOAD_EXCEPTIN);
        }
        singeltone.methodsDecleared.put(method.getName(),method);
    }

    @Override
    public boolean isDecleared(String methodName, ArrayList<Type> params){
        if(methodsDecleared.containsKey(methodName)){
            return methodsDecleared.get(methodName).checkParams(params);
        }
        return false;
    }
}
