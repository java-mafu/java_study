package jpl.ch03.ex08;

public class Vehicle implements Cloneable{
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

	static public int maxID(){
		return nextID;
	}

	public String toString() {
		String desc = ID + " (" + name + ")";
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

	/*Cloneableインターフェースの作成*/
	/*複製に関しては，選択し１のcloneをサポートする方法でよい．
	 * ただし，速さや向きなどの数値は良いが，
	 * 所有者の参照を渡すと，コピー前とコピー後の所有者の両方が
	 * 変更される．（たぶん）
	 * そのため，cloneのオーバーライドでString型だけ深いコピーを行う*/
	public Vehicle clone(){
		try {
			Vehicle obj = (Vehicle) super.clone();
			obj.name = this.name;
			return obj;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
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