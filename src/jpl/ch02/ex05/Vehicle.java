package jpl.ch02.ex05;

public class Vehicle {
	public int speed;
	public int angle;
	public String name;
	public static int nextID = 0;
	public int ID;

	public Vehicle(){}
	public Vehicle(int speed, int angle, String name){
		this.speed = speed;
		this.angle = angle % 360;
		this.name = name;
		this.ID = nextID;
		nextID++;
	}
	public void printVehicle(){
		System.out.println("name:" + name);
		System.out.println("speed:" + speed);
		System.out.println("angle:" + angle);
		System.out.println("ID:" + ID);
	}

	public static void main(String args[]){
		Vehicle sedan = new Vehicle(10, 20, "tanama");
		Vehicle sports_car = new Vehicle(40, 15, "nakata");
		Vehicle truck = new Vehicle(5, 90, "tamana");

		sedan.printVehicle();
		sports_car.printVehicle();
		truck.printVehicle();
	}

}
