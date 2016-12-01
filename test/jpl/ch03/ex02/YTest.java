package jpl.ch03.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class YTest {

	@Test
	public void testY() {
		Y y = new Y();
		assertThat(y.fullMask, equalTo(y.fullMask |= y.yMask));
	}

}
