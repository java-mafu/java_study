package jpl.ch04.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch03.ex08.Vehicle;

public class LinkedListMethodTest {

	@Test
	public void testLinkedListMethod() {
		LinkedList llm = new LinkedListMethod();
		assertNull(llm.getList());
		assertNull(llm.getNextLink());
	}

	@Test
	public void testLinkedListMethodObject() {
		LinkedList llm = new LinkedListMethod(new Object());
		assertNotNull(llm.getList());
		assertNull(llm.getNextLink());
	}

	@Test
	public void testLinkedListMethodObjectLinkedList() {
		LinkedList llm = new LinkedListMethod(new Object(), new LinkedListMethod());
		assertNotNull(llm.getList());
		assertNotNull(llm.getNextLink());
	}

	@Test
	public void testTotalLinkNum() {
		LinkedList llm1 = new LinkedListMethod(new Object());
		LinkedList llm2 = new LinkedListMethod(new Object(),llm1);
		LinkedList llm3 = new LinkedListMethod(new Object(),llm2);
		assertThat(llm1.totalLinkNum(),equalTo(1));
		assertThat(llm2.totalLinkNum(),equalTo(2));
		assertThat(llm3.totalLinkNum(),equalTo(3));
	}

	@Test
	public void testClone() {
		LinkedList l1 = new LinkedListMethod();
		LinkedList l2 = l1.clone();
		Vehicle test = new Vehicle();
		assertThat(l1.getList(), equalTo(l2.getList()));

		l1.setList(test);

		assertThat(l1.getList(), not(l2.getList()));
	}

}
