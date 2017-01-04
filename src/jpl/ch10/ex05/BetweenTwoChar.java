package jpl.ch10.ex05;

public class BetweenTwoChar {
	public static String createString(char c1, char c2){
		String resultString = "" + c1;
		for(char c = (char)(c1 + 1); c <= c2; c++){
			resultString += c;
		}
		return resultString;
	}
}
