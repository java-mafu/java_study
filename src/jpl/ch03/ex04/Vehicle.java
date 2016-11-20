package jpl.ch03.ex04;

public class Vehicle {
	private int speed;
	private int angle;
	private String name;
	private static int nextID = 0;
	private int ID;
	public static final boolean TURN_RIGHT = true;
	public static final boolean TURN_LEFT = false;
	public Vehicle(){
		ID = ++nextID;
	}
	public Vehicle(String name){
		this();
		this.name = name;
	}

	static public final int maxID(){
		return nextID;
	}

	public String toString() {
		String desc = ID + " (" + name + ")";
		desc += " speed " + speed;
		desc += " angle " + angle;
		return desc;
	}

	public final int getSpeed(){
		return speed;
	}

	public final void setSpeed(int speed){
		this.speed = speed;
	}

	public final int getAngle(){
		return angle;
	}

	public final void setAngle(int angle){
		this.angle = angle;
	}

	public String getName(){
		return name;
	}

	public final void setName(String name){
		this.name = name;
	}

	public final int getNextID(){
		return nextID;
	}

	public final int getID(){
		return ID;
	}

	public void changeSpeed(int speed){
		this.speed = speed;
	}

	public void stop(){
		speed = 0;
	}

	public void turn(int angle){
		this.angle += angle;
	}

	public void turn(boolean angle){
		if (angle == TURN_RIGHT)
			this.angle += 90;
		else if(angle == TURN_LEFT)
			this.angle -= 90;
	}



	public static void main(String args[]){
		if(args.length ==0){
			System.out.println("There are no cars.");
		}
			for (String string : args) {
				Vehicle car = new Vehicle();
				car.setName(string);
				System.out.println(car.toString());
			}
	}
}