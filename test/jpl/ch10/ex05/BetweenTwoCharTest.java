package jpl.ch10.ex05;

import static org.junit.Assert.*;

import org.junit.Test;

public class BetweenTwoCharTest {

	@Test
	public void testCreateString() {
		char c1 = 'a';
		char c2 = 'n';
		assertTrue(BetweenTwoChar.createString(c1, c2).equals("abcdefghijklmn"));
	}

}
