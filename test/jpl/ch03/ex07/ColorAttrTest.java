package jpl.ch03.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;


/*今回課題として追加したメソッドのみテストを実施*/
public class ColorAttrTest {

	@Test
	public void testHashCode() {
		ColorAttr testAttr1 = new ColorAttr("sample");
		ColorAttr testAttr2 = new ColorAttr("sample");
		assertThat(testAttr1.hashCode(), is(testAttr2.hashCode()));

		ColorAttr testAttr3 = new ColorAttr("sample2");
		assertThat(testAttr1.hashCode(), not(testAttr3.hashCode()));
	}

	@Test
	public void testEqualsColorAttr() {
		ColorAttr testAttr1 = new ColorAttr("sample");
		ColorAttr testAttr2 = new ColorAttr("sample2");
		assertThat(testAttr1, is(testAttr1));
		assertThat(testAttr1, not(testAttr2));
	}

}
