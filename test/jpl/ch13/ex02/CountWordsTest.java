package jpl.ch13.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CountWordsTest {

	@Test
	public void testCountStringFromString() {
		String str = "testtesttesttetsttestetststststtest";
		String test = "test";

		assertThat(CountWords.countStringFromString(str, test), equalTo(5));
	}

}
