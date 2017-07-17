package jpl.ch22.ex09;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CSVReader {
	public static List<String[]> readCSVTablePattern1(Readable source, int cellNum) throws IOException {
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();
		String exp = "^";
		String illegamExp;
		for (int i = 0; i < cellNum; i++)
			exp += "(.*),";
		exp = exp.substring(0, exp.lastIndexOf(","));
		illegamExp = exp + ",(.*)";
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		Pattern illegalPat = Pattern.compile(illegamExp, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			String line = in.findInLine(illegalPat);
			if (line != null) {// 行が多すぎの時は例外
				throw new IOException("input format error");
			}
			line = in.findInLine(pat);
			if (line != null) {
				String[] cells = new String[cellNum];
				MatchResult match = in.match();
				for (int i = 0; i < cellNum; i++)
					cells[i] = match.group(i + 1);
				vals.add(cells);
				in.nextLine(); // 改行を読み飛ばし
			} else {
				if (in.findInLine(",") == null)
					in.nextLine(); // 改行を読み飛ばし
				else
					throw new IOException("input format error");
			}
		}

		IOException ex = in.ioException();
		if (ex != null)
			throw ex;

		return vals;

	}

	public static List<String[]> readCSVTablePattern2(Readable source, int cellNum) throws IOException {
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();
		String exp = "";
		String illegamExp;
		for (int i = 0; i < cellNum; i++)
			exp += "([^,]*),";
		exp = exp.substring(0, exp.lastIndexOf(","));
		illegamExp = exp + ",([^,]*)";
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		Pattern illegalPat = Pattern.compile(illegamExp, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			String line = in.findInLine(illegalPat);
			if (line != null) {// 行が多すぎの時は例外
				throw new IOException("input format error");
			}
			line = in.findInLine(pat);
			if (line != null) {
				String[] cells = new String[cellNum];
				MatchResult match = in.match();
				for (int i = 0; i < cellNum; i++)
					cells[i] = match.group(i + 1);
				vals.add(cells);
				in.nextLine(); // 改行を読み飛ばし
			} else {
				if (in.findInLine(",") == null)
					in.nextLine(); // 改行を読み飛ばし
				else
					throw new IOException("input format error");
			}
		}

		IOException ex = in.ioException();
		if (ex != null)
			throw ex;

		return vals;

	}

	public static List<String[]> readCSVTablePattern3(Readable source, int cellNum) throws IOException {
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();
		String exp = "^";
		String illegamExp;
		for (int i = 0; i < cellNum; i++)
			exp += "(.*),";
		exp = exp.substring(0, exp.lastIndexOf(","));
		illegamExp = exp + ",(.*)";
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		Pattern illegalPat = Pattern.compile(illegamExp, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			String line = in.findInLine(illegalPat);
			if (line != null) {// 行が多すぎの時は例外
				throw new IOException("input format error");
			}
			line = in.findInLine(pat);
			if (line != null) {
				String[] cells = new String[cellNum];
				MatchResult match = in.match();
				for (int i = 0; i < cellNum; i++)
					cells[i] = match.group(i + 1);
				vals.add(cells);
				in.nextLine(); // 改行を読み飛ばし
			} else {
				if (in.findInLine(",") == null)
					in.nextLine(); // 改行を読み飛ばし
				else
					throw new IOException("input format error");
			}
		}

		IOException ex = in.ioException();
		if (ex != null)
			throw ex;

		return vals;

	}

	public static List<String[]> readCSVTablePattern4(Readable source, int cellNum) throws IOException {
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();
		String exp = "^";
		String illegamExp;
		for (int i = 0; i < cellNum; i++)
			exp += "(.*),";
		exp = exp.substring(0, exp.lastIndexOf(","));
		illegamExp = exp + ",(.*)";
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		Pattern illegalPat = Pattern.compile(illegamExp, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			String line = in.findInLine(illegalPat);
			if (line != null) {// 行が多すぎの時は例外
				throw new IOException("input format error");
			}
			line = in.findInLine(pat);
			if (line != null) {
				String[] cells = new String[cellNum];
				MatchResult match = in.match();
				for (int i = 0; i < cellNum; i++)
					cells[i] = match.group(i + 1);
				vals.add(cells);
				in.nextLine(); // 改行を読み飛ばし
			} else {
				if (in.findInLine(",") == null)
					in.nextLine(); // 改行を読み飛ばし
				else
					throw new IOException("input format error");
			}
		}

		IOException ex = in.ioException();
		if (ex != null)
			throw ex;

		return vals;

	}

	public static void main(String[] args) {

	}

}
