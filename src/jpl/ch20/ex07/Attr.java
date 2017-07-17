package jpl.ch20.ex07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Attr {
	private final String name;
	private Object value = null;

	public Attr(String name) {
		this(name, null);
	}

	public Attr(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public Attr(DataInputStream in) throws IOException {
		String str = in.readUTF();
		this.name = str.substring(0, str.indexOf("="));
		this.value = str.substring(str.indexOf("'") + 1, str.lastIndexOf("'"));
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public Object setValue(Object newValue) {
		Object oldVal = value;
		value = newValue;
		return oldVal;
	}

	public String toString() {
		return name + "='" + value + "'";
	}

	public void writeData(OutputStream fout) throws IOException {
		DataOutputStream out; 
		out = new DataOutputStream(fout);
		out.writeUTF(this.toString());
	}

}
