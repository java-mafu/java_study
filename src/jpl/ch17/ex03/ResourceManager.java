package jpl.ch17.ex03;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public final class ResourceManager {

	Reference reference;
	final ReferenceQueue<Object> queue;
	final Thread reaper;
	boolean shutdown = false;

	public ResourceManager() {
		queue = new ReferenceQueue<Object>();
		reaper = new ReaperThread();
		reaper.start();

		// リソース初期化
	}

	public synchronized void shutdown() {
		if (!shutdown) {
			shutdown = true;
			reaper.interrupt();
		}
	}

	public synchronized Resource getResource(Object key) {
		if (shutdown)
			throw new IllegalStateException();
		reference = new PhantomReference(key, queue);
		Resource res = new ResourceQueue(key);
		return res;
	}

	private static class ResourceImpl implements Resource {

		int keyHash;
		boolean needsRelease = false;

		ResourceImpl(Object key) {
			keyHash = System.identityHashCode(key);

			// 外部リソースの設定

			needsRelease = true;
		}

		@Override
		public void use(Object key, Object... args) {
			if (System.identityHashCode(key) != keyHash)
				throw new IllegalArgumentException("wrong key");

			// リソース使用
		}

		@Override
		public void release() {
			if (needsRelease) {
				needsRelease = false;

				// リソースの解放
			}
		}

	}

	private static class ResourceQueue implements Resource {

		int keyHash;
		boolean needsRelease = false;

		ResourceQueue(Object key){
			keyHash = System.identityHashCode(key);

			//外部リソースの設定

			needsRelease = true;
		}


		@Override
		public void use(Object key, Object... args) {
			if (System.identityHashCode(key) != keyHash)
				throw new IllegalArgumentException("wrong key");

			//リソース使用
		}

		@Override
		public void release() {
			if (needsRelease) {
				needsRelease = false;

				//リソースの解放
			}
		}

	}

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
