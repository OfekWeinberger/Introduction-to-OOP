package oop.ex6.main;

import oop.ex6.SJavaFileHandler;
import oop.ex6.syntaxobject.IllegalSyntaxException;
import oop.ex6.syntaxobject.scope.Root;

import java.io.IOException;

public class Sjavac {

	/**
	 * The main method of the program.
	 * @param args single arg is required, the path (relative or absolute) of the sJava file.
	 */
	public static void main(String[] args) {

		try {
			checkArgs(args);
			SJavaFileHandler handler = new SJavaFileHandler(args[0]);
			Root.resetRoot(handler.splitCodeFileToLines());
			Parser parser = new Parser(Root.instance());
			parser.runCheck();
			System.out.println("0");
		} catch (IllegalSyntaxException e) {
			System.out.println("1");
		} catch (IOException | IllegalArgumentException e) {
			System.err.println("2");
		}
	}

	// this method will make sure there are exactly two arguments otherwise throws relevant exception.
	private static void checkArgs(String[] args) throws IllegalArgumentException {
		if (args.length != 1)
			throw new IllegalArgumentException("Illegal program arguments, should be \"java_file\"");
	}
}
