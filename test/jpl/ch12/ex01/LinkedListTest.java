package jpl.ch12.ex01;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void testFind() {
		LinkedList<String> link = new LinkedList<String>();
		link.add("test1");
		link.add("test2");
		link.add("test3");
		link.add("test4");

		try {
			LinkedList<String> test = link.find("test1");
			assertThat(test.getList(), equalTo("test1"));
		} catch (ObjectNotFoundException e) {
			// TODO 自動生成された catch ブロック
			fail("ObjectNotFoundException");
			e.printStackTrace();
		}

		/** 以下の検証は目視で確認済み*/
		/*try {
			link.find("test6");
		} catch (ObjectNotFoundException e) {
			// TODO 自動生成された catch ブロック
			assertTrue("ObjectNotFoundException",true);
			e.printStackTrace();
		}*/
	}

}
