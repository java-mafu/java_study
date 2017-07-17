package jpl.ch13.ex03;

import java.util.ArrayList;;

public class SubString {
	public static String[] delimitedString(String from, char start, char end){
		ArrayList<String> result = new ArrayList<String>();
		int startPos = from.indexOf(start);
		int endPos;
		while(startPos != -1){
			endPos = from.indexOf(end, startPos+1);
			if(endPos == -1){
				result.add(from.substring(startPos));
				break;
			}
			else{
				result.add(from.substring(startPos, endPos+1));
				startPos = from.indexOf(start, endPos+1);
			}
		}
		String[] array = new String[result.size()];
		result.toArray(array);
		return array;

	}
}
