package jpl.ch02.ex05;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testVehicle() {
		assertThat(new Vehicle(), notNullValue());
	}

	@Test
	public void testVehicleIntIntString() {
		assertThat(new Vehicle(Integer.MAX_VALUE,Integer.MAX_VALUE,"test"), notNullValue());
		assertThat(new Vehicle(Integer.MIN_VALUE,Integer.MAX_VALUE,"test"), notNullValue());
		assertThat(new Vehicle(Integer.MAX_VALUE,Integer.MIN_VALUE,"test"), notNullValue());
		assertThat(new Vehicle(Integer.MIN_VALUE,Integer.MIN_VALUE,"test"), notNullValue());
	}


}
