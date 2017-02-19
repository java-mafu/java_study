package jpl.ch14.ex06;

public class TimeDisplay {

	static Integer counter;

	public TimeDisplay() {
		counter = 0;
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
		fsm = new FifteenSecMessage(td);
		fsm.start();

	}

}
