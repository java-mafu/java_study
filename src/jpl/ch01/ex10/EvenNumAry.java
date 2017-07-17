package jpl.ch01.ex10;

public class EvenNumAry {
	public int number;//整数値を格納
	public boolean even;//整数値が偶数ならtrue,奇数ならfalseを格納

	public String EvenString(){
		if(even)
			return "*";
		else
			return "";
	}
}
