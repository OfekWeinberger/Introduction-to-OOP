package oop.ex6.main;

import oop.ex6.syntaxobject.CodeLine;
import oop.ex6.syntaxobject.Condition;
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
    private static final String CONDITION_ERROR_EXCEPTION = "Ileagal if or while condition";
    private static final String MISSING_SEMICOLON_EXCEPTION = "all lines witch dont open scope shoulde end with ;";
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

    //run over the global variables and method declarations
    private void globalRun() throws IllegalSyntaxException{
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
                else {
                    throw new IllegalSyntaxException(SCOPE_OPEN_EXCEPTION);
                }
            }
            else {
                sendToCheck(lines.get(i),root);
            }
        }
    }

    // run over a scope lines in a recursive way
    private void runOverScope(Scope currentScope,int depth) throws IllegalSyntaxException{
        ArrayList<String> lines = currentScope.getLines();
        for(int i=0 ;i <lines.size();i++){
            if(lines.get(i).endsWith("{")){
                if (lines.get(i).startsWith("if") || lines.get(i).startsWith("while")) {
                    Condition condition = new Condition();
                    String[] condtionStr = lines.get(i).split("[\\(\\)]");
                    if(condtionStr.length!=3){
                        throw new IllegalSyntaxException(CONDITION_ERROR_EXCEPTION);
                    }
                    else {
                        condition.check(condtionStr[1], currentScope);
                    }
                    IfWhile deeperScope = new IfWhile(currentScope,getScopeLines(i,currentScope));
                    runOverScope(deeperScope,depth+1);
                    i = skipBeyondScope(i,deeperScope);
                }
                else {
                    throw new IllegalSyntaxException(SCOPE_OPEN_EXCEPTION);
                }
            }
            else {
                sendToCheck(lines.get(i),currentScope);
            }
        }
    }

    // send line to be checked by CodeLine
    private void sendToCheck(String line,Scope scope)throws IllegalSyntaxException{
        if(!line.endsWith(";")&&!line.startsWith("//")){
            throw new IllegalSyntaxException(MISSING_SEMICOLON_EXCEPTION);
        }
        else {
            varHendler.check(line.substring(0,line.length()-1), scope);
        }
    }

    // goes over all methods scope and run over them one by one
    private void deepRun() throws IllegalSyntaxException {
        for (Method method:root.getMethods()) {
            runOverScope(method,1);
        }
    }

    // return the scope section starting in the start index
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

    // skip to beyond the lines of a specific scope
    private int skipBeyondScope(int index,Scope scope){
        return index+scope.getLines().size()+1;//skip beyond the last line the 2 is for the { and } lines
    }

}
