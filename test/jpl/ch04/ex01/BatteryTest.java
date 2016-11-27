package jpl.ch04.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

public class BatteryTest {

	@Test
	public void testEmpty() {
		Battery tank = new Battery();
		tank.setbatteryamount(0);
		assertTrue(tank.empty());
		tank.setbatteryamount(1);
		assertFalse(tank.empty());
	}

}
