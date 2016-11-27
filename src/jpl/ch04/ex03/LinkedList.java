package jpl.ch04.ex03;

interface LinkedList extends Cloneable{

	Object getList();
	void setList(Object list);
	LinkedList getNextLink();
	int totalLinkNum();
	LinkedList clone();
}
