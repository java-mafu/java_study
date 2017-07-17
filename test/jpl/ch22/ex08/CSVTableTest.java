package jpl.ch22.ex08;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

public class CSVTableTest {

	static String currentPath = new File(".").getAbsoluteFile().getParent();

	static final String[] ansStrs = { "a", "b", "c", "d", "e", "f", "aa", "bb", "cc", "dd", "ee", "ff", "g", "h", "i",
			"j", "k", "l", "gg", "hh", "ii", "jj", "kk", "ll" };

	@Test
	public void testReadCSVTableTrue() {
		try {
			File f = new File(currentPath + "/test/jpl/ch22/ex08/sample.csv");
			InputStreamReader in = new InputStreamReader(new FileInputStream(f));
			List<String[]> resultList = CSVTable.readCSVTable(in, 6);
			int i = 0;
			for (String[] strs : resultList) {
				for (String str : strs)
					assertThat(str, equalTo(ansStrs[i++]));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = IOException.class)
	public void testReadCSVTableOverInput() throws IOException {
		File f = new File(currentPath + "/test/jpl/ch22/ex08/sample.csv");
		InputStreamReader in;

		in = new InputStreamReader(new FileInputStream(f));

		CSVTable.readCSVTable(in, 5);
	}

}
