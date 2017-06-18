package java8.ch01.ex06;

import java.util.concurrent.Callable;

public class RunnableExMethod {

	public static Runnable uncheck(RunnableEx runner) {

		return () -> {
			try {
				runner.run();
			} catch (Exception e) {
				// 何もしない
			}
		};
	}

	/**
	 * Callableを使用したメソッド Callableは，callメソッドにて戻り値を必要とするため，
	 * たとえ戻り値Voidとしても，returnする必要がある．
	 * 従って，uncheckCallableメソッドに渡すcallの実装に戻り値を定義しておけば， Callableを使用することができる．
	 */
	public static Runnable uncheckCallable(Callable<Void> runner) {
		return () -> {
			try {
				runner.call();
			} catch (Exception e) {
				// 何もしない
			}
		};
	}

	public static void main(String[] args) {
		new Thread(uncheck(() -> {
			System.out.println("Zzz");
			Thread.sleep(1000);
		})).start();
		// catch(interruptExeption)が必要ありません！

		new Thread(uncheckCallable(() -> {
			System.out.println("Zzz");
			Thread.sleep(1000);
			return null;
		})).start();
		// catch(interruptExeption)が必要ありません！
	}
}
