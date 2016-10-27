package jpl.ch02.ex13;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testGetSpeed() throws Exception{
		Vehicle v = new Vehicle();
        int speed = 10;
        Field nameField = v.getClass().getDeclaredField("speed"); //(1)
        nameField.setAccessible(true);	//(2)
        nameField.set(v, speed);	//(3)

        assertEquals(speed, v.getSpeed());
	}

	@Test
	public void testSetSpeed() throws Exception{
		Vehicle v = new Vehicle();
        int speed = 10;
        Field nameField = v.getClass().getDeclaredField("speed");
        nameField.setAccessible(true);
        v.setSpeed(speed);

        assertEquals(speed, nameField.get(v));
	}

	@Test
	public void testGetAngle() throws Exception{
		Vehicle v = new Vehicle();
        int angle = 10;
        Field nameField = v.getClass().getDeclaredField("angle");
        nameField.setAccessible(true);
        nameField.set(v, angle);

        assertEquals(angle,  v.getAngle());
	}

	@Test
	public void testSetAngle() throws Exception{
		Vehicle v = new Vehicle();
        int angle = 10;
        Field nameField = v.getClass().getDeclaredField("angle");
        nameField.setAccessible(true);
        v.setAngle(angle);

        assertEquals(angle, nameField.get(v));
	}

	@Test
	public void testGetName() throws Exception{
		Vehicle v = new Vehicle();
        String name = "test";
        Field nameField = v.getClass().getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(v, name);

        assertEquals(name,  v.getName());
	}

	@Test
	public void testSetName() throws Exception{
		Vehicle v = new Vehicle();
        String name = "test";
        Field nameField = v.getClass().getDeclaredField("name");
        nameField.setAccessible(true);
        v.setName(name);

        assertEquals(name, nameField.get(v));
	}


	@Test
	public void testGetNextID() throws Exception{
		Vehicle v = new Vehicle();
        int nextID = 10;
        Field nameField = v.getClass().getDeclaredField("nextID");
        nameField.setAccessible(true);
        nameField.set(v, nextID);

        assertEquals(nextID,  v.getNextID());
	}

	@Test
	public void testGetID() throws Exception{
		Vehicle v = new Vehicle();
        int ID = 10;
        Field nameField = v.getClass().getDeclaredField("ID");
        nameField.setAccessible(true);
        nameField.set(v, ID);

        assertEquals(ID,  v.getID());
	}

}
