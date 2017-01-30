package jpl.ch11.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class AttrTest {

	@Test
	public void testGetName() {
		Attr<Integer> attr = new Attr<Integer>("test", 10);
		Attr<Double> attr2 = new Attr<Double>("test2", 3.21);
		
		assertThat(attr.getName(), equalTo("test"));
		assertThat(attr2.getName(), equalTo("test2"));
	}

	@Test
	public void testGetValue() {
		Attr<Integer> attr = new Attr<Integer>("test", 10);
		Attr<Double> attr2 = new Attr<Double>("test2", 3.21);
		
		assertThat(attr.getValue(), equalTo(10));
		assertThat(attr2.getValue(), equalTo(3.21));
	}

	@Test
	public void testSetValue() {
		Attr<Integer> attr = new Attr<Integer>("test");
		Attr<Double> attr2 = new Attr<Double>("test2");
		
		attr.setValue(25);
		attr2.setValue(180.123);
		
		assertThat(attr.getValue(), equalTo(25));
		assertThat(attr2.getValue(), equalTo(180.123));
	}

}
