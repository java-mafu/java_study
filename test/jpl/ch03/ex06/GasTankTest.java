package jpl.ch03.ex06;

import static org.junit.Assert.*;

import org.junit.Test;

public class GasTankTest {

	@Test
	public void testEmpty() {
		GasTank tank = new GasTank();
		tank.setGasamount(0);
		assertTrue(tank.empty());
		tank.setGasamount(1);
		assertFalse(tank.empty());
	}
}
