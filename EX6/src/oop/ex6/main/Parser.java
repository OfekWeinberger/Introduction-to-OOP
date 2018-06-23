package oop.ex6.main;

import oop.ex6.syntaxobject.CodeLine;
import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.MethodDeclaration;
import oop.ex6.syntaxobject.scope.IfWhile;
import oop.ex6.syntaxobject.scope.Method;
import oop.ex6.syntaxobject.scope.Root;
import oop.ex6.syntaxobject.scope.Scope;

import java.util.ArrayList;
import java.util.Stack;

public class Parser {
    private Root root;
    private static final CodeLine varHendler;
    private static final String SCOPE_OPEN_EXCEPTION ="Scope open line can only start with if||while in inner scope of void in the global scope";
    private static final String SCOPE_NOT_CLOSING_EXCEPTION = "Scope is opening but missing a } to close it";
    private static final String METHOD_NO_RETURN_EXCEPTION = "Method must end with a return line";
    static {
        varHendler = new CodeLine();
    }


    public Parser(Root root){
        this.root = root;
    }

    public void runCheck() throws IllegalSyntaxException{
        globalRun();
        deepRun();
    }

    public void globalRun() throws IllegalSyntaxException{
        int i = 0;
        ArrayList<String> lines = root.getLines();
        for(;i<lines.size();i++){
            if(lines.get(i).endsWith("{")) {
                if(lines.get(i).startsWith("void")) {
                    MethodDeclaration declerator = new MethodDeclaration();
                    declerator.check(lines.get(i),root);
                    Method method = new Method(declerator.getName(),declerator.getParams(), getScopeLines(i,root));// TODO: 6/22/2018 get method name and params
                    if(method.getLines().get(method.getLines().size()-1).equals("return;")) {
                        method.getLines().remove(method.getLines().size()-1);
                        i++;
                    }
                    else {
                        throw new IllegalSyntaxException(METHOD_NO_RETURN_EXCEPTION);
                    }
                    Root.addMethod(method);
                    i = skipBeyondScope(i, method);
                }
            }
            else {
                varHendler.check(root.getLines().get(i), root);//declare variabale
                System.out.println("global line: "+i);
            }
        }
    }

    public void runOverScope(Scope currentScope,int depth) throws IllegalSyntaxException{
        ArrayList<String> lines = currentScope.getLines();
        for(int i=0 ;i <lines.size();i++){
            if(lines.get(i).endsWith("{")){
                if (lines.get(i).startsWith("if") || lines.get(i).startsWith("while")) {
                    IfWhile deeperScope = new IfWhile(currentScope,getScopeLines(i,currentScope));
                    runOverScope(deeperScope,depth+1);
                    i = skipBeyondScope(i,deeperScope);
                }
                else {
                    throw new IllegalSyntaxException(SCOPE_OPEN_EXCEPTION);
                }
            }
            else {
                System.out.println(lines.get(i));
                System.out.println("check line: "+i);
                varHendler.check(lines.get(i),currentScope);
                System.out.println("code line inside scope depth: "+depth);
            }
        }
    }

    public void deepRun() throws IllegalSyntaxException {
        for (Method method:root.getMethods()) {
            runOverScope(method,1);
        }
    }

    private ArrayList<String> getScopeLines(int scopeStartIndex,Scope parentScope) throws IllegalSyntaxException{
        ArrayList<String> scopeLines = new ArrayList<String>();
        int runerIndex  = scopeStartIndex;
        int counter = 1;
        while (counter != 0) {
            runerIndex++;
            if(runerIndex == parentScope.getLines().size()){
                throw new IllegalSyntaxException(SCOPE_OPEN_EXCEPTION);
            }
            if (parentScope.getLines().get(runerIndex).endsWith("{")) {
                counter++;
            }
            if (parentScope.getLines().get(runerIndex).equals("}")) {
                counter--;
            }
            if(counter!=0){
                scopeLines.add(parentScope.getLines().get(runerIndex));
            }
        }
        return scopeLines;
    }

    private int skipBeyondScope(int index,Scope scope){
        return index+scope.getLines().size()+1;//skip beyond the last line the 2 is for the { and } lines
    }
}
