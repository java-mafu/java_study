package java8.ch01.ex02;

import java.io.File;

public class DirectoryDisplay {
	public static File[] showDirectriesLambda(String directoryName) {
		File file = new File(directoryName);
		return file.listFiles((subfile) -> {
			return subfile.isDirectory();
		});
	}

	public static File[] showDirectriesMethod(String directoryName) {
		File file = new File(directoryName);
		File[] dirs = file.listFiles(File::isDirectory);
		return dirs;
	}
}
