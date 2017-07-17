package java8.ch02.ex10;

import java.util.stream.Stream;

//調べたら出てきましたが，よくわからない．
public class AverageCalc {
	public static double average(Stream<Double> stream) {
		Double result = stream.reduce(new double[3], (r, e) -> {
			r[0] += e.doubleValue();
			r[1] += 1.0;
			r[2] = r[0] / r[1];
			return r;
		}, (r, s) -> {
			r[0] += s[0];
			r[1] += s[1];
			r[2] = r[0] / r[1];
			return r;
		})[2];
		return result;
	}
}
