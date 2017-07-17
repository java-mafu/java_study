package jpl.ch04.ex01;

class Battery implements EnergySource {
	int batteryamount;

	final int getbatteryamount(){
		return batteryamount;
	}

	final void setbatteryamount(int battery){
		batteryamount = battery;
	}
	@Override
	public
	boolean empty() {
		if( batteryamount == 0)
			return true;
		return false;
	}

	@Override
	public
	 void supply(int addenergy){
		setbatteryamount(batteryamount + addenergy);
	}

}
