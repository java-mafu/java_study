package java8.ch02.ex12;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.junit.Test;

public class CountWordTest {

	@Test
	public void test() {
		Stream<String> words = Stream.of("a", "ab", "abc", "bca");
		AtomicInteger[] shortWords = CountWord.counter(words);
		assertThat(shortWords[0].get(), is(0));
		assertThat(shortWords[1].get(), is(1));
		assertThat(shortWords[2].get(), is(1));
		assertThat(shortWords[3].get(), is(2));
		for (int i = 4; i < shortWords.length; i++)
			assertThat(shortWords[i].get(), is(0));
	}

}
