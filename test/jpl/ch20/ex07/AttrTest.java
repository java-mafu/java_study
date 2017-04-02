package jpl.ch20.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;


public class AttrTest {
	// currentPath
	final static String currentPath = System.getProperty("user.dir") + "/test/"
			+ AttrTest.class.getPackage().getName().replace(".", "/");
	// 入出力のファイル名
	final static String inputFileName = "/input.txt";
	final static String outputFileName = "/output.txt";

	@Test
	public void DataInputStreamAttrTest() throws IOException {
		InputStream in = new FileInputStream(currentPath + inputFileName);
		DataInputStream din = new DataInputStream(in);
		Attr attr = new Attr(din);
		din.close();
		assertThat(attr.getName(), equalTo("test"));
		assertThat(attr.getValue(), equalTo("hoge"));
	}

	@Test
	public void DataOutputStreamAttrTest() throws IOException {
		InputStream in = new FileInputStream(currentPath + inputFileName);
		DataInputStream din = new DataInputStream(in);
		Attr attr = new Attr("test", "hoge");
		attr.writeData(currentPath + outputFileName);
		din.close();
		in = new FileInputStream(currentPath + outputFileName);
		din = new DataInputStream(in);
		assertThat(din.readUTF(), equalTo("test='hoge'"));
	}

}
