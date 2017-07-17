package jpl.ch03.ex08;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testClone() {
		Vehicle v1 = new Vehicle("person");
		Vehicle v2 = v1.clone();
		assertThat(v1.getAngle(), equalTo(v2.getAngle()));
		assertThat(v1.getID(), equalTo(v2.getID()));
		assertThat(v1.getName(), equalTo(v2.getName()));
		assertThat(v1.getNextID(), equalTo(v2.getNextID()));
		assertThat(v1.getSpeed(), equalTo(v2.getSpeed()));

		v1.setName("brother");

		assertThat(v1.getName(), not(v2.getName()));
	}

}
