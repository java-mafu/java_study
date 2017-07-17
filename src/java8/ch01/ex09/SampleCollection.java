package java8.ch01.ex09;

import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Collectionの要素を利用して処理を行いたい場合に， 毎回for文と条件をつけて処理するよりも，一つの関数の引数にラムダ式を利用して
 * 処理を行う方が，コードの可読性が向上する．
 */
public class SampleCollection<T> extends LinkedList<T> implements Collection2<T> {

	public static void main(String[] args) {
		SampleCollection<String> sc = new SampleCollection<String>();
		sc.add("hoge");
		sc.add("fuga");
		sc.add("test");

		// forEachIfの使用
		sc.forEachIf(System.out::println, string -> string.indexOf("f") == -1);

		// forEachIfを使用しない場合
		for (String str : sc) {
			if (str.indexOf("f") == -1)
				System.out.println(str);
		}
	}

	@Override
	public void forEachIf(Consumer<T> action, Predicate<T> filter) {
		for (T t : this) {
			if (filter.test(t))
				action.accept(t);
		}
	}

}
