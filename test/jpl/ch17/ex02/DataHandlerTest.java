package jpl.ch17.ex02;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class DataHandlerTest {

	@Test
	public void testReadFile() {
		DataHandler dh = new DataHandler();
		String currentPath = new File(".").getAbsoluteFile().getParent();
		System.out.println(currentPath);

		byte[] b;
		for (int i = 0; i < 10000; i++) {
			b = dh.readFile(new File(currentPath + "/test/jpl/ch17/ex02/test.txt"));
			// 戻り値がnullではないことを確認
			assertNotNull(b);
			long fMemory = Runtime.getRuntime().freeMemory();
			//時々gcしてみる
			if (i%10==0) {
				while(fMemory > Runtime.getRuntime().freeMemory()){
				System.runFinalization();
				System.gc();
				}
			}
		}
	}

}
