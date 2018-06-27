import oop.ex6.SJavaFileHandler;
import oop.ex6.main.Parser;
import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.Type;
import oop.ex6.syntaxobject.Variable;
import oop.ex6.syntaxobject.scope.Method;
import oop.ex6.syntaxobject.scope.Root;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class testYoav {
    public static void main(String[] args){
        try {
            SJavaFileHandler handler = new SJavaFileHandler("C:/Users/t8473619/Desktop/ex6T/502.txt");
            Root.resetRoot(handler.splitCodeFileToLines());
            Root root = Root.instance();
            printScode(root.getLines());
            Parser parser = new Parser(root);
            parser.runCheck();
//            //deep run
//            ArrayList<Variable> params = new ArrayList<>();
//            params.add(new Variable(Type.INT,"a",true,false));
//            params.add(new Variable(Type.INT,"b",true,false));
//            params.add(new Variable(Type.STRING,"s",true,false));
//            ArrayList<String> mLines = new ArrayList<>();
//            for(int i=4;i<11;i++){
//                mLines.add(root.getLines().get(i));
//            }
//            Method m = new Method("boo",params,mLines);
//            Root.addMethod(m);
//            parser.runOverScope(m,1);
        }
        catch (IllegalSyntaxException e){
            System.out.println("CAUGHT SOMETHING!!!");
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void printScode(ArrayList<String> code){
        for(int i=0;i<code.size();i++){
            System.out.println("line "+i+": "+code.get(i));
        }
    }
    public static void printMethods(Root root){
        for(Method m:root.getMethods()){
            System.out.println(m);
        }
    }
}
