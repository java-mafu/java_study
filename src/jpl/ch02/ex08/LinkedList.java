package jpl.ch02.ex08;
import jpl.ch02.ex05.Vehicle;

class LinkedList {
	Object list;
	LinkedList nextlink;

	LinkedList(){
		this.list = null;
		this.nextlink = null;
	}

	LinkedList(Object list){
		this();
		this.list = list;
	}

	LinkedList(Object list, LinkedList nextlink){
		this.list = list;
		this.nextlink = nextlink;
	}

	public static void main(String args[]){
		Vehicle car1 = new Vehicle();
		LinkedList c1 = new LinkedList(car1);

		Vehicle car2 = new Vehicle();
		LinkedList c2 = new LinkedList(car2, c1);

		Vehicle car3 = new Vehicle();
		LinkedList c3 = new LinkedList(car3,c2);
	}
}
