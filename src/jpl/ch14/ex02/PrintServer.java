package jpl.ch14.ex02;

/**run()内で，PrintServerとして定義したrunの場合のみ処理可能とした
 * Runnableとして扱う場合は，"private final Thread th"が定義されていないため，
 * クライアントがrunを呼ぶことができない．*/
class PrintServer implements Runnable {

	private final PrintQueue requests = new PrintQueue();
	private final Thread th;
	public PrintServer() {
		th = new Thread(this);
		th.start();
	}

	public void print(PrintJob job){
		requests.add(job);
	}

	@Override
	public void run() {

		if(Thread.currentThread() != th)
			System.out.println("Cannot Access PrintServer !");
		for(;;)
			try {
				realPrint(requests.remove());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	private void realPrint(PrintJob job) {
		//印刷処理
		System.out.println(job);
	}

	public static void main(String[] args){
		PrintServer ps = new PrintServer();
		ps.print(PrintJob.COPY);
		ps.print(PrintJob.SCAN);
		ps.print(PrintJob.COPY);

		Runnable service = new PrintServer();
		new Thread(service).start();
	}

}
