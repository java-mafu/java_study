package jpl.ch03.ex09;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch03.ex08.Vehicle;

public class GarageTest {

	@Test
	public void testClone() {
		Vehicle car1 = new Vehicle("person1");
		Vehicle car2 = new Vehicle("person2");

		Vehicle[] cars = {car1,car2};
		Garage g = new Garage(cars);
		Garage newG = g.clone();
		/*String型のフィールドの値は一致するが，Vehicleリストの参照先は異なる*/
		assertThat(g.getCarlist()[0].getName(),equalTo(newG.getCarlist()[0].getName()));
		assertThat(g.getCarlist()[1].getName(),equalTo(newG.getCarlist()[1].getName()));
		assertNotSame(g.getCarlist(),newG.getCarlist());

		/*Vehicleリストの要素の一つを変更しても，コピー元は変わらないことを確認する*/
		Vehicle car3 = new Vehicle("person3");
		Vehicle[] carss = {car1,car3};
		newG.setCarlist(carss);
		assertThat(g.getCarlist()[1].getName(),equalTo(car2.getName()));
		assertThat(newG.getCarlist()[1].getName(),equalTo(car3.getName()));
	}

}
