package jpl.ch20.ex10;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;

public class WordCounter {

	public static HashMap countWordsInFile(File file) throws IOException{
		StreamTokenizer st = new StreamTokenizer(new FileReader(file));
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		while(st.nextToken() != StreamTokenizer.TT_EOF){
				map.put(st.sval, map.get(st.sval)==null ? 1 : map.get(st.sval)+1);
		}
		return map;
	}

	public static void main(String[] args) throws IOException{
		String filePath = System.getProperty("user.dir") + "/src/jpl/ch20/ex10/input.txt";
		HashMap map = countWordsInFile(new File(filePath));
		System.out.println(map);
	}
}
