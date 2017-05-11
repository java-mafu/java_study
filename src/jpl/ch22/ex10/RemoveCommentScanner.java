package jpl.ch22.ex10;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoveCommentScanner {
	public static List<String> scanAttrs(Reader source) {
		List<String> result = new ArrayList<String>();
		Scanner in = new Scanner(source);
		in.useDelimiter("(#.*)|\r|\n");
		while (in.hasNext()) {
			String line = in.next();
			if (!line.equals(""))
				result.add(line);
		}
		return result;
	}
}
