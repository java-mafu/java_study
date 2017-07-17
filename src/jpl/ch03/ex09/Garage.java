package jpl.ch03.ex09;

import jpl.ch03.ex08.Vehicle;

public class Garage implements Cloneable {
	int id;
	private Vehicle[] carlist;
	public Garage(){
		id = 0;
	}

	public Garage(Vehicle[] carlist){
		this.carlist = carlist;
		id = carlist.length;
	}

	public Vehicle[] getCarlist(){
		return carlist;
	}

	public void setCarlist(Vehicle[] carlist){
		this.carlist = carlist;
		id = carlist.length;
	}

	public void addCarlist(Vehicle car){
		if(id < carlist.length)
			carlist[id] = car;
		else{
			Vehicle[] obj = new Vehicle[id+1];
			for(int i = 0; i < id; i++)
				obj[i] = carlist[i];
			obj[id] = car;
			carlist = obj;
		}
		id++;
	}

	public void getCarlistName(){
		for( int i = 0; i < carlist.length; i++){
			System.out.println(carlist[i].getName());
		}
	}
	public Garage clone(){
		try {
			Garage obj = (Garage) super.clone();
			obj.carlist = carlist.clone();
			return obj;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	public static void main(String[] args) {
		Vehicle car1 = new Vehicle("person1");
		Vehicle car2 = new Vehicle("person2");
		Vehicle car3 = new Vehicle("person3");
		Vehicle car4 = new Vehicle("person4");

		Vehicle[] cars = {car1,car2,car3,car4};
		Garage g = new Garage(cars);
		Garage newG = g.clone();
		Vehicle car5 =new Vehicle("m...mother !?");
		newG.addCarlist(car5);

		g.getCarlistName();
		System.out.println();
		newG.getCarlistName();
	}

}
