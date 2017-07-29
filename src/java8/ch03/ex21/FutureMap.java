package java8.ch03.ex21;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class FutureMap {
	public static <T, U> Future<U> map(Future<T> future, Function<T, U> function){
		return new Future<U>(){

			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				return future.cancel(mayInterruptIfRunning);
			}

			@Override
			public boolean isCancelled() {
				return future.isCancelled();
			}

			@Override
			public boolean isDone() {
				// TODO 自動生成されたメソッド・スタブ
				return future.isDone();
			}

			@Override
			public U get() throws InterruptedException, ExecutionException {
				// TODO 自動生成されたメソッド・スタブ
				return function.apply(future.get());
			}

			@Override
			public U get(long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException, TimeoutException {
				// TODO 自動生成されたメソッド・スタブ
				return function.apply(future.get(timeout, unit));
			}

		};
	}
}
