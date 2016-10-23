package jpl.ch02.ex06;
import jpl.ch02.ex05.Vehicle;

class LinkedList {
	Object list;
	LinkedList nextlink;

	public static void main(String args[]){
		Vehicle car1 = new Vehicle();
		Vehicle car2 = new Vehicle();
		Vehicle car3 = new Vehicle();

		car1.ID = Vehicle.nextID++;
		car2.ID = Vehicle.nextID++;
		car3.ID = Vehicle.nextID++;
	}
}
