package jpl.ch16.ex04;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnnotationsDisplayTest {

	@Test
	public void testGetAnnotationsString() {
		Class<?> c = AnnotationClass.class;
		String[] str = AnnotationsDisplay.getAnnotationsString(c);
		for(String s : str)
			assertTrue(s.equals("@jpl.ch16.ex04.MyAnnotation()"));

	}

}
