package jpl.ch01.ex09;

public class Fibonacci {
	static final String WORD = "Fibonacci数列を出力します";
	static final int MAX_INDEX = 10;
	public static void main(String[] args){
		int lo = 1;
		int hi = 1;
		int[] fibary = new int[MAX_INDEX];
		System.out.println(WORD);
		fibary[0] = lo;
		for(int i = 1; i < MAX_INDEX; i++){
			fibary[i] = hi;
			hi = lo + hi;
			lo = hi - lo;
		}
		for(int i = 0; i < MAX_INDEX; i++){
			System.out.println(fibary[i]);
		}
	}
}
