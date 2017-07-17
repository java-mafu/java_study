package jpl.ch20.ex09;

import java.io.File;
import java.io.IOException;

public class FileInformation {

	public static String getFileInfo(String... files) throws IOException{
		String data = "";
		for(String filePath : files){
			File file = new File(filePath);
			data += "Name:" + file.getName() + "\n";
			data += "Path:" + file.getPath() + "\n";
			data += "AbsolutePath:" + file.getAbsolutePath() + "\n";
			data += "CanonicalPath:" + file.getCanonicalPath() + "\n";
			data += "Parent:" + file.getParent() + "\n";
			data += "lastModified:" + file.lastModified() + "\n";
			data += "length:" + file.length() + "\n";
			data += "\n";
		}
		return data;
	}

	public static void main(String[] args) throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/jpl/ch20/ex01";
		String f1 = filePath + "/input.txt";
		filePath = System.getProperty("user.dir") + "/src/jpl/ch20/ex02";
		String f2 = filePath + "/input.txt";
		filePath = System.getProperty("user.dir") + "/src/jpl/ch20/ex04";
		String f3 = filePath + "/input.txt";
		filePath = System.getProperty("user.dir") + "/src/jpl/ch20/ex05";
		String f4 = filePath + "/input.txt";
		System.out.println(getFileInfo(f1,f2,f3,f4));
	}

}
