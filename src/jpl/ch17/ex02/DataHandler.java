package jpl.ch17.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class DataHandler {
	private WeakReference<File> lastFile;
	private WeakReference<byte[]> lastData;

	byte[] readFile(File file) {
		byte[] data;

		// データを記憶しているか調べる
		if (file != null && file.equals(lastFile.get())) {
			data = lastData.get();
			if (data != null)
				return data;
		}

		// 記憶していないので読み込む
		data = readBytesFromFile(file);
		lastFile = new WeakReference<File>(file);
		lastData = new WeakReference<byte[]>(data);
		return data;
	}

	private byte[] readBytesFromFile(File file) {
		byte[] results = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			int buffer = 0;
			int b;
			while((b = (byte) fis.read()) != -1){
				buffer += b;
			}
			results = new byte[buffer];
			fis.read(results);
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return results;
	}
}
