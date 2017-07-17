package jpl.ch20.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;


public class AttrTest {
	// currentPath
	final static String currentPath = System.getProperty("user.dir") + "/test/"
			+ AttrTest.class.getPackage().getName().replace(".", "/");
	// 蜈･蜃ｺ蜉帙�ｮ繝輔ぃ繧､繝ｫ蜷�
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
		Attr attr = new Attr("test", "hoge");
		OutputStream fout = new FileOutputStream(currentPath + outputFileName);
		attr.writeData(fout);
		fout.close();
		InputStream in = new FileInputStream(currentPath + outputFileName);
		DataInputStream din = new DataInputStream(in);
		assertThat(din.readUTF(), equalTo("test='hoge'"));
	}

}
