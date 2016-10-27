package jpl.ch02.ex16;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch02.ex10.Vehicle;

public class LinkedListTest {

	@Test
	public void testTotalLinkNum() {
		Vehicle sedan = new Vehicle("tanama");
		LinkedList c1 = new LinkedList(sedan);
		Vehicle sports_car = new Vehicle("nakata");
		LinkedList c2 = new LinkedList(sports_car, c1);
		Vehicle truck = new Vehicle("tamana");
		LinkedList c3 = new LinkedList(truck, c2);

		assertThat(c1.totalLinkNum(),equalTo(1));
		assertThat(c2.totalLinkNum(),equalTo(2));
		assertThat(c3.totalLinkNum(),equalTo(3));
	}

}
