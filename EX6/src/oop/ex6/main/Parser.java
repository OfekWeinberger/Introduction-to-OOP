package oop.ex6.main;

import oop.ex6.syntaxobject.CodeLine;
import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.scope.IfWhile;
import oop.ex6.syntaxobject.scope.Method;
import oop.ex6.syntaxobject.scope.Root;
import oop.ex6.syntaxobject.scope.Scope;

import java.util.ArrayList;
import java.util.Stack;

public class Parser {
    private Root root;
    private static final CodeLine varHendler;
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
        for(;i<root.getLines().size();i++){
            if(root.getLines().get(i).endsWith("{")) {
                Method method = new Method("generic",null,getScopeLines(i)); // TODO: 6/22/2018 get method name and params
                i = skipBeyondScope(i,method);
            }
            else {
//                varHendler.check(root.getLines().get(i), root);//declare variabale
                System.out.println("global line: "+i);
            }
        }
    }

    public void runOverScope(Scope currentScope,int depth) throws IllegalSyntaxException{
        ArrayList<String> lines = currentScope.getLines();
        for(int i=0 ;i <lines.size();i++){
            if(lines.get(i).endsWith("{")){
                Scope deeperScope = new IfWhile(currentScope,getScopeLines(i));
                runOverScope(deeperScope,depth+1);
                i = skipBeyondScope(i,deeperScope);
            }
            else {
//                varHendler.check(lines.get(i),currentScope);
                System.out.println("code line inside scope depth: "+depth);
            }
        }
    }

    public void deepRun() throws IllegalSyntaxException {
        for (Method method:root.getMethods()) {
            runOverScope(method,1);
        }
    }

    private ArrayList<String> getScopeLines(int scopeStartIndex){
        ArrayList<String> scopeLines = new ArrayList<String>();
        int runerIndex  = scopeStartIndex;
        int counter = 1;
        while (counter != 0) {
            runerIndex++;
            if (root.getLines().get(runerIndex).endsWith("{")) {
                counter++;
            }
            if (root.getLines().get(runerIndex).endsWith("}")) {
                counter--;
            }
            if(counter!=0){
                scopeLines.add(root.getLines().get(runerIndex));
            }
        }
        return scopeLines;
    }

    private int skipBeyondScope(int index,Scope scope){
        return index+scope.getLines().size()+2;//skip beyond the last line the 2 is for the { and } lines
    }
}
