package java8.ch03.ex23;

import java.util.function.Function;

/*Pair Classは自分で作成するということで良い？*/
public class Pair<T> {
	T first;
	T second;

	public Pair(T first, T second){
		this.first = first;
		this.second = second;
	}

	public <U> Pair<U> map(Function<T,U> f){
		return new Pair<U>(f.apply(first), f.apply(second));
	}


}
