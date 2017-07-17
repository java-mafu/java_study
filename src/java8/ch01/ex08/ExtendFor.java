package java8.ch01.ex08;

import java.util.ArrayList;
import java.util.List;

/**
 * 拡張for分を使用したときは，各ラムダ式で異なる値をキャプチャした 一方で，従来のfor文では，names配列に直接"i"を指定するとエラーになる
 * 代わりに，for文内で毎回finalの変数に値を一度代入してからnameにあてはめると問題なく動作する
 * このことから，拡張for文を使用したときの"name"は毎回finalとして定義されていると推測される
 */
public class ExtendFor {

	public static void main(String[] args) {
		String[] names = { "Peter", "Paul", "Mary" };
		List<Runnable> runners = new ArrayList<Runnable>();
		for (String name : names)
			runners.add(() -> System.out.println(name));

		for (Runnable r : runners)
			new Thread(r).start();

		runners = new ArrayList<Runnable>();
		for (int i = 0; i < names.length; i++) {
			final int j = i;
			runners.add(() -> System.out.println(names[j]));
		}

		for (Runnable r : runners)
			new Thread(r).start();

	}

}
