package jpl.ch21.ex04;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ShortStringsListIt implements ListIterator<String> {
	private ListIterator<String> strings; // 元の文字列
	private String prevShort; // 前が不明ならばnull
	private String nextShort; // 次が不明ならばnull
	private final int maxLen;// この長さ以下の文字列だけを返す
	private int nextIdx; // 次のインデックスを保持する
	private int prevIdx; // 前のインデックスを保持する

	private int removeIdx; // 取り除くインデックス

	public ShortStringsListIt(ListIterator<String> strings, int maxLen) {
		this.strings = strings;
		this.maxLen = maxLen;
		prevShort = null;
		nextShort = null;
	}

	@Override
	public boolean hasNext() {
		if (nextShort != null)
			return true;

		while (strings.hasNext()) {
			nextShort = strings.next();
			nextIdx = strings.nextIndex();
			if (nextShort.length() <= maxLen) {
				return true;
			}
		}
		nextIdx = strings.nextIndex();
		nextShort = null;
		return false;
	}

	@Override
	public String next() {
		if (nextShort == null && !hasNext())
			throw new NoSuchElementException();
		removeIdx = nextIdx;
		String n = nextShort;
		nextShort = null;
		return n;
	}

	@Override
	public boolean hasPrevious() {
		if (prevShort != null)
			return true;

		while (strings.hasPrevious()) {
			prevShort = strings.previous();
			prevIdx = strings.previousIndex();
			if (prevShort.length() <= maxLen)
				return true;
		}
		prevIdx = strings.previousIndex();
		prevShort = null;
		return false;
	}

	@Override
	public void remove() {
		strings.remove();
	}

	@Override
	public String previous() {
		if (prevShort == null && !hasPrevious())
			throw new NoSuchElementException();
		removeIdx = prevIdx;
		String n = prevShort;
		prevShort = null;
		return n;
	}

	@Override
	public int nextIndex() {
		hasNext();
		return nextIdx;
	}

	@Override
	public int previousIndex() {
		hasPrevious();
		return prevIdx;
	}

	@Override
	public void set(String e) {
		strings.set(e);
	}

	@Override
	public void add(String e) {
		strings.add(e);
	}

	public static void main(String[] args) {
		List<String> coll = new ArrayList<String>();
		coll.add("hoge");
		coll.add("hhoge");
		coll.add("hhhoge");
		coll.add("hoge");
		coll.add("hhoge");
		coll.add("hhhoge");
		coll.add("hoge");
		coll.add("hhoge");
		coll.add("hhhoge");

		ShortStringsListIt it = new ShortStringsListIt(coll.listIterator(coll.size() / 2), 4);
		while (it.hasNext()) {
			String str = it.next();
			System.out.println(str);
		}
		while (it.hasPrevious()) {
			String str = it.previous();
			System.out.println(str);
		}
	}

}
