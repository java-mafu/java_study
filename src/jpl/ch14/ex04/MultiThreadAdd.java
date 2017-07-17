package jpl.ch14.ex04;

class MultiThreadAdd {
	private static Integer value = 0;

	public static Integer addValue(Integer value){
		synchronized(MultiThreadAdd.value){
			MultiThreadAdd.value += value;
			return MultiThreadAdd.value;
		}
	}

	public static void main(String[] args) {
		AddRunnable[] addRunnable = {
				new AddRunnable(2),
				new AddRunnable(3),
				new AddRunnable(4),
				new AddRunnable(5),
				new AddRunnable(6)
				};
		for(int i = 0; i< addRunnable.length; i++){
			String threadName = "add:" + (i+2) + " thread ";
			Thread th = new Thread(addRunnable[i], threadName);
			th.start();
		}
	}

}
