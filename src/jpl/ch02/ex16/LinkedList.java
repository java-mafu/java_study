package jpl.ch02.ex16;
import jpl.ch02.ex10.Vehicle;

class LinkedList {
	private Object list;
	private LinkedList nextlink;
	static private int num = 0;

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

	public String toString() {
		String desc = list.toString();
		if (nextlink != null){
			desc += " nextlink "+ nextlink.toString();
		}
		return desc;
	}

	public Object getList() {
		return list;
	}
	public void setList(Object list) {
		this.list = list;
	}
	public LinkedList getNextLink() {
		return nextlink;
	}

	public int totalLinkNum(){
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

	public static void main(String args[]){
		Vehicle sedan = new Vehicle("tanama");
		LinkedList c1 = new LinkedList(sedan);

		Vehicle sports_car = new Vehicle("nakata");
		LinkedList c2 = new LinkedList(sports_car, c1);

		Vehicle truck = new Vehicle("tamana");
		LinkedList c3 = new LinkedList(truck, c2);

		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
	}
}
