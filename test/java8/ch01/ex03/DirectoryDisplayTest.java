package java8.ch01.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class DirectoryDisplayTest {

	@Test
	public void testShowDirectriesLambda() {
		String foldername = "test/java8/ch01/ex02";
		String[] folderList = DirectoryDisplay.showDirectries(foldername);
		List<String> list = new LinkedList();
		for (String file : folderList)
			list.add(file);
		assertThat(list.indexOf("hoge"), not(-1));
		assertThat(list.indexOf("fuga"), not(-1));
		assertThat(list.indexOf("DirectoryDisplayTest"), is(-1));
	}

}
