package jpl.ch20.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;


//入力と出力を1セットでテストする
public class DecryEncryptStreamTest {

	// currentPath
	final static String currentPath = System.getProperty("user.dir") + "/test/"
			+ DecryEncryptStreamTest.class.getPackage().getName().replace(".", "/");
	// 入出力のファイル名
	final static String inputFileName = "input.txt";
	final static String outputFileName = "output.txt";

	@Test
	public void testReadWrite() throws IOException {
		InputStream in = new FileInputStream(currentPath + "/" + inputFileName);
		OutputStream out = new FileOutputStream(currentPath + "/" + outputFileName);
		EncryptOutputStream eos = new EncryptOutputStream(out);
		int b, b2;
		while ((b = in.read()) != -1) {
			eos.write(b);
		}
		in.close();
		out.close();
		eos.close();
		in = new FileInputStream(currentPath + "/" + inputFileName);
		InputStream in2 = new FileInputStream(currentPath + "/" + outputFileName);
		InputStream result = new DecryptInputStream(in2);
		while ((b = in.read()) != -1 &&(b2 = result.read()) != -1) {
			assertThat(b, equalTo(b2));
		}

		in.close();
		in2.close();
		result.close();
	}
}
