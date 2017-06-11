package java8.ch01.ex01;

import java.util.Arrays;

/**
 * A.sortメソッドを呼び出したThreadで実行される
 * 以下のコードは，Threadの数が変更されたときに変更後のThread数を表示するプログラムだが，
 * mainを実行しているThreadが終了する場合以外で，Thread数が変化することはなかった
 */
public class Sort {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Thread:" + Thread.getAllStackTraces().size() + "個");
				System.out.println(Thread.getAllStackTraces().toString());
				int firstThreadNum = Thread.getAllStackTraces().size();
				int currentThreadNum = firstThreadNum;
				long startTime = System.currentTimeMillis();
				while (currentThreadNum <= firstThreadNum && System.currentTimeMillis() - startTime < 10000) {
					currentThreadNum = Thread.getAllStackTraces().size();
				}
				System.out.println("Thread:" + firstThreadNum + "個->" + currentThreadNum + "個");
				System.out.println(Thread.getAllStackTraces().toString());
			}

		}).start();
		String[] strs = { "aaa", "bbbb" };
		Arrays.sort(strs, (first, second) -> Integer.compare(first.length(), second.length()));
		/*
		 * sortが別スレッドの場合に，新たなスレッドが作成される前にmainが終了することの 無いように対策
		 */
		try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			System.out.println("sleep failed");
		}
	}
}