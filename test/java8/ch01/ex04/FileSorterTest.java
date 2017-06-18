package java8.ch01.ex04;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.File;

import org.junit.Test;

public class FileSorterTest {

	String[] answerFileList = { "a", "b", "c", "a.txt", "b.txt", "c.txt", "FileSorterTest.java" };
	String foldername = "test/java8/ch01/ex04";

	@Test
	public void testSortFiles() {
		File[] fileList = new File(foldername).listFiles();
		File[] sortedFiles = FileSorter.sortFiles(fileList);
		for (int i = 0; i < answerFileList.length; i++) {
			assertThat(sortedFiles[i].getName(), is(answerFileList[i]));
		}
	}

}
