package jpl.ch01.ex14;

class WalkmanPhone extends TwoPortWalkman {

	public boolean call(){
		if (!getPort() || !getSecondPort()){
			System.out.println("Call Failed !");
			return false;
		}

		System.out.println("Let's talk !");
		return true;
	}
}
