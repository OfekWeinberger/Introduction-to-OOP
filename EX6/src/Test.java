import oop.ex6.SJavaFileHandler;
import oop.ex6.syntaxobject.Condition;
import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.Type;

import java.io.IOException;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args){
		try {
			SJavaFileHandler handler = new SJavaFileHandler("C:/Users/t8473619/Desktop/ex6T/502.txt");
			System.out.println(handler.getContent());
			ArrayList<String> lines = handler.splitCodeFileToLines();
			for (int i=0;i<lines.size();i++){
				System.out.println("line "+i);
				System.out.println(lines.get(i));
			}
		}
		catch (IOException e){
			System.out.println("IO EXCEPTION");
			System.out.println(e.getMessage());
		}
	}
}
