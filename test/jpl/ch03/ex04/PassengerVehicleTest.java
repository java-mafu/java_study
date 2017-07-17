package jpl.ch03.ex04;

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

	@Test
	public void testSetPassengernum() {
		String name = "test";
		int seatnum = 100;
		int passengernum = 800;
		int passengernum2 = 20;
		PassengerVehicle pv = new PassengerVehicle(name, seatnum, passengernum);
		pv.setPassengernum(passengernum2);
		assertThat(pv.getPassengernum(), equalTo(passengernum2));
	}
}
