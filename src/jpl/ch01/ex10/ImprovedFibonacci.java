package jpl.ch01.ex10;

public class ImprovedFibonacci {
	static final int MAX_INDEX = 9;
	public static void main(String[] args){
		int lo = 1;
		int hi = 1;
		EvenNumAry[] fibary = new EvenNumAry[MAX_INDEX];
		for(int i = 0; i < MAX_INDEX; i++)
			fibary[i] = new EvenNumAry();

		fibary[0].number = lo;

		for(int i = 1;i < MAX_INDEX; i++){
			fibary[i].number = hi;
			if(hi % 2 == 0)
				fibary[i].even = true;
			else
				fibary[i].even = false;
			hi = lo + hi;
			lo = hi - lo;
		}
		for(int i = 0;i < MAX_INDEX; i++){
			System.out.println(fibary[i].number+" "+fibary[i].EvenString());
		}
	}
}
