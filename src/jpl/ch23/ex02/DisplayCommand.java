package jpl.ch23.ex02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DisplayCommand extends Thread {

	InputStream in;
	OutputStream out;

	public DisplayCommand(InputStream in) {
		this.in = in;
		this.out = System.out;
	}

	public static void main(String[] args) {
		try {
			String cmd ="";
			if (args.length == 0)
				cmd = "java -h";
			else{
				for(String str : args)
					cmd += str+" ";
			}
			Process proc = displayCmd(cmd);
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

	private void addNumberOutput() {

		try {
			int number = 0;// 行数
			int data;// 今取得したデータ
			int prevData = 0;// ひとつ前のデータ
			while ((data = in.read()) != -1) {
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
