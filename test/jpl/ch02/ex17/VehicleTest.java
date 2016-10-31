package jpl.ch02.ex17;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testTurnInt() {
		Vehicle testVehicle = new Vehicle();
		testVehicle.turn(30);
		assertThat(testVehicle.getAngle(),equalTo(30));

	}

	@Test
	public void testTurnTURN() {
		Vehicle testVehicle = new Vehicle();
		testVehicle.turn(Vehicle.TURN_RIGHT);
		assertThat(testVehicle.getAngle(),equalTo(90));
		testVehicle.turn(Vehicle.TURN_LEFT);
		assertThat(testVehicle.getAngle(),equalTo(0));
	}

}
