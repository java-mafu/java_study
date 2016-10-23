package jpl.ch02.ex06;
import jpl.ch02.ex05.Vehicle;

class LinkedList {
	Object list;
	LinkedList nextlink;

	public static void main(String args[]){
		Vehicle car1 = new Vehicle();
		LinkedList c1 = new LinkedList();
		c1.list = car1;

		Vehicle car2 = new Vehicle();
		LinkedList c2 = new LinkedList();
		c2.list = car2;
		c2.nextlink = c1;

		Vehicle car3 = new Vehicle();
		LinkedList c3 = new LinkedList();
		c3.list = car3;
		c3.nextlink = c2;
	}
}
