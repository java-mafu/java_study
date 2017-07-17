package jpl.ch06.ex02;

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
		testVehicle.turn(Direction.RIGHT);
		assertThat(testVehicle.getAngle(),equalTo(90));
		testVehicle.turn(Direction.LEFT);
		assertThat(testVehicle.getAngle(),equalTo(0));
	}

}
