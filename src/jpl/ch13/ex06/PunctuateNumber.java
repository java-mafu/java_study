package jpl.ch13.ex06;

public class PunctuateNumber {

	public static String punctuateNumber(String str, char punc, int digit){
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) < '0' || str.charAt(i) > '9')
				return "Error";
		}
		StringBuffer buf = new StringBuffer(str);
		int insertPos = str.length() % digit;
		for(int i = insertPos; i < buf.length(); i+= digit+1){
			if(i == 0)
				continue;
			buf.insert(i, punc);
		}
		return buf.toString();
	}
}
