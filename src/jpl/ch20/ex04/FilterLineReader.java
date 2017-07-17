package jpl.ch20.ex04;

import java.io.FileInputStream;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class FilterLineReader extends FilterReader {
	// currentPath
	final static String currentPath = System.getProperty("user.dir") + "/src/"
			+ FilterLineReader.class.getPackage().getName().replace(".", "/");
	// 入出力のファイル名
	final static String inputFileName = "/input.txt";
	final static String outputFileName = "/output.txt";

	protected FilterLineReader(Reader in) {
		super(in);
	}

	public Byte[] readLine() throws IOException {
		int b;
		List<Byte> bytes = new ArrayList<Byte>();
		while ((b = in.read()) != -1 && b != '\n')
			bytes.add((byte) b);
		Byte[] result = null;
		if (bytes != null)
			result = (Byte[]) bytes.toArray(new Byte[0]);
		return result;
	}

	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream(currentPath + inputFileName);
		FilterLineReader fl = new FilterLineReader(new InputStreamReader(in));
		Byte[] bytes;
		while ((bytes = fl.readLine()).length != 0) {
			for (byte b : bytes)
				System.out.print((char) b);
		}
	}
}
