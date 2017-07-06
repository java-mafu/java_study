package java8.ch02.ex08;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class ZipTest {

	@Test
	public void test() {
		String[] ans = { "a", "d", "b", "e", "c", "f", "g" };
		Stream<String> f = Stream.of("a", "b", "c");
		Stream<String> s = Stream.of("d", "e", "f", "g");
		int i = 0;
		for (String str : Zip.zip(f, s).collect(Collectors.toList()))
			assertThat(str, is(ans[i++]));
		assertThat(i, equalTo(6));
	}

}
