package jpl.ch03.ex06;

class Battery extends EnergySource {
	int batteryamount;

	final int getbatteryamount(){
		return batteryamount;
	}

	final void setbatteryamount(int battery){
		batteryamount = battery;
	}
	@Override
	boolean empty() {
		if( batteryamount == 0)
			return true;
		return false;
	}

}
