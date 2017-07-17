package jpl.ch01.ex04;

class Square {
	public static void main(String[] args){
		int num = 1;
		int sqr;
		System.out.println("自然数の平方を出力します");
		while(num < 20){
			sqr = num*num;
			System.out.println(sqr);
			num++;
		}
	}
}
