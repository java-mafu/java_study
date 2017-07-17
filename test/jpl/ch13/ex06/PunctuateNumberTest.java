package jpl.ch13.ex06;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PunctuateNumberTest {

	@Test
	public void testPunctuateNumber() {
		String test = "1234567890987654321";
		String test2 = "123456789098765432l";
		char punc = '!';
		int digit = 4;
		assertThat(PunctuateNumber.punctuateNumber(test, punc, digit), equalTo("123!4567!8909!8765!4321"));
		assertThat(PunctuateNumber.punctuateNumber(test2, punc, digit), equalTo("Error"));
	}
}
