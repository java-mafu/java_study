package jpl.ch22.ex14;

public class DoubleNumberSumCalc {

	public static double calcSum(String input){
		String[] numbers = input.split(" ");
		double sum = 0;
		for(String number : numbers){
			sum += Double.parseDouble(number);
		}
		return sum;
	}
}
