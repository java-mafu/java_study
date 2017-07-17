package jpl.ch02.ex13;

public class Vehicle {
	private int speed;
	private int angle;
	private String name;
	private static int nextID = 0;
	private int ID;

	public Vehicle(){
		ID = ++nextID;
	}
	public Vehicle(String name){
		this();
		this.name = name;
	}

	static public int maxID(){
		return nextID;
	}

	public String toString() {
		String desc = ID + " ( " + name + ")";
		desc += " speed " + speed;
		desc += " angle " + angle;
		return desc;
	}

	public int getSpeed(){
		return speed;
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}

	public int getAngle(){
		return angle;
	}

	public void setAngle(int angle){
		this.angle = angle;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getNextID(){
		return nextID;
	}

	public int getID(){
		return ID;
	}

	public static void main(String args[]){
		Vehicle sedan = new Vehicle("tanama");
		Vehicle sports_car = new Vehicle("nakata");
		Vehicle truck = new Vehicle("tamana");
		
		sedan.setSpeed(10);
		sedan.setAngle(20);
		sedan.setName("satoh");

		System.out.println(sedan);
		System.out.println(sports_car);
		System.out.println(truck);
	}

}
