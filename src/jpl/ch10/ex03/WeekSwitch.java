package jpl.ch10.ex03;
import jpl.ch06.ex01.Week;

public class WeekSwitch {
	//日曜以外仕事があることを想定
		public static boolean checkWorkDay(Week day){
			switch(day){
			case MONDAY:
				return true;
			case TUESDAY:
				return true;
			case WEDNESDAY:
				return true;
			case THURSDAY:
				return true;
			case FRIDAY:
				return true;
			case SATURDAY:
				return true;
			case SUNDAY:
				return false;
			default:
				return false;
			}
		}
}
