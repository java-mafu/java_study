package jpl.ch14.ex06;

public class FifteenSecMessage extends Thread {
	private int secondCount;
	TimeDisplay td;

	public FifteenSecMessage(TimeDisplay td) {
		super("fifteen");
		secondCount = 0;
		this.td = td;
	}

	@Override
	public void run() {
		while (true) {
			while (secondCount++ < 15) {
				td.countTime();
			}
			System.out.println("15sec");
			secondCount = 0;
		}
	}
}
