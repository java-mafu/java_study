package jpl.ch03.ex08;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PassengerVehicleTest {

	@Test
	public void testClone() {
		PassengerVehicle pv1 = new PassengerVehicle(1);
		PassengerVehicle pv2 = pv1.clone();
		assertThat(pv1.getPassengernum(), equalTo(pv2.getPassengernum()));
		assertThat(pv1.getSeatnum(), equalTo(pv2.getSeatnum()));
	}

}
