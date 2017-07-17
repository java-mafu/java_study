package jpl.ch01.ex15;

interface LookupEdit extends Lookup {
	void add(String name, Object value);
	boolean remove(String name);
}
