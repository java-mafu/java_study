package jpl.ch04.ex01;

class GasTank implements EnergySource {

	private int gasamount = 0;

	final int getGasamount(){
		return gasamount;
	}

	final void setGasamount(int gas){
		gasamount = gas;
	}
	@Override
	public
	boolean empty() {
		if(gasamount == 0)
			return true;
		return false;
	}
	@Override
	public
	 void supply(int addenergy){
		setGasamount(gasamount + addenergy);
	}

}
