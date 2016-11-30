package jpl.ch03.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PassengerVehicleTest {

	@Test
	public void testGetSeatnum() {
		String name = "test";
		int seatnum = 100;
		int passengernum = 800;
		PassengerVehicle pv = new PassengerVehicle(name, seatnum, passengernum);
		assertThat(pv.getSeatnum(), equalTo(seatnum));
	}

	@Test
	public void testGetPassengernum() {
		String name = "test";
		int seatnum = 100;
		int passengernum = 800;
		PassengerVehicle pv = new PassengerVehicle(name, seatnum, passengernum);
		assertThat(pv.getPassengernum(), equalTo(passengernum));
	}

}
