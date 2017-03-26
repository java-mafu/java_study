package jpl.ch20.ex01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte {

	// currentPath
	final static String currentPath = System.getProperty("user.dir") + "/src/"
			+ TranslateByte.class.getPackage().getName().replace(".", "/");
	// 入出力のファイル名
	final static String inputFileName = "input.txt";
	final static String outputFileName = "output.txt";

	//input.txtの中身を，変換してoutput.txtに書き込む．
	public static void main(String[] args) throws IOException {
		byte from = (byte) args[0].charAt(0);
		byte to = (byte) args[1].charAt(0);
		System.out.println(currentPath);
		InputStream input = new FileInputStream(currentPath + "/" + inputFileName);
		OutputStream output = new FileOutputStream(currentPath + "/" + outputFileName);
		translateByte(input, output, from, to);
	}

	public static void translateByte(InputStream input, OutputStream output, byte from, byte to) throws IOException {
		int b;
		while ((b = input.read()) != -1)
			output.write(b == from ? to : b);
	}

}
