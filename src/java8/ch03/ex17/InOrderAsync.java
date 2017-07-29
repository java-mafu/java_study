package java8.ch03.ex17;

import java.util.function.Consumer;


public class InOrderAsync {
	public static <T> void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
		Thread tf = new Thread() {
			public void run() {
				try {
					first.run();
				} catch (Throwable t) {
					handler.accept(t);
				}
			}
		};
		Thread ts = new Thread() {
			public void run() {
				try {
					second.run();
				} catch (Throwable t) {
					handler.accept(t);
				}
			}
		};
		tf.start();
		ts.start();
	}


	public static void main(String[] args) {
		Runnable f = ()->System.out.println("first");
		Runnable s = ()->System.out.println("second");
		Consumer<Throwable> handler = System.out::println;
		doInParallelAsync(f,s,handler);

		//以下の記述がしたい
		//f = ()->System.out.println("first");
		//s = ()->{Thread.sleep(100);};
		doInParallelAsync(f,s,handler);
	}

}
