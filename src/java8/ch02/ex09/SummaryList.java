package java8.ch02.ex09;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class SummaryList {

	public static <T> ArrayList<T> summaryStream(Stream<ArrayList<T>> stream) {
		ArrayList<T> result = stream.reduce(new ArrayList<T>(), (x, y) -> {
			x.addAll(y);
			return x;
		});
		return result;
	}

	public static <T> ArrayList<T> summaryStream2(Stream<ArrayList<T>> stream) {
		Optional<ArrayList<T>> resultOpt = stream.reduce((x, y) -> {
			x.addAll(y);
			return x;
		});
		ArrayList<T> result = new ArrayList<T>();
		resultOpt.ifPresent(result::addAll);
		return result;
	}

	public static <T> ArrayList<T> summaryStream3(Stream<ArrayList<T>> stream) {
		ArrayList<T> result = stream.reduce(new ArrayList<T>(), (x, y) -> {
			x.addAll(y);
			return x;
		});
		return result;
	}
}
