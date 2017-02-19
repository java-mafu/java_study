package jpl.ch14.ex03;

public class AddRunnable implements Runnable {

	private final int addvalue;
	MultiThreadAdd mta;
	public AddRunnable(MultiThreadAdd mta, int addvalue){
		this.mta = mta;
		this.addvalue = addvalue;
	}
	@Override
	public void run() {
		for(int i = 0; i< 10; i++){
			System.out.println(Thread.currentThread().getName() + mta.addValue(addvalue));
		}
	}

}
