package jpl.ch10.ex03;

import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch06.ex01.Week;

public class WeekIfElseTest {

	@Test
	public void testCheckWorkDay() {
		assertTrue(WeekIfElse.checkWorkDay(Week.MONDAY));
		assertTrue(WeekIfElse.checkWorkDay(Week.TUESDAY));
		assertTrue(WeekIfElse.checkWorkDay(Week.WEDNESDAY));
		assertTrue(WeekIfElse.checkWorkDay(Week.THURSDAY));
		assertTrue(WeekIfElse.checkWorkDay(Week.FRIDAY));
		assertTrue(WeekIfElse.checkWorkDay(Week.SATURDAY));
		assertFalse(WeekIfElse.checkWorkDay(Week.SUNDAY));
		assertFalse(WeekIfElse.checkWorkDay(null));
	}

}
