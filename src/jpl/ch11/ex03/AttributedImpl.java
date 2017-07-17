package jpl.ch11.ex03;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jpl.ch11.ex02.Attr;

public class AttributedImpl implements Attributed, Iterator<Attr<? extends Number>> {

	protected Map<String, Attr<? extends Number>>attrTable =
			new HashMap<String, Attr<? extends Number>>();

	@Override
	public void add(Attr<? extends Number> newAttr) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public Attr<? extends Number> find(String attrName) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Attr<? extends Number> remove(String attrName) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Iterator<Attr<? extends Number>> attrs() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean hasNext() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public Attr<? extends Number> next() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
