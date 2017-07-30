package java8.ch04.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class DefaultPropertiesTest {

	@Test
	public void test() {
		// DefaultProperty使用
		DefaultProperties dp = new DefaultProperties();
		assertThat(dp.getText(), is("default"));
		dp.textProperty();
		dp.setText("hoge");
		assertThat(dp.getText(), is("hoge"));
	}

}
