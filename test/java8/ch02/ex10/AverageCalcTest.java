package java8.ch02.ex10;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

public class AverageCalcTest {

	@Test
	public void test() {
		Double[] data = { 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0 };
		Stream<Double> stream = Stream.of(data);
		double ans = AverageCalc.average(stream);
		double expect = 0;
		for (double d : data)
			expect += d;
		expect /= data.length;
		assertThat(ans, is(expect));
	}

}
