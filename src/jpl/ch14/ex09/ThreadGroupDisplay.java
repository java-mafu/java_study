package jpl.ch14.ex09;


/** 問題の意味がよくわかりません*/
class ThreadGroupDisplay {

	public static synchronized void displayThreadGroup(ThreadGroup tg){
		new Thread(new Runnable() {
			public void run() {
				Thread list[] = new Thread[tg.activeCount()];
				while(true){
					tg.enumerate(list, false);
					System.out.print("Thread List:");
					for(int i = 0; i < list.length; i++){
						if(list[i] != null)
						System.out.print(list[i].getName() + " ");
					}
					System.out.println();
					int hierarchy = 0;
					ThreadGroup tgnow = tg;
					while((tgnow = tgnow.getParent()) != null){
						hierarchy++;
					}
					System.out.println("ThreadGroup Hierarchy:" + hierarchy);

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
			}
		}).start();
	}


	public static void main(String[] args) {
		ShortThread th[] = new ShortThread[100];
		ThreadGroup tg1 = new ThreadGroup("group1");
		ThreadGroup tg2 = new ThreadGroup(tg1,"group2");
		ThreadGroup tg3 = new ThreadGroup(tg2,"group3");
		for(int i = 0; i < 100; i++){
			if(i<33)
				th[i] = new ShortThread(tg1,"Thread"+i);
			else if(i<66)
				th[i] = new ShortThread(tg2,"Thread"+i);
			else
				th[i] = new ShortThread(tg3,"Thread"+i);
			th[i].start();
		}
		ThreadGroupDisplay.displayThreadGroup(tg1);
		ThreadGroupDisplay.displayThreadGroup(tg2);
		ThreadGroupDisplay.displayThreadGroup(tg3);
	}

}
