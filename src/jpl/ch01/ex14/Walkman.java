package jpl.ch01.ex14;

class Walkman {
	private boolean port = false;//一つの端子

	//portに接続
	public void connectPort(){
		port = true;
	}

	//portの接続解除
	public void disconnectPort(){
		port = false;
	}

	//portの状態確認
	public boolean getPort(){
		return port;
	}


	//音楽を聴く
	public boolean listenMusic(){
		if(port){
			System.out.println("The music plays");
			return true;
		}
		else {
			return false;
		}
	}
}
