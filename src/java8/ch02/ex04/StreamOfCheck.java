package java8.ch02.ex04;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Stream.ofだとintの配列のStreamが生成される．
//intのストリームを取得するなら，Arraysを使用してIntStreamを作成してボクシング変換するのが良い
public class StreamOfCheck {

	public static void main(String[] args) {
		int[] values = { 1, 4, 9, 16 };
		Stream<int[]> stream = Stream.of(values);
		Stream<Integer> istream = Arrays.stream(values).boxed();
		for (int i : stream.collect(Collectors.toList()).get(0))
			System.out.print(i + " ");
		System.out.println();
		System.out.println(istream.collect(Collectors.toList()));
	}

}
