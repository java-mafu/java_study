package jpl.ch10.ex05;

//charをひとつづつ加算して，stringに代入していく
public class BetweenTwoChar {
	public static String createString(char c1, char c2){
		String resultString = "" + c1;
		for(char c = (char)(c1 + 1); c <= c2; c++){
			resultString += c;
		}
		return resultString;
	}
}

