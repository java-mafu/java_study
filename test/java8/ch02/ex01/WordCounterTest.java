package java8.ch02.ex01;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class WordCounterTest {

	@Test
	public void testWordCounter() throws IOException, InterruptedException {
		String contents = new String(Files.readAllBytes(Paths.get("test/java8/ch02/ex01/alice.txt")),
				StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		int count = 0;
		WordCounter.setCount(count);
		for (String w : words)
			new WordCounter(w).start();
		Thread.sleep(1000);
		assertTrue("" + WordCounter.getCount(), WordCounter.getCount() == 8);
	}

}
