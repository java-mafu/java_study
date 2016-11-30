package jpl.ch06.ex04;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class TrafficLightTest {

	@Test
	public void testGetColor() {
		assertTrue(TrafficLight.getColor(TrafficLight.BLUE).equals(Color.blue));
		assertTrue(TrafficLight.getColor(TrafficLight.YELLOW).equals(Color.yellow));
		assertTrue(TrafficLight.getColor(TrafficLight.RED).equals(Color.red));
	}

}
