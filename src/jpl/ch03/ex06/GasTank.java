package jpl.ch03.ex06;

class GasTank extends EnergySource {

	private int gasamount = 0;

	final int getGasamount(){
		return gasamount;
	}

	final void setGasamount(int gas){
		gasamount = gas;
	}
	@Override
	boolean empty() {
		if(gasamount == 0)
			return true;
		return false;
	}
	@Override
	 void supply(int addenergy){
		setGasamount(gasamount + addenergy);
	}

}
