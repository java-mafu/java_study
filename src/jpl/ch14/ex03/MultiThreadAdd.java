package jpl.ch14.ex03;

class MultiThreadAdd {
	private Integer value = 0;

	public Integer addValue(Integer value){
		synchronized(this){
			this.value += value;
			return this.value;
		}
	}

	public static void main(String[] args) {
		MultiThreadAdd mta = new MultiThreadAdd();
		AddRunnable[] addRunnable = {
				new AddRunnable(mta,2),
				new AddRunnable(mta,3),
				new AddRunnable(mta,4),
				new AddRunnable(mta,5),
				new AddRunnable(mta,6)
				};
		for(int i = 0; i< addRunnable.length; i++){
			String threadName = "add:" + (i+2) + " thread ";
			Thread th = new Thread(addRunnable[i], threadName);
			th.start();
		}
	}

}
