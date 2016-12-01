package jpl.ch03.ex04;

import jpl.ch02.ex18.Vehicle;

class PassengerVehicle extends Vehicle {

	private int seatnum;
	private int passengernum;

	public PassengerVehicle(String name, int seatnum, int passengernum){
		 super(name);
		 this.seatnum = seatnum;
		 this.passengernum = passengernum;
	}

	public PassengerVehicle(String name, int seatnum){
		 super(name);
		 this.seatnum = seatnum;
		 this.passengernum = 0;
	}

	public PassengerVehicle(int seatnum){
		 super("No name");
		 this.seatnum = seatnum;
		 this.passengernum = 0;
	}

	public String toString(){
		String desc;
		desc = super.toString();
		desc += " seatnum " + seatnum;
		desc += " passengernum " + passengernum;
		return desc;
	}
	public final int getSeatnum(){
		return seatnum;
	}

	public final int getPassengernum(){
		return passengernum;
	}

	public final void setPassengernum(int passenger){
		passengernum = passenger;
	}

	public static void main(String[] args) {
		PassengerVehicle car1 = new PassengerVehicle("person1", 8, 5);
		PassengerVehicle car2 = new PassengerVehicle("person2", 5);
		PassengerVehicle car3 = new PassengerVehicle(2);

		System.out.println(car1.toString());
		System.out.println(car2.toString());
		System.out.println(car3.toString());
	}

}
