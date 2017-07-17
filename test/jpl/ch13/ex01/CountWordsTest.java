package jpl.ch13.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CountWordsTest {

	@Test
	public void testCountWordFromString() {
		String str = "abcabcbcacbdbviebvaabueibvwia";
		char test = 'a';

		assertThat(CountWords.countWordFromString(str, test), equalTo(6));
	}

}
