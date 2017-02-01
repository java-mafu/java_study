package jpl.ch13.ex04;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MyMapTest {

	@Test
	public void testReadTypeValueString() {
		String test = "Integer 10\n"
				+ "Double 5.79\n"
				+ "Boolean true\n"
				+ "Float 3.2\n"
				+ "Long 99999999999\n"
				+ "Character z\n"
				+ "Byte 84\n"
				+ "Short 4";
		ArrayList<Object> list = MyMap.readTypeValueString(test);
		assertTrue(list.get(0) instanceof Integer);
		assertThat((Integer)list.get(0), equalTo(10));
		assertTrue(list.get(1) instanceof Double);
		assertThat((Double)list.get(1), equalTo(5.79));
		assertTrue(list.get(2) instanceof Boolean);
		assertThat((Boolean)list.get(2), equalTo(true));
		assertTrue(list.get(3) instanceof Float);
		assertThat((Float)list.get(3), equalTo(3.2f));
		assertTrue(list.get(4) instanceof Long);
		assertThat((Long)list.get(4), equalTo(99999999999L));
		assertTrue(list.get(5) instanceof Character);
		assertThat((Character)list.get(5), equalTo('z'));
		assertTrue(list.get(6) instanceof Byte);
		assertThat((Byte)list.get(6), equalTo((byte)84));
		assertTrue(list.get(7) instanceof Short);
		assertThat((Short)list.get(7), equalTo((short)4));
	}

}
