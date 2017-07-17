package jpl.ch02.ex05;

public class Vehicle {
	public int speed;
	public int angle;
	public String name;
	public static int nextID = 0;
	public int ID;

	public void printVehicle(){
		System.out.println("name:" + name);
		System.out.println("speed:" + speed);
		System.out.println("angle:" + angle);
		System.out.println("ID:" + ID);
	}

	public static void main(String args[]){
		Vehicle sedan = new Vehicle();
		Vehicle sports_car = new Vehicle();
		Vehicle truck = new Vehicle();

		sedan.speed = 10;
		sedan.angle = 20;
		sedan.name = "tanama";
		sedan.ID = 1;
		sedan.printVehicle();

		sports_car.speed = 40;
		sports_car.angle = 15;
		sports_car.name = "nakata";
		sports_car.ID = 2;
		sports_car.printVehicle();

		truck.speed = 5;
		truck.angle = 90;
		truck.name = "tamana";
		truck.ID = 3;
		truck.printVehicle();

	}

}
