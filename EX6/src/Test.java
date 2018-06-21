import oop.ex6.syntaxobject.Condition;
import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.Type;

public class Test {
	public static void main(String[] args){
//		String s = "?||";
//		String[] array = s.split("(&&)|(\\|\\|)");
//		for(String a : array)
//			System.out.print(a + " ");

		Type a = Type.BOOLEAN;
		Type b = Type.INT;

		System.out.println(a == b);
	}
}
