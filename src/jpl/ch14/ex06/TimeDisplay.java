package jpl.ch14.ex06;

public class TimeDisplay extends Thread {

	static Integer counter;

	public TimeDisplay() {
		counter = 0;
	}

	@Override
	public void run() {
		while (true)
			countTime();
	}

	synchronized public void countTime() {
		try {
			wait(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notifyAll();
		counter++;
		System.out.println("total:" + counter + "sec");
	}

	public static void main(String[] args) {
		TimeDisplay td;
		FifteenSecMessage fsm;
		td = new TimeDisplay();
		fsm = new FifteenSecMessage();
		fsm.start();
		td.start();
	}

}
