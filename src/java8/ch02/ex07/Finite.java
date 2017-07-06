package java8.ch02.ex07;

import java.util.stream.Stream;

/**
 * 終端処理は一度しか使用できないため，streamが終わったかを確認する＝終端処理ができなくなる ということ...？
 */
public class Finite {

	public static <T> boolean isFinite(Stream<T> stream) {
		return stream.count() != 0;
	}

	public static void main(String[] args) {
		Stream<String> str = Stream.of("a", "b", "c");
		if (isFinite(str))
			str.forEach(System.out::println);
	}

}
