package jpl.ch01.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

public class HelloWorldTest {

	@Test
	public void test() {
	      assertEquals(10, HelloWorld.main(null));
	      assertEquals("10じゃない!!",10, sample.num());
	}

}
