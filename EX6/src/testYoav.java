import oop.ex6.SJavaFileHandler;
import oop.ex6.main.Parser;
import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.scope.Root;

import java.io.IOException;
import java.util.ArrayList;

public class testYoav {
    public static void main(String[] args){
        try {
            SJavaFileHandler handler = new SJavaFileHandler("C:/Users/t8473619/Desktop/ex6T/502.txt");
            Root root = Root.instance(handler.splitCodeFileToLines());

            Parser parser = new Parser(root);
            parser.globalRun();
        }
        catch (IllegalSyntaxException e){
            System.out.println("CAUGHT SOMETHING!!!");
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void printScode(ArrayList<String> code){
        for (String line:code) {

        }
    }
}
