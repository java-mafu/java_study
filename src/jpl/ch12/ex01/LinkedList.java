package jpl.ch12.ex01;
import jpl.ch03.ex08.Vehicle;

class LinkedList<T>{
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinkedList<?> other = (LinkedList<?>) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}

	private T list;
	private LinkedList<T> nextlink;
	static private int num = 0;

	LinkedList(){
		this.list = null;
		this.nextlink = null;
	}

	LinkedList(T list){
		this();
		this.list = list;
	}

	LinkedList(T list, LinkedList<T> nextlink){
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

	public T getList() {
		return list;
	}
	public void setList(T list) {
		this.list = list;
	}
	public LinkedList<T> getNextLink() {
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

	public void add(T list){
		if(nextlink != null){
			nextlink.add(list);
		}
		else
			nextlink = new LinkedList<T>(list);

	}

	public LinkedList<T> find(T object) throws ObjectNotFoundException{
		if(nextlink != null){
			if((nextlink.getList()).equals(object))
				return nextlink;
			else
				nextlink.find(object);
		}
		else
			throw new ObjectNotFoundException(object.toString());

		return null;//呼ばれない．

	}

	public static void main(String args[]){
		Vehicle sedan = new Vehicle("tanama");
		LinkedList<Vehicle> c1 = new LinkedList<Vehicle>(sedan);

		Vehicle sports_car = new Vehicle("nakata");
		LinkedList<Vehicle> c2 = new LinkedList<Vehicle>(sports_car, c1);

		Vehicle truck = new Vehicle("tamana");
		LinkedList<Vehicle> c3 = new LinkedList<Vehicle>(truck, c2);

		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
	}
}
