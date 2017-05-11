package jpl.ch22.ex14;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class DoubleNumberSumCalcTest {

	@Test
	public void testCalcSum() {
		String str = "1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9";
		assertThat(DoubleNumberSumCalc.calcSum(str), is(49.5));
	}

}
