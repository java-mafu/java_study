package jpl.ch11.ex03;

import java.util.Iterator;

import jpl.ch11.ex02.Attr;

/** Object型をジェネリック型「T」にしたことで，Attributedで
 * Attrを使用する際に型を決める必要がある．valueの自由度を高めることを
 * 考慮すると，ジェネリック型を使用する際はワイルドカード「?」を用いて定義するのが
 * よいと思う．また，valueを数値に限定することもできるため，Attrにジェネリック型を使用する
 * 有用性が得られる．
 * 下は，数値をvalueに利用するときのインターフェースである．
 * */
public interface Attributed {
	void add(Attr<? extends Number> newAttr);
	Attr<? extends Number> find(String attrName);
	Attr<? extends Number> remove(String attrName);
	Iterator<Attr<? extends Number>> attrs();

}
