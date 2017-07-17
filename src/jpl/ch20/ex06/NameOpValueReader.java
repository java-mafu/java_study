package jpl.ch20.ex06;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;

public class NameOpValueReader {


	// currentPath
	static String currentPath;
	// 入出力のファイル名
	final static String inputFileName = "/input.txt";
	final static String outputFileName = "/output.txt";


	static final String name[] = { "name", "id", "title" };
	static double value[] = { 0, 0, 0 };

	public static void resetValue() {
		value[0] = 0;
		value[1] = 0;
		value[2] = 0;
	}

	public static String getValues() {
		String result[] = { name[0] + ":" + value[0], name[1] + ":" + value[1], name[2] + ":" + value[2] };

		return result[0] + "\n" + result[1] + "\n" + result[2] + "\n";
	}

	public static void readValues(Reader source) throws IOException {
		StreamTokenizer in = new StreamTokenizer(source);
		String output[] = new String[3];
		// 名前ごとのidを判別
		int idx = -1;

		char op = 0;
		while (in.nextToken() != StreamTokenizer.TT_EOL) {
			if (in.ttype == StreamTokenizer.TT_WORD) {
				if (in.sval.equals(name[0]))
					idx = 0;
				else if (in.sval.equals(name[1]))
					idx = 1;
				else if (in.sval.equals(name[2]))
					idx = 2;
				else
					throw new IOException("no such name");
			} else if (in.ttype == '+' || in.ttype == '-' || in.ttype == '=') {
				op = (char) in.ttype;
			} else if(in.ttype == StreamTokenizer.TT_NUMBER){
				if (op == 0)
					throw new IOException("illegal operator");
				switch (op) {
				case '+':
					value[idx] += in.nval;
					break;
				case '-':
					value[idx] -= in.nval;
					break;
				case '=':
					value[idx] = in.nval;
					break;
				default:
					throw new IOException();
				}
			} else {
				break;
			}
		}

	}

	public static void main(String args[]) throws Exception{
		currentPath = System.getProperty("user.dir") + "/src/"
				+ NameOpValueReader.class.getPackage().getName().replace(".", "/");
			InputStreamReader in = new FileReader(currentPath + inputFileName);
			NameOpValueReader.readValues(in);
			System.out.println(NameOpValueReader.getValues());

	}

}
