package java8.ch02.ex08;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Zip {

	public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
		Stream.Builder<T> result = Stream.builder();
		List<T> flist = first.collect(Collectors.toList());
		List<T> slist = second.collect(Collectors.toList());
		long length = flist.size() < slist.size() ? flist.size() : slist.size();
		for (int i = 0; i < length; i++) {
			result.add(flist.get(i));
			result.add(slist.get(i));
		}
		return result.build();
	}

	public static void main(String[] args) {
		Stream<String> f = Stream.of("a", "b", "c");
		Stream<String> s = Stream.of("d", "e", "f", "g");

		System.out.println(zip(f, s).collect(Collectors.toList()));
	}

}
