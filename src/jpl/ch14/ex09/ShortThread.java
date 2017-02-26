package jpl.ch14.ex09;

public class ShortThread extends Thread{
	public ShortThread(ThreadGroup tg,String name){
		super(tg, name);
	}
	@Override
	public void run(){
		try {
			sleep(10);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}