import oop.ex6.SJavaFileHandler;
import oop.ex6.syntaxobject.CodeLine;
import oop.ex6.syntaxobject.Condition;
import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.Type;
import oop.ex6.syntaxobject.scope.Root;
import oop.ex6.syntaxobject.scope.Scope;

import java.io.IOException;
import java.util.ArrayList;

public class TestOfek {
	public static void main(String[] args){
		CodeLine cl = new CodeLine();
		try {
			ArrayList<String> arr = new ArrayList<String>();
			arr.add("int a = 5;");
			Root r = Root.instance(arr);
			cl.check("int a = 5;", r);

		}
		catch (IllegalSyntaxException e){
			System.out.println("CAUGHT SOMETHING!!!");
			System.out.println(e.getMessage());
		}
	}
}