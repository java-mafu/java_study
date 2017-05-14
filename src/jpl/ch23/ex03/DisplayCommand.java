package jpl.ch23.ex03;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DisplayCommand extends Thread {

	InputStream in;
	OutputStream out;
	byte[] exitCommand;

	public DisplayCommand(InputStream in) {
		this(in, null);
	}

	public DisplayCommand(InputStream in, byte[] exitCommand) {
		this.in = in;
		this.out = System.out;
		this.exitCommand = exitCommand;
	}

	public static void main(String[] args) {
		try {
			String cmd = "";
			if (args.length == 0)
				cmd = "java";
			else {
				for (String str : args)
					cmd += str + " ";
			}
			byte[] b;
			String str = "version";
			b = str.getBytes();
			Process proc = displayCmd(cmd,b);
			proc.waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	public static Process displayCmd(String cmd) throws IOException {
		Process proc = Runtime.getRuntime().exec(cmd);
		new DisplayCommand(proc.getInputStream()).start();
		new DisplayCommand(proc.getErrorStream()).start();
		return proc;
	}

	public static Process displayCmd(String cmd, byte[] exitCommand) throws IOException {
		Process proc = Runtime.getRuntime().exec(cmd);
		new DisplayCommand(proc.getInputStream(), exitCommand).start();
		new DisplayCommand(proc.getErrorStream(), exitCommand).start();
		return proc;
	}

	private void addNumberOutput() {

		try {
			int number = 0;// 行数
			int data;// 今取得したデータ
			int prevData = 0;// ひとつ前のデータ
			int exitCommandChecker = 0;
			while ((data = in.read()) != -1) {
				if (exitCommand != null) {
					if (exitCommandChecker == exitCommand.length)
						return;
					if ((byte) data == exitCommand[exitCommandChecker])
						exitCommandChecker++;
					else if(exitCommandChecker !=0)
						exitCommandChecker = 0;
				}
				if (prevData == '\n' || prevData == 0) {
					number++;
					int distNum = 1;
					int dist = number;
					// 行の桁数を計算
					while ((dist /= 10) != 0) {
						distNum++;
					}
					dist = number;
					// 行番号を配列に保存(出力時に桁の上位から書き込むために
					// 数字の順番を入れ替えている)
					int[] distArray = new int[distNum];
					for (int i = 0; i < distNum; i++) {
						distArray[distNum - 1 - i] = dist % 10 + '0';
						dist /= 10;
					}
					for (int i = 0; i < distNum; i++)
						out.write(distArray[i]);
					out.write(':');
				}
				out.write(data);
				prevData = data;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		addNumberOutput();
	}

}
