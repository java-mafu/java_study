package jpl.ch14.ex05;

public class SubRunnable implements Runnable {

	private final int subvalue;
	public SubRunnable(int subvalue){
		this.subvalue = subvalue;
	}
	@Override
	public void run() {
		for(int i = 0; i< 10; i++){
			System.out.println(Thread.currentThread().getName() + MultiThreadSub.subValue(subvalue));
		}
	}

}
