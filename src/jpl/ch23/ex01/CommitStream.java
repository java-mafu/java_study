package jpl.ch23.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CommitStream extends Thread {
	InputStream in;
	OutputStream out;

	public CommitStream(InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
	}

	public static void main(String[] args) {
		try {
			String cmd = "java -version";
			Process proc = userProg(cmd);
			proc.waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	public static Process userProg(String cmd) throws IOException {
		Process proc = Runtime.getRuntime().exec(cmd);
		plugTogether(System.in, proc.getOutputStream());
		plugTogether(System.out, proc.getInputStream());
		plugTogether(System.err, proc.getErrorStream());
		return proc;
	}

	public static void plugTogether(Object in, Object out) {
		if (in instanceof InputStream && out instanceof OutputStream)
			new CommitStream((InputStream) in, (OutputStream) out).start();
		else if (in instanceof OutputStream && out instanceof InputStream)
			new CommitStream((InputStream) out, (OutputStream) in).start();
		else
			System.out.println("plugTogetherの引数が未対応");
	}

	public void run() {
		try {
			int data;
			while ((data = in.read()) != -1) {
				out.write(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
