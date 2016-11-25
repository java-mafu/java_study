package jpl.ch03.ex07;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;


/*今回課題として追加したメソッドのみテストを実施*/
public class ColorAttrTest {

	@Test
	public void testHashCode() {
		ColorAttr testAttr1 = new ColorAttr("sample");
		ColorAttr testAttr2 = new ColorAttr("sample");
		assertThat(testAttr1.hashCode(), is(testAttr2.hashCode()));

		ColorAttr testAttr3 = new ColorAttr("foo");
		assertThat(testAttr1.hashCode(), not(is(testAttr3.hashCode())));
	}

	@Test
	public void testEqualsColorAttr() {
		ColorAttr testAttr = new ColorAttr("sample");
		ColorAttr testAttr2 = new ColorAttr("sample2");
		assertThat(testAttr.equals(testAttr), equalTo(true));
		assertThat(testAttr.equals(testAttr2), equalTo(false));
	}

}
