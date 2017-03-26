package jpl.ch17.ex03;

import java.lang.ref.Reference;

class ReaperThread extends Thread {
	public void run() {
		while (true) {
			try {
				Reference<?> ref = queue.remove();
				Resource res = null;
				synchronized (ResourceManager.this) {
					res = refs.get(ref);
					refs.remove(ref);
				}
				res.release();
				ref.clear();
			} catch (InterruptedException ex) {
				break;
			}
		}
	}
}
