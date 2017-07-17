package jpl.ch14.ex06;

/*7secの方法がわかりませんでした*/
public class FifteenSecMessage extends Thread {
	private int secondCount;

	public FifteenSecMessage() {
		super("fifteen");
		secondCount = 0;
	}

	@Override
	public void run() {
		while (true) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			while (secondCount < 15) {
				secondCount++;
			}
			System.out.println("15sec");
			secondCount = 0;
		}
	}
}
