package oop.ex6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

//	public CodeUnit buildCodeUnit() throws IllegalSyntaxException{
//		return new CodeUnit(content);
//	}


	public String getContent() {
		return content;
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

	public ArrayList<String> splitCodeFileToLines() {
		String[] lines = content.split(RegularExpressions.SPLITER_REGEX);
		ArrayList<String> linesArray = new ArrayList<String>();
		for (int i = 0; i < lines.length; i++) {
			//clean empty lines
			if (!RegularExpressions.SPACES_PATTERN.matcher(lines[i]).matches()) {
				linesArray.add(lines[i]);
			}
		}
		return linesArray;
	}

	private ArrayList<String> cleanCode(ArrayList<String> lines){
		ArrayList<String> cleanLines = new ArrayList<String>();
		for (String line:lines) {
			String trimedLine = line.replaceAll(RegularExpressions.START_TRIMER_REGEX,"");
		}
	}


}
