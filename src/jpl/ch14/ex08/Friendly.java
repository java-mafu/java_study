package jpl.ch14.ex08;

/*デットロックはThread1が二回呼ばれたときにおこるときもあれば，
 * 4回目で起きる時もあった．起きない時もあった．*/
class Friendly {
	private Friendly partner;
	private String name;

	public Friendly(String name) {
		this.name = name;
	}

	public synchronized void hug() {
		System.out.println(Thread.currentThread().getName() + " in " + name + ".hug() trying to invoke " + partner.name
				+ ".hugBack()");

		partner.hugBack();
	}

	public synchronized void hugBack() {
		System.out.println(Thread.currentThread().getName() + " in " + name + ".hugBack()");
	}

	public void becomeFriend(Friendly partner) {
		this.partner = partner;
	}

	public static void main(String[] args) {
		final Friendly jareth = new Friendly("jareth");
		final Friendly cory = new Friendly("cory");

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
