package oop.ex6;

import oop.ex6.syntaxobject.IllegalSyntaxException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SJavaFileHandler {

	private String content;

	/**
	 * The constructor of the JavaFileHandler, reads file, then disassemble it to blocks.
	 *
	 * @param path The relative or absolute path of the sjava file.
	 * @throws IOException Throws IOException in any case of problem with reading the command file.
	 */
	public SJavaFileHandler(String path) throws IOException {
		this.content = readFile(path);
	}

	public CodeUnit buildCodeUnit() throws IllegalSyntaxException{
		return new CodeUnit(content);
	}


	// Reads the given file and returns a String with it's contents.
	private String readFile(String path) throws IOException {
		StringBuilder sb = new StringBuilder();
		File file = new File(path);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufferedReader.readLine()) != null)
			sb.append(line);
		fileReader.close();
		return sb.toString();
	}




}
