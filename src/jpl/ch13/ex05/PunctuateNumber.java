package jpl.ch13.ex05;

public class PunctuateNumber {

	public static String punctuateNumber(String str){
		final int DIGIT = 3;
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) < '0' || str.charAt(i) > '9')
				return "Error";
		}
		StringBuffer buf = new StringBuffer(str);
		int insertPos = str.length() % DIGIT;
		for(int i = insertPos; i < buf.length(); i+= DIGIT+1){
			if(i == 0)
				continue;
			buf.insert(i, ",");
		}
		return buf.toString();
	}
}
