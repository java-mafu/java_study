package java8.ch01.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class DirectoryDisplayTest {

	@Test
	public void testShowDirectriesLambda() {
		String foldername = "test/java8/ch01/ex02";
		File[] folderList = DirectoryDisplay.showDirectriesLambda(foldername);
		List<String> list = new LinkedList();
		for (File file : folderList)
			list.add(file.getName());
		assertThat(list.indexOf("hoge"),not(-1));
		assertThat(list.indexOf("fuga"),not(-1));
		assertThat(list.indexOf("DirectoryDisplayTest"),is(-1));
	}

	@Test
	public void testShowDirectriesMethod() {
		String foldername = "test/java8/ch01/ex02";
		File[] folderList = DirectoryDisplay.showDirectriesMethod(foldername);
		List<String> list = new LinkedList();
		for (File file : folderList)
			list.add(file.getName());
		assertThat(list.indexOf("hoge"),not(-1));
		assertThat(list.indexOf("fuga"),not(-1));
		assertThat(list.indexOf("DirectoryDisplayTest"),is(-1));
	}

}
