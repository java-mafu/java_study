package jpl.ch21.ex02;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class DataHandler {
	private WeakHashMap<String, byte[]> lastData;

	byte[] readFile(File file) {
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
}
