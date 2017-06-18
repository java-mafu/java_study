package java8.ch01.ex07;

public class DoubleRunnable {

	public static Runnable andThen(Runnable first, Runnable second) {
		return () -> {
			first.run();
			second.run();
		};
	}

	public static void main(String[] args) {
		Runnable runner = andThen(() -> System.out.println("first!"), () -> System.out.println("second!"));
		new Thread(runner).start();
	}

}
