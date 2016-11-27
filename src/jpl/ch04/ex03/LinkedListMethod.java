package jpl.ch04.ex03;

import jpl.ch04.ex01.Vehicle;

class LinkedListMethod implements LinkedList {
	Object list;
	LinkedList nextlink;
	int num = 0;

	LinkedListMethod(){
		this.list = null;
		this.nextlink = null;
	}


	LinkedListMethod(Object list){
		this();
		this.list = list;
	}

	public String toString() {
		String desc = list.toString();
		if (nextlink != null){
			desc += " nextlink "+ nextlink.toString();
		}
		return desc;
	}

	@Override
	public Object getList() {
		return list;
	}

	@Override
	public void setList(Object list) {
		this.list = list;
	}

	LinkedListMethod(Object list, LinkedList nextlink){
		this.list = list;
		this.nextlink = nextlink;
	}

	@Override
	public LinkedList getNextLink()  {
		return nextlink;
	}

	@Override
	public int totalLinkNum() {
		num++;
		int totalnum = num;

		if (nextlink != null)
			 totalnum = nextlink.totalLinkNum();
		else {
			totalnum = num;
			num = 0;
		}
		return totalnum;
	}

	@Override
	public LinkedList clone() {
		try {
			LinkedListMethod obj = (LinkedListMethod) super.clone();
			obj.list = this.list;

			return obj;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	public static void main(String args[]){
		Vehicle sedan = new Vehicle("tanama");
		LinkedList c1 = new LinkedListMethod(sedan);

		Vehicle sports_car = new Vehicle("nakata");
		LinkedList c2 = new LinkedListMethod(sports_car, c1);

		Vehicle truck = new Vehicle("tamana");
		LinkedList c3 = new LinkedListMethod(truck, c2);

		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
	}
}
