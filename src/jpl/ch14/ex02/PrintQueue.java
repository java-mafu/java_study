package jpl.ch14.ex02;

class PrintQueue {
	private SingleLinkedQueue<PrintJob> queue = new SingleLinkedQueue<PrintJob>();

	public synchronized void add(PrintJob j){
		queue.add(j);
		notifyAll();
	}

	public synchronized PrintJob remove() throws InterruptedException{
		while (queue.size() == 0)
			wait();
		return queue.remove();
	}
}
