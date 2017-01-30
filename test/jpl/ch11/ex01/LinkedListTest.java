package jpl.ch11.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void testGetNextLink() {
		LinkedList<String> link = new LinkedList<String>("test1");
		LinkedList<String> link2 = new LinkedList<String>("test2", link);
		
		assertTrue(link2.getNextLink().equals(link));
	}

	@Test
	public void testTotalLinkNum() {
		LinkedList<String> link = new LinkedList<String>("test1");
		LinkedList<String> link2 = new LinkedList<String>("test2", link);
		LinkedList<String> link3 = new LinkedList<String>("test3", link2);
		LinkedList<String> link4 = new LinkedList<String>("test4", link3);
		
		assertThat(link4.totalLinkNum(),equalTo(4));
	}

}
