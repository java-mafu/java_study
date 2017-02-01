package jpl.ch13.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch13.ex03.SubString;

public class SubStringTest {

	@Test
	public void testDelimitedString() {
		String str = "ababacbadhivrbiadhierhccccaaacahgfdeuisgcadddy";
		String result[] = SubString.delimitedString(str, 'a', 'c');
		
		assertThat(result[0], equalTo("ababac"));
		assertThat(result[1], equalTo("adhivrbiadhierhc"));
		assertThat(result[2], equalTo("aaac"));
		assertThat(result[3], equalTo("ahgfdeuisgc"));
		assertThat(result[4], equalTo("adddy"));
	}

}
