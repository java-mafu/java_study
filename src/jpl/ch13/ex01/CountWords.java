package jpl.ch13.ex01;

public class CountWords {

	public static int countWordFromString(String str, char word){
		int index = 0;
		int count = 0;
		while((index = str.indexOf(word, index)) != -1)
			count++;
		return count;
	}


}
