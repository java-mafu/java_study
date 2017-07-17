package jpl.ch14.ex08;


/** yieldを使ったデットロック回避方法がわからない*/
class YieldFriendly {

	private YieldFriendly partner;
	private String name;
	private static boolean doHug = false;

	public YieldFriendly(String name) {
		this.name = name;
	}

	public synchronized void hug() {
		if(doHug){
			Thread.yield();
		}
		System.out.println(Thread.currentThread().getName() + " in " + name + ".hug() trying to invoke " + partner.name
				+ ".hugBack()");

			doHug = true;
		partner.hugBack();
	}

	public synchronized void hugBack() {
		System.out.println(Thread.currentThread().getName() + " in " + name + ".hugBack()");
		doHug = false;
	}

	public void becomeFriend(YieldFriendly partner) {
		this.partner = partner;
	}

	public static void main(String[] args) {
		final YieldFriendly jareth = new YieldFriendly("jareth");
		final YieldFriendly cory = new YieldFriendly("cory");

		jareth.becomeFriend(cory);
		cory.becomeFriend(jareth);

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				public void run() {
					jareth.hug();
				}
			}, "Thread1").start();

			new Thread(new Runnable() {
				public void run() {
					cory.hug();
				}
			}, "Thread2").start();
		}
	}
}
