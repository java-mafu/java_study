package jpl.ch21.ex01;

import java.io.FileInputStream;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class FilterLineReader extends FilterReader {
	
	protected FilterLineReader(Reader in) {
		super(in);
	}

	public Byte[] readLine() throws IOException {
		int b;
		List<Byte> bytes = new ArrayList<Byte>();
		while ((b = in.read()) != -1 && b != '\n')
			bytes.add((byte) b);
		Byte[] result = null;
		if (bytes != null)
			result = (Byte[]) bytes.toArray(new Byte[0]);
		return result;
	}
}
