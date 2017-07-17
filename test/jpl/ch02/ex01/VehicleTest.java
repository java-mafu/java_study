package jpl.ch02.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void test() {
		Vehicle test = new Vehicle();
		assertThat(test.angle, is(any(Integer.class)));
		assertThat(test.name = "", isA(String.class));
		assertThat(test.speed, is(any(Integer.class)));
	}

}
