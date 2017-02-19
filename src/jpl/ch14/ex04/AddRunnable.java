package jpl.ch14.ex04;

public class AddRunnable implements Runnable {

	private final int addvalue;
	public AddRunnable(int addvalue){
		this.addvalue = addvalue;
	}
	@Override
	public void run() {
		for(int i = 0; i< 10; i++){
			System.out.println(Thread.currentThread().getName() + MultiThreadAdd.addValue(addvalue));
		}
	}

}
