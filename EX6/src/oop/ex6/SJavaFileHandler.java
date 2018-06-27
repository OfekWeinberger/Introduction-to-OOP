package oop.ex6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
			sb.append(line + "\n");
		fileReader.close();
		return sb.toString();
	}

	/**
	 * split the line text acurding to the rules (for now by /n chars)
	 *
	 * @return an array list of lines
	 */
	public ArrayList<String> splitCodeFileToLines() {
		String[] lines = content.split(RegularExpressions.SPLITTER_REGEX);
		ArrayList<String> linesArray = new ArrayList<String>();
		for (int i = 0; i < lines.length; i++) {
			//clean empty lines
			if (!RegularExpressions.SPACES_PATTERN.matcher(lines[i]).matches() && (!lines[i].isEmpty())) {
				linesArray.add(lines[i]);
			}
		}
		linesArray = cleanCode(linesArray);
		return linesArray;
	}

	// clean code from empty lines and not nececery spaces. format the code lins so it will be easy to work with
	private ArrayList<String> cleanCode(ArrayList<String> lines) {
		ArrayList<String> cleanLines = new ArrayList<String>();
		for (String line : lines) {
			String trimedLine;
			if (line.contains("//")) {
				trimedLine = line;
			} else {
				trimedLine = line.replaceAll(RegularExpressions.START_TRIMER_REGEX, "");
			}
			String beforeCleanSpaced = trimedLine.replaceAll(RegularExpressions.BEFORE_TRIMER_REGEX, "");
			String cleanSpaced = beforeCleanSpaced.replaceAll(RegularExpressions.AFTER_TRIMER_REGEX, "");
			String noDouble = cleanSpaced.replaceAll(RegularExpressions.SPACES_REGEX, " ");
			cleanLines.add(noDouble);
		}
		return cleanLines;
	}


}
