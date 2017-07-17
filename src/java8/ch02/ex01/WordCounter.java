package java8.ch02.ex01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class WordCounter extends Thread {

	String word;
	static int count;

	public WordCounter(String word) {
		this.word = word;
	}

	public static void setCount(int recount) {
		count = recount;
	}

	public static int getCount() {
		return count;
	}

	@Override
	public void run() {
		if (word.length() > 12) {
			counter();
		}
	}

	synchronized public int counter() {
		return count++;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		String contents = new String(Files.readAllBytes(Paths.get("src/java8/ch02/ex01/alice.txt")),
				StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		count = 0;
		for (String w : words) {
			new WordCounter(w).start();

		}
		System.out.println(count);
	}
}
