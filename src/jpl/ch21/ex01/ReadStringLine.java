package jpl.ch21.ex01;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ReadStringLine {
	// currentPath
	final static String currentPath = System.getProperty("user.dir") + "/src/"
			+ FilterLineReader.class.getPackage().getName().replace(".", "/");
	// 入出力のファイル名
	final static String inputFileName = "/input.txt";
	final static String outputFileName = "/output.txt";

	public static List<String> preserveSortList(List<String> input) {
		List<String> resultList = new ArrayList<String>(input);
		for (int i = 0; i < resultList.size() - 1; i++) {
			for (int j = i + 1; j < resultList.size(); j++) {
				if (resultList.get(i).compareTo(resultList.get(j)) > 0) {
					String str = resultList.get(j);
					resultList.set(j, resultList.get(i));
					resultList.set(i, str);
				}
			}
		}
		return resultList;
	}

	public static void main(String[] args) throws IOException {
		InputStream in;
		if(args.length == 0)
		in = new FileInputStream(currentPath + inputFileName);
		else if(args.length == 1)
			in = new FileInputStream(args[0]);
		else{
			System.out.println("引数が多すぎます");
			return;
		}
		FilterLineReader fl = new FilterLineReader(new InputStreamReader(in));
		Byte[] bytes;
		LinkedList<String> list = new LinkedList<String>();
		while ((bytes = fl.readLine()).length != 0) {
			char[] characters = new char[bytes.length];
			for (int i = 0; i < bytes.length; i++)
				characters[i] = (char) bytes[i].byteValue();
			list.add(String.valueOf(characters));
		}
		List<String> result = preserveSortList(list);
		System.out.println(result);
	}

}
