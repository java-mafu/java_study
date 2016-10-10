package jpl.ch01.ex12;

class ImprovedFibonacci {
	static final int MAX_INDEX = 9;
	static String[] fibarystr = new String[MAX_INDEX];
	public static void main(String[] args){
		int lo = 0;
		int hi = 1;

		for(int i = 0; i < MAX_INDEX; i++){
			if(hi % 2 == 0)
				fibarystr[i] = hi + " *";
			else
				fibarystr[i] = hi + "";
			hi = lo + hi;
			lo = hi - lo;
		}
	}
}