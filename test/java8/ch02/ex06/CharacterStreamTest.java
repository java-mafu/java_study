package java8.ch02.ex06;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class CharacterStreamTest {

	@Test
	public void test() {
		String str = "hogehogefugafuga";
		Stream<Character> a1 = CharacterStream.characterStream0(str);
		Stream<Character> a2 = CharacterStream.characterStream(str);
		assertThat(a1.collect(Collectors.toList()), is(a2.collect(Collectors.toList())));
	}

}
