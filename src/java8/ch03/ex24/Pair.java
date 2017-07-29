package java8.ch03.ex24;

import java.util.function.Function;

/**
 *  flatMapは、ネストしない形式でmapを作成するのが役割であるため、
 * Pairでは、作成できない
 * 無理やり作成しようとすると必然的にPair内にネストするPairが生まれる形になる
 * */
public class Pair<T> {
	T first;
	T second;

	public Pair(T first, T second){
		this.first = first;
		this.second = second;
	}

	public <U> Pair<U> map(Function<? super T,U> f){
		return new Pair<U>(f.apply(first), f.apply(second));
	}

	//作ったものの、flatMapではない
	public <U> Pair<Pair<U>> flatMap(Function<? super T,Pair<U>> f){
		return new Pair<Pair<U>>(f.apply(first), f.apply(second));
	}

}
