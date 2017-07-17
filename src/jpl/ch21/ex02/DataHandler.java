package jpl.ch21.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.WeakHashMap;

public class DataHandler {
	private WeakHashMap<String, byte[]> lastData;

	byte[] readFile(File file) throws IOException {
		byte[] data;

		// データを記憶しているか調べる
		if (lastData.containsKey(file.getName())) {
			data = lastData.get(file.getName());
			if (data != null)
				return data;
		}

		// 記憶していないので、読み込む
		data = readBytesFromFile(file);
		lastData.put(file.getName(), data);
		return data;
	}

	private byte[] readBytesFromFile(File file) throws IOException {
		InputStream in = new FileInputStream(file);
		int filebyte;
		ArrayList<Byte> byteList = new ArrayList<Byte>();
		while ((filebyte = in.read()) != -1) {
			byteList.add((byte) filebyte);
		}
		byte[] result = new byte[byteList.size()];
		for (int i = 0; i < byteList.size(); i++)
			result[i] = byteList.get(i);
		return result;
	}
}
