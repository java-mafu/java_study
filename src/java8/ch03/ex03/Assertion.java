package java8.ch03.ex03;

import java.util.function.BooleanSupplier;
import java.util.logging.Logger;

/** assertが予約語であり、ライブラリの機能として提供されなかった理由
JDK1.4では遅延実行ができないため、ライブラリとして提供すると、assertの数だけ処理が余計にかかってしまう。
予約語として提供することでデバックとリリースの場合で実行するか使い分けることで、上記問題に対応した。

assertをjava8で実装するなら以下。（引数がSupplier以外の方法はわからなかった）
*/
public class Assertion {
	static boolean assertOption;
	public static void assertion(BooleanSupplier condition, String comment){
		if(assertOption && !condition.getAsBoolean()){
			Logger.getLogger("Exception").info(comment);
		}
	}

	public static void main(String[] args) {
		//assertOptionがtrueならassertを実行する
		int a = 5;

		assertOption = true;
		assertion(() -> a == 5 , "Error1");
		assertion(() -> a == 3 , "Error2");
		assertOption = false;
		assertion(() -> a == 5 , "Error3");
		assertion(() -> a == 3 , "Error4");
	}

}
