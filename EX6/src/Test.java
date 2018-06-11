import oop.ex6.syntaxobject.CodeUnit;
import oop.ex6.syntaxobject.Condition;
import oop.ex6.syntaxobject.IllegalSyntaxException;

public class Test {
	public static void main(String[] args){
//		String s = "?||";
//		String[] array = s.split("(&&)|(\\|\\|)");
//		for(String a : array)
//			System.out.print(a + " ");

		CodeUnit cu = new CodeUnit();
		try {
			Condition a = new Condition("true",cu);
			Condition b = new Condition("false", cu);
			Condition c = new Condition("24", cu);
			Condition d = new Condition("true&&25||false", cu);
			Condition e = new Condition("24", cu);
			Condition f = new Condition("true&&25||", cu);
		} catch (IllegalSyntaxException e) {
			e.printStackTrace();
		}
	}
}
