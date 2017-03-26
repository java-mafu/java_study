package jpl.ch20.ex03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream{

	//復元用のkey
	byte key = EncryptOutputStream.key;
	protected DecryptInputStream(InputStream in) {
		super(in);
	}

	@Override
	public int read() throws IOException{
		int b = super.read() ^ key;
		return b;

	}

}
