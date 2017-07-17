package jpl.ch13.ex05;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PunctuateNumberTest {

	@Test
	public void testPunctuateNumber() {
		String test = "1234567890987654321";
		String test2 = "123456789098765432l";
		assertThat(PunctuateNumber.punctuateNumber(test), equalTo("1,234,567,890,987,654,321"));
		assertThat(PunctuateNumber.punctuateNumber(test2), equalTo("Error"));
	}

	@Test
	public void testPunctuateNumber2() {
		String test = "123";
		assertThat(PunctuateNumber.punctuateNumber(test), equalTo("123"));
	}

	@Test
	public void testPunctuateNumber3() {
		String test = "12";
		assertThat(PunctuateNumber.punctuateNumber(test), equalTo("12"));
	}

	@Test
	public void testPunctuateNumber4() {
		String test = "";
		assertThat(PunctuateNumber.punctuateNumber(test), equalTo(""));
	}

}
