package jpl.ch02.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testVehicle() {
		Vehicle.nextID = 0;
		Vehicle v = new Vehicle();
		assertThat(v.ID, equalTo(1));
	}

	@Test
	public void testVehicleString() {
		Vehicle.nextID = 0;
		Vehicle v = new Vehicle("test");
		assertThat(v.ID, equalTo(1));
		assertThat(v.name, equalTo("test"));
	}

}
