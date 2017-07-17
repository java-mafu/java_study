package jpl.ch20.ex02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;

public class TranslateByte extends FilterReader {


	// currentPath
	final static String currentPath = System.getProperty("user.dir") + "/src/"
			+ TranslateByte.class.getPackage().getName().replace(".", "/");
	// 入出力のファイル名
	final static String inputFileName = "input.txt";
	final static String outputFileName = "output.txt";

	//変換する文字
	static byte from = 'a';
	static byte to = 'A';

	// input.txtの中身を，変換してoutput.txtに書き込む．
	public static void main(String[] args) throws IOException {
		InputStream input = new FileInputStream(currentPath + "/" + inputFileName);
		OutputStream output = new FileOutputStream(currentPath + "/" + outputFileName);
		translateByte(input, output, from, to);
	}

	protected TranslateByte(Reader in) {
		super(in);
	}

	public int read() throws IOException {
		int c = super.read();
		return (c == from ? to : c);
	}

	public int read(char[] buf, int offset, int count) throws IOException {
		int nread = super.read(buf, offset, count);
		int last = offset + nread;
		for (int i = offset; i < last; i++)
			buf[i] = buf[i] == from ? (char)to : buf[i];
		return nread;
	}

	public static void translateByte(InputStream input, OutputStream output, byte from, byte to) throws IOException {
		int b;
		while ((b = input.read()) != -1)
			output.write(b);
	}

}
