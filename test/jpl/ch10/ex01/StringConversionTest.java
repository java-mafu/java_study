package jpl.ch10.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringConversionTest {

	@Test
	public void testChangeSpecialString() {
		String[] str = {"\n",
				"\t",
				"\b",
				"\r",
				"\f",
				"\\",
				"\'",
				"\""};
		String[] ans = {"\\\n",
				"\\\t",
				"\\\b",
				"\\\r",
				"\\\f",
				"\\\\",
				"\\\'",
				"\\\""};
		for(int i = 0; i < str.length;i++)
		assertTrue(StringConversion.changeSpecialString(str[i]).equals(ans[i]));
	}

}
