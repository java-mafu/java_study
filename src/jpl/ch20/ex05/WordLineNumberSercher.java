package jpl.ch20.ex05;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.LinkedList;
import java.util.List;

public class WordLineNumberSercher {

	// 探す単語
	static String searchedWord = "hoge";

	// currentPath
	final static String currentPath = System.getProperty("user.dir") + "/src/"
			+ WordLineNumberSercher.class.getPackage().getName().replace(".", "/");

	// 入力のファイル名
	final static String inputFileName = "/input.txt";

	// 単語を探すファイル名．指定があれば上書きされる
	static String searchedFileName = currentPath + inputFileName;

	public static void main(String[] args) throws IOException {
		switch (args.length) {
		case 0:
			break;
		case 1:
			searchedWord = args[0];
			break;
		case 2:
			searchedWord = args[0];
			searchedFileName = args[1];
			break;
		default:
			throw new IllegalArgumentException("illegal argument");
		}
		List<String> outputData = searchWordLocation(searchedWord, searchedFileName);
		for (String s : outputData)
			System.out.println(s);
	}

	private static List<String> searchWordLocation(String word, String fileName)
			throws FileNotFoundException, IOException {
		LineNumberReader in = new LineNumberReader(new FileReader(fileName));

		String str = "";
		List<String> outputData = new LinkedList<String>();
		outputData.add("'" + word + "'");
		while ((str = in.readLine()) != null) {
			if (str.indexOf(word) != -1) {
				outputData.add("at line " + in.getLineNumber());
			}
		}
		return outputData;
	}
}
