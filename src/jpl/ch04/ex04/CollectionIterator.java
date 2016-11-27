package jpl.ch04.ex04;

import java.util.Iterator;

public interface CollectionIterator extends Iterator<Object> {
	//イテレーターにアクセスするメソッドが必要
	void add(Object obj);
	Object find(String Name);
	Object remove(String Name);
}
