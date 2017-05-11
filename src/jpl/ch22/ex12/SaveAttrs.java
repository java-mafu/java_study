package jpl.ch22.ex12;

import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class SaveAttrs {

	public static Attributed readAttrs(Reader source) throws IOException {
		Scanner in = new Scanner(source);
		AttributedImpl attrs = new AttributedImpl();
		Attr attr = null;
		String exp = "^(.*)=(.*)";
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			String line = in.findInLine(pat);
			if (line != null) {
				MatchResult match = in.match();
				attr = new Attr(match.group(1), match.group(2));
				attrs.add(attr);
				in.nextLine(); // 改行を読み飛ばし
			} else {
				throw new IOException("input format error");
			}
		}

		IOException ex = in.ioException();
		if (ex != null)
			throw ex;

		return attrs;
	}
}
