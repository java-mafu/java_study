package jpl.ch07.ex02;

public class BasicFigure {
	static boolean bl = true;
	static char ch = 1234;
	static byte by = 12;
	static short sh = 1234;
	static int in = 12345678;
	static long ln = 123456789098765L;
	static float fl = 1234.5678f;
	static double db = 1234.56789876;


	public static void charTest(){
		String output = "charに別の型を代入\n";
		char test;
		test = (char) ch;
		output += " char:"+test;
		test = (char) by;
		output += " byte:"+test;
		test = (char) sh;
		output += " short:"+test;
		test = (char) in;
		output += " int:"+test;
		test = (char) ln;
		output += " long:"+test;
		test = (char) fl;
		output += " float:"+test;
		test = (char) db;
		output += " double:"+test;
		System.out.println(output);
	}

	public static void byteTest(){
		String output = "byteに別の型を代入\n";
		byte test;
		test = (byte) ch;
		output += " char:"+test;
		test = (byte) by;
		output += " byte:"+test;
		test = (byte) sh;
		output += " short:"+test;
		test = (byte) in;
		output += " int:"+test;
		test = (byte) ln;
		output += " long:"+test;
		test = (byte) fl;
		output += " float:"+test;
		test = (byte) db;
		output += " double:"+test;
		System.out.println(output);
	}

	public static void shortTest(){
		String output = "shortに別の型を代入\n";
		short test;
		test = (short) ch;
		output += " char:"+test;
		test = (short) by;
		output += " byte:"+test;
		test = (short) sh;
		output += " short:"+test;
		test = (short) in;
		output += " int:"+test;
		test = (short) ln;
		output += " long:"+test;
		test = (short) fl;
		output += " float:"+test;
		test = (short) db;
		output += " double:"+test;
		System.out.println(output);
	}

	public static void intTest(){
		String output = "intに別の型を代入\n";
		int test;
		test = (int) ch;
		output += " char:"+test;
		test = (int) by;
		output += " byte:"+test;
		test = (int) sh;
		output += " short:"+test;
		test = (int) in;
		output += " int:"+test;
		test = (int) ln;
		output += " long:"+test;
		test = (int) fl;
		output += " float:"+test;
		test = (int) db;
		output += " double:"+test;
		System.out.println(output);
	}

	public static void longTest(){
		String output = "longに別の型を代入\n";
		long test;
		test = (long) ch;
		output += " long:"+test;
		test = (long) by;
		output += " byte:"+test;
		test = (long) sh;
		output += " short:"+test;
		test = (long) in;
		output += " int:"+test;
		test = (long) ln;
		output += " long:"+test;
		test = (long) fl;
		output += " float:"+test;
		test = (long) db;
		output += " double:"+test;
		System.out.println(output);
	}

	public static void floatTest(){
		String output = "floatに別の型を代入\n";
		float test;
		test = (float) ch;
		output += " float:"+test;
		test = (float) by;
		output += " byte:"+test;
		test = (float) sh;
		output += " short:"+test;
		test = (float) in;
		output += " int:"+test;
		test = (float) ln;
		output += " long:"+test;
		test = (float) fl;
		output += " float:"+test;
		test = (float) db;
		output += " double:"+test;
		System.out.println(output);
	}

	public static void doubleTest(){
		String output = "doubleに別の型を代入\n";
		double test;
		test = (double) ch;
		output += " double:"+test;
		test = (double) by;
		output += " byte:"+test;
		test = (double) sh;
		output += " short:"+test;
		test = (double) in;
		output += " int:"+test;
		test = (double) ln;
		output += " long:"+test;
		test = (double) fl;
		output += " float:"+test;
		test = (double) db;
		output += " double:"+test;
		System.out.println(output);
	}


	public static void main(String[] args) {
		charTest();
		byteTest();
		shortTest();
		intTest();
		longTest();
		floatTest();
		doubleTest();

	}

}
