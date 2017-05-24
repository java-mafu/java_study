package jpl.ch24.ex03;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class DisplayDate {

	public static void showDate(String str) {
		DateFormat fmt;
		Date date;

		System.out.println("入力文字列："+str);
		fmt = DateFormat.getDateInstance(DateFormat.SHORT);
		try {
			date = fmt.parse(str);
			System.out.printf(fmt.format(date));
		} catch (ParseException e) {
			System.out.print("parse failed");
		}

		System.out.println();


		fmt = DateFormat.getDateInstance(DateFormat.MEDIUM);
		try {
			date = fmt.parse(str);
			System.out.printf(fmt.format(date));
		} catch (ParseException e) {
			System.out.print("parse failed");
		}
		System.out.println();

		fmt = DateFormat.getDateInstance(DateFormat.LONG);
		try {
			date = fmt.parse(str);
			System.out.printf(fmt.format(date));
		} catch (ParseException e) {
			System.out.print("parse failed");
		}
		System.out.println();

		fmt = DateFormat.getDateInstance(DateFormat.FULL);
		try {
			date = fmt.parse(str);
			System.out.printf(fmt.format(date));
		} catch (ParseException e) {
			System.out.print("parse failed");
		}
		System.out.println();
	}


	public static void main(String[] args) {
		showDate("2017/1/1");
		showDate("17/01/01");
		showDate("01/01");
		showDate("２０１７/１/１");
		showDate("2017_01_01");
		showDate("2017-01-01");
		showDate("2017年1月1日");
		showDate("二〇一七年一月一日");
		showDate("２０１７年１月１日");
	}

}
