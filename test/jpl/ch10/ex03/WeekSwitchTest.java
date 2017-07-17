package jpl.ch10.ex03;

import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch06.ex01.Week;

public class WeekSwitchTest {

	@Test
	public void testCheckWorkDay() {
		assertTrue(WeekSwitch.checkWorkDay(Week.MONDAY));
		assertTrue(WeekSwitch.checkWorkDay(Week.TUESDAY));
		assertTrue(WeekSwitch.checkWorkDay(Week.WEDNESDAY));
		assertTrue(WeekSwitch.checkWorkDay(Week.THURSDAY));
		assertTrue(WeekSwitch.checkWorkDay(Week.FRIDAY));
		assertTrue(WeekSwitch.checkWorkDay(Week.SATURDAY));
		assertFalse(WeekSwitch.checkWorkDay(Week.SUNDAY));
	}

}
