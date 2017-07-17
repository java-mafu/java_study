package jpl.ch20.ex11;

import java.io.File;
import java.io.FilenameFilter;

public class DisplayFiles implements FilenameFilter{

	String suffix = null;

	public DisplayFiles(String suffix){
		this.suffix = suffix;
	}
	public boolean accept(File dir, String name){
		String fileName = new File(dir, name).getName();
		return fileName.lastIndexOf("."+suffix) != -1;
	}

	public static String[] showFilterFiles(File dir, String suffix){
		String[] files = dir.list(new DisplayFiles(suffix));
		return files;
	}

	public static void main(String[] args) {
		String[] strs = showFilterFiles(new File(args[0]), args[1]);
		for(String str : strs)
			System.out.println(str);
	}

}
