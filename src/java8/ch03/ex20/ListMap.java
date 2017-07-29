package java8.ch03.ex20;

import java.util.List;
import java.util.function.Function;

public class ListMap {
	@SuppressWarnings("unchecked")
	public static <T, U> List<U> map(List<T> l, Function<T, U> f) {
		List<U> result;
		try {
			result = l.getClass().newInstance();
			for (T element : l) {
				result.add(f.apply(element));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
