package oop.ex6.main;

import oop.ex6.SJavaFileHandler;
import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.Type;
import oop.ex6.syntaxobject.scope.Scope;

import java.io.IOException;

public class Sjavac {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

//		try {
//			checkArgs(args);
//			SJavaFileHandler fh = new SJavaFileHandler(args[0]);
//			Scope cu = fh.buildCodeUnit();
//			System.out.println("0");
//		} catch (IllegalSyntaxException e) {
//			System.out.println("1");
//		} catch (IOException | IllegalArgumentException e) {
//			System.err.println("2");
//		}
	}

	// this method will make sure there are exactly two arguments otherwise throws relevant exception.
	private static void checkArgs(String[] args) throws IllegalArgumentException {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal program arguments, should be \"java_file\"");
	}
}
