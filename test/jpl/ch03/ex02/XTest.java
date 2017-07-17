package jpl.ch03.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class XTest {

	@Test
	public void testX() {
		X x = new X();
		assertThat(x.fullMask, equalTo(x.xMask));
	}

	@Test
	public void testMask() {
		X x = new X();
		int orig = 0x11;
		assertThat(x.mask(orig), equalTo(orig & x.fullMask));
	}

}
