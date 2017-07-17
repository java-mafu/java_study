package jpl.ch09.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TrueBitCounterTest {

	@Test
	public void testCountBit() {
		int testdata = (int) (Math.random()*Integer.MAX_VALUE);
		assertThat(TrueBitCounter.countBit(testdata),equalTo(Integer.bitCount(testdata)));
	}

}
