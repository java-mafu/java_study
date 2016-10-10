package jpl.ch01.ex15;

import static org.junit.Assert.*;

import org.junit.Test;

public class LookupEditTest {

	@Test
	public void test() {
		LookupEdit lookup = new LookupEditMethod();
		lookup.add("test", 10);
		lookup.add("test2", 20);

		assertTrue((int)lookup.find("test") == 10);
		assertTrue(lookup.find("test5") == null);

		assertTrue(lookup.remove("test"));
		assertTrue(lookup.find("test")==null);
		assertTrue(lookup.remove("test2"));

		assertFalse(lookup.remove("test2"));

	}
}
