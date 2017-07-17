package java8.ch01.ex05;

public class UseRunnable {
	/*
	 * ちょうどよい，プロジェクトが分からなかったため，単純に作成 複雑なコードも見やすくできそうと感じた
	 */
	public static void samplejava7() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("a");
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("b");
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("c");
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("d");
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("e");
			}
		}).start();
	}

	public static void samplejava8() {
		new Thread(() -> System.out.println("a")).start();
		new Thread(() -> System.out.println("b")).start();
		new Thread(() -> System.out.println("c")).start();
		new Thread(() -> System.out.println("d")).start();
		new Thread(() -> System.out.println("e")).start();
	}

	public static void main(String[] args) {
		samplejava7();
		samplejava8();
	}
}
