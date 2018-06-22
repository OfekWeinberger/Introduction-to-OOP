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


    public Parser(Root root){
        this.root = root;
    }

    public void globalRun() throws IllegalSyntaxException{
        int i = 0;
        for(;i<root.getLines().size();i++){
            if(root.getLines().get(i).endsWith("{")) {
                CodeLine.check(root.getLines().get(i), root);//declare method
                int counter = 1;
                while (counter != 0) {
                    i++;
                    if (root.getLines().get(i).endsWith("{")) {
                        counter++;
                    }
                    if (root.getLines().get(i).endsWith("}")) {
                        counter--;
                    }
                }
                i++;
            }
            else {
                CodeLine.check(root.getLines().get(i), root);//declare variabale
            }
        }
    }

    public void runOverScope(Scope currentScope) throws IllegalSyntaxException{

    }

    public void deepRun() {
        for (Method method:root.getMethods()) {
            ArrayList<String> lines = method.getLines();
            for(int i=0 ;i <lines.size();i++){
                if(lines.get(i).endsWith("{")){
                    Scope deeperScope = new IfWhile();
                }
                else {
                    CodeLine line = new CodeLine()
                            .check(lines.get(i),method);
                }
            }
        }
    }
}
