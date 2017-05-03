package jpl.ch22.ex04;

import java.util.Iterator;
import java.util.Observable;

import jpl.ch11.ex02.Attr;
import jpl.ch11.ex03.Attributed;

public class AttributedObserver extends Observable implements Attributed {

	private State finalDoing;

	public State getState(){
		return finalDoing;
	}

	@Override
	public void add(Attr<? extends Number> newAttr) {
		// addが呼ばれたことを通知
		finalDoing = State.ADDED;
		notifyObservers(finalDoing);
	}

	@Override
	public Attr<? extends Number> find(String attrName) {
		// findが呼ばれたことを通知
		finalDoing = State.FOUND;
		notifyObservers(finalDoing);
		return null;
	}

	@Override
	public Attr<? extends Number> remove(String attrName) {
		// removeが呼ばれたことを通知
		finalDoing = State.REMOVED;
		notifyObservers(finalDoing);
		return null;
	}

	@Override
	public Iterator<Attr<? extends Number>> attrs() {
		// attrsが呼ばれたことを通知
		finalDoing = State.DOATTRS;
		notifyObservers(finalDoing);
		return null;
	}

}
