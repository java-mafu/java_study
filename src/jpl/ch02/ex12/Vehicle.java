package jpl.ch02.ex12;

public class Vehicle {
	public int speed;
	public int angle;
	public String name;
	public static int nextID = 0;
	public int ID;

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

	public void printVehicle(){
		System.out.println("name:" + name);
		System.out.println("speed:" + speed);
		System.out.println("angle:" + angle);
		System.out.println("ID:" + ID);
	}

	public static void main(String args[]){
		Vehicle sedan = new Vehicle("tanama");
		Vehicle sports_car = new Vehicle("nakata");
		Vehicle truck = new Vehicle("tamana");

		sedan.speed = 10;
		sedan.angle = 20;
		sedan.printVehicle();

		sports_car.speed = 40;
		sports_car.angle = 15;
		sports_car.printVehicle();

		truck.speed = 5;
		truck.angle = 90;
		truck.printVehicle();

		System.out.println(sedan);
		System.out.println(sports_car);
		System.out.println(truck);
	}

}
