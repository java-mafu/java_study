package jpl.ch21.ex04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ShortStrings implements Iterator<String> {

		private Iterator<String> strings; //元の文字列
		private String nextShort; //次が不明ならばnull
		private final int maxLen;// この長さ以下の文字列だけを返す

		public ShortStrings(Iterator<String> strings, int maxLen){
			this.strings = strings;
			this.maxLen = maxLen;
			nextShort = null;
		}


	@Override
	public boolean hasNext() {
		if(nextShort != null)
			return true;

		while(strings.hasNext()){
			nextShort = strings.next();
			if (nextShort.length() <= maxLen)
				return true;
		}
		nextShort = null;
		return false;
	}

	@Override
	public String next() {
		if (nextShort == null && !hasNext())
			throw new NoSuchElementException();
		String n = nextShort;
		nextShort = null;
		return n;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args){
		Collection<String> coll = new ArrayList<String>();
		coll.add("hoge");
		coll.add("hhoge");
		coll.add("hhhoge");
		coll.add("hoge");
		coll.add("hhoge");
		coll.add("hhhoge");
		coll.add("hoge");
		coll.add("hhoge");
		coll.add("hhhoge");

		ShortStrings it = new ShortStrings(coll.iterator(), 4);
		while(it.hasNext()){
			String str = it.next();
			System.out.println(str);
		}
	}
}
