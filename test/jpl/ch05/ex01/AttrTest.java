package jpl.ch05.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class AttrTest {

	@Test
	public void testAttrString() {
		String name = "test";
		Attr a = new Attr(name);
		assertThat(a.getName(), equalTo(name));
	}

	@Test
	public void testAttrStringObject() {
		String name = "test";
		Object obj = new Object();
		Attr a = new Attr(name, obj);
		assertThat(a.getName(), equalTo(name));
		assertThat(a.getValue(), equalTo(obj));
	}

}
