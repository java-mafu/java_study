package jpl.ch06.ex05;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import jpl.ch06.ex04.TrafficLight;

public class TrafficLightTest {

	@Test
	public void testGetColor() {
		TrafficLight light = TrafficLight.BLUE;
		assertTrue(light.getColor().equals(Color.blue));
		 light = TrafficLight.YELLOW;
		assertTrue(light.getColor().equals(Color.yellow));
		 light = TrafficLight.RED;
		assertTrue(light.getColor().equals(Color.red));
	}

}
