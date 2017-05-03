package jpl.ch22.ex01;

import java.util.Formatter;

public class DoubleArrayFormatter {

	static final int CHARNUM = 80;

	// 与えられた配列を，指定された縦の列に分割したFormatter配列を返すメソッド
	@SuppressWarnings("resource")
	public static Formatter formatDoubleArray(double[] array, int ver) {
		String format = "%." + (CHARNUM / ver - 3) + "f";
		String formattedString = "";
		if (array.length < ver) {
			for (int i = 0; i < array.length; i++) {
				formattedString += format + "\n";
			}
		}

		for(int arrayIdx = 0; arrayIdx < array.length; ++arrayIdx){
			formattedString += "|" + format;
			if((arrayIdx+1) % ver == 0)
				formattedString += "|\n";
		}
		Double objectDouble[] = new Double[array.length];
		for(int i = 0; i < array.length; i++)
			objectDouble[i] = array[i];
		return new Formatter().format(formattedString, objectDouble);
	}

	public static void main(String[] args) {
		double[] array = new double[100];
		for(int i = 0; i < 100; i++){
			array[i] = Math.random();
		}
		System.out.print(formatDoubleArray(array,10));
	}

}
