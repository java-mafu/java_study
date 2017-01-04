package jpl.ch10.ex01;

public class StringConversion {

	public static String changeSpecialString(String str){
		String resultString = "";
		for(int i = 0; i < str.length(); i++){
			char c = str.charAt(i);
			if(c =='\n')
				resultString += "\\\n";
			else if(c == '\t')
				resultString += "\\\t";
			else if(c == '\b')
				resultString += "\\\b";
			else if(c == '\r')
				resultString += "\\\r";
			else if(c == '\f')
				resultString += "\\\f";
			else if(c == '\\')
				resultString += "\\\\";
			else if(c == '\'')
				resultString += "\\\'";
			else if(c == '\"')
				resultString += "\\\"";
			else
				resultString += c;
		}
		return resultString;
	}
}
