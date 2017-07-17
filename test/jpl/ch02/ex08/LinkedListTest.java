package jpl.ch02.ex08;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void testLinkedList() {
		LinkedList l = new LinkedList();
		assertNull(l.list);
		assertNull(l.nextlink);
	}

	@Test
	public void testLinkedListObject() {
		LinkedList l = new LinkedList(new Object());
		assertNotNull(l.list);
		assertNull(l.nextlink);
	}

	@Test
	public void testLinkedListObjectLinkedList() {
		LinkedList l = new LinkedList(new Object(), new LinkedList());
		assertNotNull(l.list);
		assertNotNull(l.nextlink);
	}

}
