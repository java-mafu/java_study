package jpl.ch22.ex11;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class CSVTable {

	public static List<String[]> readCSVTable(Reader source, int cellNum) throws IOException {
		StreamTokenizer in = new StreamTokenizer(source);
		in.resetSyntax();
		// in.whitespaceChars(0, ' ' - 1);
		in.wordChars(' ', 127);
		in.wordChars(128 + 32, 256);
		in.whitespaceChars(',', ',');

		List<String[]> vals = new ArrayList<String[]>();
		int nextTokenVal = in.nextToken();
		while (nextTokenVal != StreamTokenizer.TT_EOF) {
			String[] linStrs = new String[cellNum];
			for (int i = 0; i < cellNum; i++) {
				if (in.ttype == StreamTokenizer.TT_WORD) {
					linStrs[i] = in.sval;
					nextTokenVal = in.nextToken();
				} else
					throw new IOException("input format error");
			}
			vals.add(linStrs);
			System.out.println();
			if (in.sval != null)
				throw new IOException("input format error");
			while(in.ttype != -1 && in.sval == null){
				in.nextToken();
			}
			nextTokenVal = in.ttype;
		}

		return vals;

	}

}
