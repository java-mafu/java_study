package jpl.ch03.ex10;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch03.ex08.Vehicle;

public class LinkedListTest {

	@Test
	public void testClone() {
		LinkedList l1 = new LinkedList();
		LinkedList l2 = l1.clone();
		Vehicle test = new Vehicle();
		assertThat(l1.getList(), equalTo(l2.getList()));

		l1.setList(test);

		assertThat(l1.getList(), not(l2.getList()));
	}

}
