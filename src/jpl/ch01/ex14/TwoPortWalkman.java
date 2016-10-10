package jpl.ch01.ex14;

class TwoPortWalkman extends Walkman {
	private boolean secondport = false;

	public void connectSecondPort(){
		secondport = true;
	}

	public void disconnectSecondPort(){
		secondport = false;
	}

	public boolean getSecondPort(){
		return secondport;
	}

	//音楽を聴く
	public boolean listenMusic(){
		if(!getPort() && !secondport){
			return false;
		}
		if(getPort()){
			System.out.println("The music plays in Port1");
		}
		if(secondport){
			System.out.println("The music plays in Port2");
		}
		return true;
	}
}
