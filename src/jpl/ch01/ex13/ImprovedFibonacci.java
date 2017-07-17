package jpl.ch01.ex13;

class ImprovedFibonacci {
	static final int MAX_INDEX = 9;
	public static void main(String[] args){
		int lo = 1;
		int hi = 1;
		String mark;
		System.out.printf("1: %s %n", lo);
		for(int i = 1; i < MAX_INDEX; i++){
			if(hi % 2 == 0)
				mark = " *";
			else
				mark = "";
			System.out.printf("%d: %d %s %n", i, hi, mark);
			hi = lo + hi;
			lo = hi - lo;
		}
	}
}