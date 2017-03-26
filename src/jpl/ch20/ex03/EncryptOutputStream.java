package jpl.ch20.ex03;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;


//反転してからxorをとる暗号化Filter
public class EncryptOutputStream  extends FilterOutputStream{

	//ORで暗号化するためのkey
	static final byte key = 0x7;
	protected EncryptOutputStream(OutputStream in) {
		super(in);
	}

	@Override
	public void write(int ch) throws IOException{
		int b = ch^key;
		super.write(b);
	}

}
