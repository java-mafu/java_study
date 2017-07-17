package jpl.ch01.ex12;

import org.junit.Test;

public class ImprovedFibonacciTest {

	@Test
	public void test() {
		ImprovedFibonacci.main(null);
		for(int i = 0; i < ImprovedFibonacci.MAX_INDEX; i++)
		System.out.println(i + ImprovedFibonacci.fibarystr[i]);
	}

}
