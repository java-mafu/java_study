package jpl.ch22.ex15;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testAnalysisStringsMathMethods() throws Exception {
		Method[] methods = Math.class.getMethods();

		for (Method m : methods) {
			Class<?>[] c = m.getParameterTypes();
			if (c.length==0)
				continue;
			if (c.length == 2 && c[0] != c[1])
				continue;
			Object[] objs = new Object[c.length];
			String[] str = new String[c.length + 1];
			for (int i = 0; i < objs.length; i++) {
				objs[i] = new Random().nextInt(1000);
				str[i] = objs[i].toString();
			}
			str[objs.length] = m.getName();
			String expectedStr = m.invoke(null, objs).toString();
			String resultStr = (Calculator.analysisStrings(str)).toString();
			if (m.getReturnType() == float.class || m.getReturnType() == double.class) {
				if (expectedStr.length() > 8) {
					expectedStr = expectedStr.substring(0, expectedStr.length() - 4);
					resultStr = resultStr.substring(0, expectedStr.length());
				}
			} else {
				expectedStr = "" + Double.parseDouble(expectedStr);
				resultStr = "" + Double.parseDouble(resultStr);
			}
			assertThat(m.toString(),resultStr, equalTo(expectedStr));
		}
	}

}
