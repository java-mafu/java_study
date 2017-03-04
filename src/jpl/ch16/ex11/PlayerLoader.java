package jpl.ch16.ex11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlayerLoader extends ClassLoader {
	public Class<?> findClass(String name)
	throws ClassNotFoundException
	{
		String classpath = PlayerLoader.class.getPackage().getName();
		name = "src/"+ classpath.replace(".", "/") + "/" + name;
		try {
			byte [] buf = bytesForClass(name);
			return defineClass(null, buf, 0, buf.length);
		} catch (IOException e){
			throw new ClassNotFoundException(e.toString());
		}
	}

	protected byte[] bytesForClass(String name)
	throws IOException, ClassNotFoundException
	{
		FileInputStream in = null;
		try {
			in = streamFor(name + ".class");
			int length = in.available();
			if (length == 0)
				throw new ClassNotFoundException(name);
			byte[] buf = new byte[length];
			in.read(buf);
			return buf;
		} finally{
			if (in != null)
				in.close();
		}
	}

	private FileInputStream streamFor(String string) throws FileNotFoundException {
		return new FileInputStream(string);
	}
}
