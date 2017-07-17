package java8.ch01.ex04;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FileSorter {
	public static File[] sortFiles(File[] files) {
		List<File> sortedFiles = new LinkedList<File>();
		List<File> directoryList = new LinkedList<File>();
		List<File> fileList = new LinkedList<File>();

		for (File file : files) {
			if (file.isDirectory())
				directoryList.add(file);
			else
				fileList.add(file);
		}
		directoryList.sort((str1, str2) -> str1.compareTo(str2));
		fileList.sort((str1, str2) -> str1.compareTo(str2));
		sortedFiles.addAll(directoryList);
		sortedFiles.addAll(fileList);
		return sortedFiles.toArray(files);
	}
}
