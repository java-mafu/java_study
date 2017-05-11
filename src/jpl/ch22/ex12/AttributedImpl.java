package jpl.ch22.ex12;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AttributedImpl implements Attributed, Iterator<Attr> {

	protected Map<String, Attr>attrTable =
			new HashMap<String, Attr>();

	@Override
	public void add(Attr newAttr) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public Attr find(String attrName) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Attr remove(String attrName) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Iterator<Attr> attrs() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean hasNext() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public Attr next() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


}
