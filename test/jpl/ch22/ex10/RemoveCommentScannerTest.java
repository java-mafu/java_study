package jpl.ch22.ex10;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

public class RemoveCommentScannerTest {
	static String currentPath = new File(".").getAbsoluteFile().getParent();
	static final String[] ansStrs = { "test", "hoge", "fuga" };

	@Test
	public void testScanAttrs() throws FileNotFoundException {
		File f = new File(currentPath + "/test/jpl/ch22/ex10/input.txt");
		InputStreamReader in = new InputStreamReader(new FileInputStream(f));
		List<String> resultList = RemoveCommentScanner.scanAttrs(in);
		int i = 0;
		for (String str : resultList)
			assertThat(str, equalTo(ansStrs[i++]));
	}

}
