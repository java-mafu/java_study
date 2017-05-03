package jpl.ch21.ex07;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * ArrayListは内部で使用するべきである サブクラスにすると，類似したメソッドが複数存在することになり， 扱いにくいため
 */
public class StackArrayList<E> {

	private ArrayList<E> stack;

	public StackArrayList() {
		stack = new ArrayList<E>();
	}

	public boolean empty() {
		return stack.isEmpty();
	}

	public E peek() {
		try {
			return stack.get(0);
		} catch (IndexOutOfBoundsException e) {
			throw new EmptyStackException();
		}
	}

	public E pop() {
		try {
			return stack.remove(0);
		} catch (IndexOutOfBoundsException e) {
			throw new EmptyStackException();
		}
	}

	public E push(E item) {
		stack.add(item);
		return item;
	}

	public int search(Object o) {
		return stack.indexOf(o);
	}
}
