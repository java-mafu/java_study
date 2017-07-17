package jpl.ch10.ex03;
import jpl.ch06.ex01.Week;

public class WeekIfElse {
	//日曜以外仕事があることを想定
	public static boolean checkWorkDay(Week day){
		if(day == Week.MONDAY)
			return true;
		else if(day == Week.TUESDAY)
			return true;
		else if(day == Week.WEDNESDAY)
			return true;
		else if(day == Week.THURSDAY)
			return true;
		else if(day == Week.FRIDAY)
			return true;
		else if(day == Week.SATURDAY)
			return true;
		else if(day == Week.SUNDAY)
			return false;
		else//念のため
			return false;
	}
}
