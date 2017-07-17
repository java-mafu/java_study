package jpl.ch14.ex05;

class MultiThreadSub {
	private static Integer value = 200;
	private static boolean lockValue = false;//falseならアクセス可能．trueなら不可

	public static Integer subValue(Integer value){
		while(lockValue){
			try {
				Thread.currentThread().wait((int)(Math.random()*10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		lockValue = true;
		MultiThreadSub.value -= value;
		lockValue = false;
		return MultiThreadSub.value;
	}

	public static void main(String[] args) {
		SubRunnable[] subRunnable = {
				new SubRunnable(2),
				new SubRunnable(3),
				new SubRunnable(4),
				new SubRunnable(5),
				new SubRunnable(6)
				};
		for(int i = 0; i< subRunnable.length; i++){
			String threadName = "sub:" + (i+2) + " thread ";
			Thread th = new Thread(subRunnable[i], threadName);
			th.start();
		}
	}

}
