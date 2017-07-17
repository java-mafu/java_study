package jpl.ch17.ex01;

public class SearchMemory {

	public static void main(String args[]) {
		System.out.println("起動時の空きメモリ：" + Runtime.getRuntime().freeMemory());
		Object obj[] = new Object[10000];
		for (int i = 0; i < 10000; i++) {
			obj[i] = new Object();
		}
		System.out.println("10000個オブジェクト生成した後の空きメモリ：" + Runtime.getRuntime().freeMemory());
		for (int i = 0; i < 10000; i++) {
			obj[i] = null;
		}
		System.gc();
		System.out.println("gc後の空きメモリ：" + Runtime.getRuntime().freeMemory());
	}
}
