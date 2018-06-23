import oop.ex6.SJavaFileHandler;
import oop.ex6.syntaxobject.*;
import oop.ex6.syntaxobject.scope.Root;
import oop.ex6.syntaxobject.scope.Scope;

import java.io.IOException;
import java.util.ArrayList;

public class TestOfek {
	public static void main(String[] args){
		CodeLine cl = new CodeLine();
		MethodDeclaration md = new MethodDeclaration();
		try {
			ArrayList<String> arr = new ArrayList<String>();
			arr.add("int a;");
			arr.add("a = 5;");
			Root r = Root.instance(arr);
			cl.check("int a;", r);
			System.out.println("first passed");
			cl.check("a = 5;", r);
//			arr.add("void methodName(int a, double b, final String c){");
//			Root r = Root.instance(arr);
//			md.check("void methodName(int a, double b, final String c){", r);
//			//System.out.println(r.getMethods());
		}
		catch (IllegalSyntaxException e){
			System.out.println("CAUGHT SOMETHING!!!");
			System.out.println(e.getMessage());
		}
	}
}
