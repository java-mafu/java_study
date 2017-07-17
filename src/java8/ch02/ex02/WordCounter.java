package java8.ch02.ex02;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WordCounter {

	public static void main(String[] args) throws IOException, InterruptedException {
		String contents = new String(Files.readAllBytes(Paths.get("src/java8/ch02/ex02/alice.txt")),
				StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		int count = 0;
		List<String> longWords = words.stream().filter(w -> {
			if (w.length() > 12) {
				System.out.println("filter実行");
				return true;
			} else
				return false;
		}).limit(5).collect(Collectors.toList());
		System.out.println(longWords);
	}
}
