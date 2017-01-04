package jpl.ch10.ex02;

public class StringConversion {

	public static String changeSpecialString(String str){
		String resultString = "";
		for(int i = 0; i < str.length(); i++){
			char c = str.charAt(i);
			switch(c){
			case '\n':
				resultString += "\\\n";
				break;
			case '\t':
				resultString += "\\\t";
				break;
			case '\b':
				resultString += "\\\b";
				break;
			case '\r':
				resultString += "\\\r";
				break;
			case '\f':
				resultString += "\\\f";
				break;
			case '\\':
				resultString += "\\\\";
				break;
			case '\'':
				resultString += "\\\'";
				break;
			case '\"':
				resultString += "\\\"";
				break;
			default:
				resultString += c;
				break;
		}
		}
		return resultString;
	}
}
