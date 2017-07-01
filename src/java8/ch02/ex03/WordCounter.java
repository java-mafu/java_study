package java8.ch02.ex03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WordCounter {

	public static void main(String[] args) throws IOException, InterruptedException {
		String contents = new String(Files.readAllBytes(Paths.get("src/java8/ch02/ex03/alice.txt")),
				StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		int count = 0;
		long start = System.nanoTime();
		List<String> longWords = words.stream().filter(w -> w.length() > 8).collect(Collectors.toList());
		long end = System.nanoTime();
		long streamSec = end - start;
		System.out.println(longWords);
		start = System.nanoTime();
		longWords = words.parallelStream().filter(w -> w.length() > 8).collect(Collectors.toList());
		end = System.nanoTime();
		long paraStreamSec = end - start;
		System.out.println(longWords);
		System.out.println(streamSec);
		System.out.println(paraStreamSec);
		System.out.println("処理速度" + ((float) (streamSec) / paraStreamSec) + "倍");
	}
}
