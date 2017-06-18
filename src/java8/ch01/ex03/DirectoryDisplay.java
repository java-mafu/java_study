package java8.ch01.ex03;

import java.io.File;

public class DirectoryDisplay {
	public static String[] showDirectries(String directoryName) {
		File file = new File(directoryName);
		return file.list((subfile, str) -> subfile.isDirectory());
	}

	/**
	 * キャプチャされている変数の確認 以下のコードではコンパイルエラーが起きる．
	 * 今回はラムダ式外の，directoryNameとfileがキャプチャされているため変更が許可されない
	 */
	/*
	 * public static String[] captureValueCheck(String directoryName) { File
	 * file = new File(directoryName); file.list((subfile, str) -> { file = new
	 * File("fuga"); directoryName = "fuga"; return subfile.isDirectory(); });
	 * return null; }
	 */
}
