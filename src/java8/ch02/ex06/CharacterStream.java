package java8.ch02.ex06;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CharacterStream {

	// テキストの記述
	public static Stream<Character> characterStream0(String s) {
		List<Character> result = new ArrayList<>();
		for (char c : s.toCharArray())
			result.add(c);
		return result.stream();
	}

	// 追加作成
	public static Stream<Character> characterStream(String s) {
		Stream.Builder<Character> builder = Stream.builder();
		s.chars().forEach(c -> builder.add((char) c));
		return builder.build();
	}

}
